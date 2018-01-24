package com.shaklee.rulesets.healthQuestionaire;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

//import com.shaklee.DAO.EventStreamDAO;
//import com.shaklee.DAO.SearchDistributorDAO;
//import com.shaklee.DAO.SearchDistributorDAO.UserData;
import com.shaklee.common.util.LazyLoadingSecureRandom;
import com.shaklee.common.util.UTCDateUtils;
//import com.shaklee.resources.EventStreamResource;
//import com.shaklee.resources.mulesoft.MuleSoftLeadResource;
import com.shaklee.shared.util.StatusResponse;
import com.shaklee.shared.validation.InputValidationException;
import com.shaklee.shared.validation.Validators;

@Component
public class HQUserDataStorageModel {

	private static final Logger logger = Logger.getLogger(HQUserDataStorageModel.class);

	private LazyLoadingSecureRandom random = new LazyLoadingSecureRandom();

	private long RECENT_MS = DateUtils.MILLIS_PER_DAY * 1;

	@Autowired
	UserDataStorageDAO userDataDAO;

	@Autowired
	SearchDistributorDAO searchDistributorDAO;

	@Autowired
	EventStreamResource eventStreamResource;

	@Autowired
	EventStreamDAO eventStreamDAO;

	@Autowired
	HQReport hqReport;
	
	@Autowired
	MuleSoftLeadResource muleSoftLeadResource;

	// TODO: Do not use service objects (StorageRequest).
	public String insertUserDataStorageAndGetHealthProfileId(final StorageRequest request, String shakleeId)
			throws InputValidationException {

		// start with the email flow
		if (isGuest(request)) {
			Validators.assertRequired(request.email, "email");
			
			// if opt_in is null default to false
			if (request.opt_in == null)
				request.opt_in = false;

			final Date followUp = userDataDAO.getFollowup(request.email, null);

			// healthProfileId will change only when we get
			// DuplicateKeyException

			long hqId;
			String healthProfileId = getNewId();
			try {
				hqId = userDataDAO.insert(null, request.email, request.first_name, request.last_name, healthProfileId,
						request.questions, request.opt_in, request.host_name, followUp, request.referrer_code, request.share_with_distributors);
			} catch (DuplicateKeyException e) {
				healthProfileId = getNewId();
				hqId = userDataDAO.insert(null, request.email, request.first_name, request.last_name, healthProfileId,
						request.questions, request.opt_in, request.host_name, followUp, request.referrer_code, request.share_with_distributors);
			}
			// insert into HQ Report
			hqReport.update(request.referrer_id, shakleeId, request.email, hqId);
			
			// Send to mulesoft - lead/create
			if (request.referrer_id != null)
			{
				muleSoftLeadResource.createLead(request.referrer_id, request.email, request.first_name, request.last_name, request.questions.country_code, hqId);
			}
			return healthProfileId;
		} else
			// email flow is over, proceed with userId flow
			return insertByUserId(request, shakleeId);
		// TODO:else return error
	}

	static boolean isGuest(final StorageRequest request) {
		Boolean isGuest = request.questions != null ? request.questions.is_guest : null;
		return isGuest != null && isGuest.booleanValue();
	}

	public boolean noRecentSubmissions(String email, String shakleeId) {
		Date created = userDataDAO.lastCreatedDate(email, shakleeId);
		if (created == null)
			return true;
		return created.getTime() < System.currentTimeMillis() - RECENT_MS;
	}

	// TODO: Do not use service objects (StorageRequest).
	private String insertByUserId(StorageRequest request, String shakleeId) {
		String healthProfileId = null;
		long hqId = 0;
		if (shakleeId != null) {

			// create new record
			healthProfileId = getNewId();

			// get followup
			Date followUp = userDataDAO.getFollowup(null, shakleeId);

			// healthProfileId will change only when we get
			// DuplicateKeyException
			try {
				hqId = userDataDAO.insert(shakleeId, null, request.first_name, request.last_name, healthProfileId,
						request.questions, request.opt_in, request.host_name, followUp, request.referrer_code, request.share_with_distributors);
			} catch (DuplicateKeyException e) {
				healthProfileId = getNewId();
				hqId = userDataDAO.insert(shakleeId, null, request.first_name, request.last_name, healthProfileId,
						request.questions, request.opt_in, request.host_name, followUp, request.referrer_code, request.share_with_distributors);
			}
			// insert into HQ Report
			hqReport.update(request.referrer_id, shakleeId, request.email, hqId);
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

	/**
	 * Associate the userId to the existing record
	 */
	// TODO: Do not use service objects (AssociateRequest).
	public void associate(AssociateRequest request) {

		// GetAllHealthPrints of the user based on email

		List<UserDataResponse> allHealthPrints = userDataDAO.getAllHealthPrints(request.email,
				IgnoreUnknownFieldsQuestions.class);

		if (allHealthPrints != null && allHealthPrints.size() > 0) {
			for (UserDataResponse healthPrint : allHealthPrints) {
				Questions questions = healthPrint.questions;

				// manipulate questions and set isGuest to false;
				questions.is_guest = false;

				userDataDAO.updateUserIdAndAnswerJSONByProfielId(request.user_id, questions,
						healthPrint.getHealth_profile_id());
			}

		}

	}

	/**
	 * Method to get the existing questionnaire by health profile id.
	 */
	public UserDataRequest getByHealthProfileId(final String healthProfileId) {
		return userDataDAO.getQuestions(healthProfileId, IgnoreUnknownFieldsQuestions.class);
	}

	/**
	 * Method to get all health_profile_ids.
	 */
	public List<UserDataResponse> getAllHealthProfileIds(final String userId, final String email,
			final String downlineId) {
		return userDataDAO.getTop20HealthPrints(userId, email, downlineId, IgnoreUnknownFieldsQuestions.class);
	}

	public String getProfileByEmail(String email) {
		return userDataDAO.getProfileIdByEmail(email);
	}

	public String getProfileById(String userId) {
		return userDataDAO.getProfileIdById(userId);
	}

	public StatusResponse healthPrintCompleted(String userId, String healthProfileId) throws InputValidationException {
		UserDataRequest userDataRequest = getByHealthProfileId(healthProfileId);

		if (userDataRequest != null && userDataRequest.questions != null) {
			if (userDataRequest.questions.is_guest) {
				return eventStreamResource.insert(formatDate(new Date()), userId,
						eventStreamDAO.STREAM_EVENT_GUEST_HEALTH_PRINT_COMPLETED, false, null, null, healthProfileId);
			} else {
				userId = userDataRequest.user_id;

				return eventStreamResource.insert(formatDate(new Date()), userId,
						eventStreamDAO.STREAM_EVENT_MEMBER_HEALTH_PRINT_COMPLETED, false, null, null, healthProfileId);
			}
		} else {
			return StatusResponse.dataNotFound("data not found for healthProfileId:" + healthProfileId);
		}
	}

	static String formatDate(Date date) throws InputValidationException {
		if (date == null)
			return null;

		try {
			return UTCDateUtils.formatUTCDate(date);
		} catch (Exception e) {
			throw new InputValidationException(
					"could not parse date :" + date + " format:" + UTCDateUtils.DATETIME_FORMAT);
		}
	}

	public Long getIdByEmail(String email) {
		return userDataDAO.getIdByEmail(email);
	}

	public Long getIdById(String userId) {
		return userDataDAO.getIdById(userId);
	}

}