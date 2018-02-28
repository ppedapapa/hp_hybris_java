package com.shaklee.model;


import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;

import com.shaklee.DAO.UserDAO;
import com.shaklee.DAO.UserDataStorageDAO;
import com.shaklee.DAO.UserDataStorageDAO.UserDataResponse;
import com.shaklee.common.util.validation.BeanValidator;
import com.shaklee.entity.User;
import com.shaklee.resources.HealthQuestionnaireResource.CommonStorageRequest;
import com.shaklee.common.util.LazyLoadingSecureRandom;
import com.shaklee.resources.HealthQuestionnaireResource.StorageRequest;
import com.shaklee.resources.HealthQuestionnaireResource.StorageResponse;
import com.shaklee.resources.HealthQuestionnaireResource.UserRequestForGetAllHealthPrints;
import com.shaklee.util.DataResponse;
import com.shaklee.resources.HealthQuestionnaireResource;
import com.shaklee.resources.HealthQuestionnaireResource.MultipleHealthProfilesResponse;
import com.shaklee.shared.util.StatusResponse;
import com.shaklee.shared.validation.InputValidationException;
import com.shaklee.shared.validation.Validators;

@Controller
public class HealthQuestionnaireModel {
	
	Logger logger = LoggerFactory.getLogger(HealthQuestionnaireModel.class);

	
	@Autowired 
	UserDataStorageDAO userDataStorageDAO;
	
	@Autowired
	UserDAO userDao;
	
	BeanValidator bval = BeanValidator.INSTANCE;
	
	private LazyLoadingSecureRandom random = new LazyLoadingSecureRandom();
	
	private long RECENT_MS = DateUtils.MILLIS_PER_DAY * 1;


	
	public MultipleHealthProfilesResponse getAllHealthPrints(UserRequestForGetAllHealthPrints requestObj)
		{

		String request = "shakleeId: " + requestObj.user_id + ", email: " + requestObj.email + " , downline_id:" + requestObj.downline_id
				+ "health profile id: " + requestObj.health_profile_id;
		
		requestObj.email = normalizeEmail(requestObj.email);
		
		try {

			final List<UserDataResponse> data = getAllHealthProfileIds(requestObj.health_profile_id, requestObj.user_id, 
					requestObj.email, requestObj.downline_id);

			if (data == null) {
				
				return new MultipleHealthProfilesResponse(StatusResponse.NOT_FOUND);
			}

			MultipleHealthProfilesResponse r = new MultipleHealthProfilesResponse(data);

		
			return r;
		  } catch (Exception e) {
			String msg = "FAILED for shakleeId: " + requestObj.user_id + ", email: " + requestObj.email
					+ " , downline_id:" + requestObj.downline_id + "health_print_id" + requestObj.health_profile_id;

			
			return (MultipleHealthProfilesResponse) new MultipleHealthProfilesResponse(StatusResponse.SERVER_ERROR);
		}
	}

	public static String normalizeEmail(String s) {
		s = StringUtils.trim(s);
		if (s != null)
			return s.toLowerCase();

		return s;
	}
	
	public List<UserDataResponse> getAllHealthProfileIds(String health_print_id, String user_id, String email, String downline_id) {
		return userDataStorageDAO.getTop20HealthPrints(health_print_id, user_id, email, downline_id);
	}
	
	public StorageResponse insert(final StorageRequest request, final String shakleeId)
			throws InputValidationException {
		
		// Don't assign request.questions.user_id = shakleeID if guest flag is
		// true.
		if (request.questions != null && request.questions.is_guest != null && !request.questions.is_guest) {
			request.questions.user_id = shakleeId;
		}
		
		normalizeCommonStorageRequest(request);
		bval.assertValid(request);

		try {

			if (request.questions.is_guest != null && request.questions.is_guest)
				Validators.assertRequired(request.email, "email");
			else
				Validators.assertRequired(request.questions.user_id, "questions.user_id");

			// check if there is a recent storage record before entering a new one. 
			final boolean noRecentSubmissions = noRecentSubmissions(request.email, shakleeId);
			
			// proceed with storage
			final String healthProfileId = insertUserDataStorageAndGetHealthProfileId(request,
					shakleeId);
			// done with storage, proceed with tracker
  
			// NO MORE TRACKER AFTER 11-03
			// challengeTracker.update(request.referrer_id, shakleeId,
			// request.email, healthProfileId);

			//logActivity(request.questions.user_id, "UPDATE", "SUCCESS health_profile_id: " + healthProfileId, request, null);

			// return the response
			if (healthProfileId != null) {
				final StorageResponse response = new StorageResponse();
				response.healthProfileId = healthProfileId;
				response.no_recent_submissions = noRecentSubmissions;
				return response;
			}
		} catch (InputValidationException e) {
			throw e;
		} catch (Exception e) {
			String msg = "FAILED for email: " + request.email + ", userId: " + request.questions.user_id;
			logger.error(msg, e);
			
			
			return (StorageResponse) new StorageResponse(DataResponse.SERVER_ERROR).message(msg);

		}
		return new StorageResponse();
	}
	
	
	public boolean noRecentSubmissions(String email, String shakleeId) {
		Date created = userDataStorageDAO.lastCreatedDate(email, shakleeId);
		if (created == null)
			return true;
		return created.getTime() < System.currentTimeMillis() - RECENT_MS;
	}
	
	public String insertUserDataStorageAndGetHealthProfileId(final StorageRequest request, String shakleeId)
			throws InputValidationException {

		// start with the email flow
		if (isGuest(request)) {
			Validators.assertRequired(request.email, "email");
			
			// if opt_in is null default to false
			if (request.opt_in == null)
				request.opt_in = false;

			//final Date followUp = userDataStorageDAO.getFollowup(request.email, null);

			// healthProfileId will change only when we get
			// DuplicateKeyException
			
			try {
				User userData = userDao.findByEmail(request.email);

				if (userData != null && userData.getContactId() != null) {
					return insertByUserId(request, userData.getContactId(), userData.getShakleeId());
				}
			} catch (EmptyResultDataAccessException e) {
				// normal case, not a shaklee user
			}

			long hqId;
			String healthProfileId = getNewId();
			try {
				hqId = userDataStorageDAO.insert(null, null, request.email, request.first_name, request.last_name, healthProfileId,
						request.questions, request.opt_in, request.host_name, request.referrer_code, request.share_with_distributors);
			} catch (DuplicateKeyException e) {
				healthProfileId = getNewId();
				hqId = userDataStorageDAO.insert(null, null, request.email, request.first_name, request.last_name, healthProfileId,
						request.questions, request.opt_in, request.host_name, request.referrer_code, request.share_with_distributors);
			}
			// insert into HQ Report
			//hqReport.update(request.referrer_id, shakleeId, request.email, hqId);
			
			// Send to mulesoft - lead/create
			if (request.referrer_id != null)
			{
				// muleSoftLeadResource.createLead(request.referrer_id, request.email, request.first_name, request.last_name, request.questions.country_code, hqId);
			}
			return healthProfileId;
		} else
		{
			User userData = userDao.findUser(shakleeId);
			// email flow is over, proceed with userId flow
			return insertByUserId(request, shakleeId, userData.getShakleeId());
		}
		// TODO:else return error
	}
	
	private String insertByUserId(StorageRequest request, String contactId, String accountId) {
		String healthProfileId = null;
		long hqId = 0;
		if (contactId != null) {

			// create new record
			healthProfileId = getNewId();

			// get followup
			//Date followUp = userDataStorageDAO.getFollowup(null, shakleeId);

			// healthProfileId will change only when we get
			// DuplicateKeyException
			try {
				hqId = userDataStorageDAO.insert(contactId, accountId, null, request.first_name, request.last_name, healthProfileId,
						request.questions, request.opt_in, request.host_name, request.referrer_code, request.share_with_distributors);
			} catch (DuplicateKeyException e) {
				healthProfileId = getNewId();
				hqId = userDataStorageDAO.insert(contactId, accountId,  null, request.first_name, request.last_name, healthProfileId,
						request.questions, request.opt_in, request.host_name, request.referrer_code, request.share_with_distributors);
			}
			// insert into HQ Report
			//hqReport.update(request.referrer_id, shakleeId, request.email, hqId);
			// insert into health print
			return healthProfileId;

		}
		return healthProfileId;
	}

	/**
	 * Utility method to generate random ids
	 */
	private String getNewId() {
		return new BigInteger(128, random.getRandom()).toString(36);
	}

	static boolean isGuest(final StorageRequest request) {
		Boolean isGuest = request.questions != null ? request.questions.is_guest : null;
		return isGuest != null && isGuest.booleanValue();
	}
	
	static void normalizeCommonStorageRequest(CommonStorageRequest r) {
		r.email = normalizeEmail(r.email);
	}
}
