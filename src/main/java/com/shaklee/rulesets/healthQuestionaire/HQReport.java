package com.shaklee.rulesets.healthQuestionaire;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shaklee.DAO.HQReportDAO;
import com.shaklee.DAO.HQReportDAO.HealthPrintReportDetails;
import com.shaklee.DAO.HQReportDAO.HealthPrintSummaryDetails;
import com.shaklee.DAO.SearchDistributorDAO;
import com.shaklee.DAO.SearchDistributorDAO.UserData;
import com.shaklee.DAO.UserDAO;
import com.shaklee.DAO.UserDataStorageDAO;
import com.shaklee.resources.HealthQuestionnaireResource.IgnoreUnknownFieldsQuestions;
import com.shaklee.resources.PublicHealthQuestionnaireResource.FollowupDate;
import com.shaklee.resources.PublicHealthQuestionnaireResource.FollowupDateResponse;
import com.shaklee.resources.data.HQReportDetails.HQUsers;
import com.shaklee.shared.util.StatusResponse;

/**
 * Model class for HQ Report
 * 
 * @author jkuriakos
 * 
 */
@Component
public class HQReport {
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private SearchDistributorDAO distributorDAO;
	@Autowired
	HQReportDAO hqReportDAO;
	@Autowired
	UserDataStorageDAO userDataStorageDAO;

	final static Logger LOGGER = Logger.getLogger(HQReport.class);

	public void update(String refererId, String userId, final String email, long hqId) {
		final String req = " Request : refererId : " + refererId + " userId : " + userId + " email : " + email
				+ " hqId : " + hqId;
		LOGGER.debug("HQReport request : " + req);

		try {
			// both userId and email can't be null at same time
			if (userId == null && email == null) {
				LOGGER.debug("HQReport exit - userId and email are null. " + req);
				return;
			}

			// proceed with the email flow
			// for email flow refererId is required
			if (email != null && refererId == null) {

				// for shaklee.com if email is existing user then go with userId
				// flow
				final UserData userData = getExtUser(email);
				if (userData != null && userData.shakleeID != null) {
					String extUserId = userData.shakleeID;

					if (extUserId != null) {
						LOGGER.debug("HQReport changing to userId " + extUserId + " as email exists " + req);
						updateByUserId(refererId, extUserId, hqId);
						return;
					}
				}

				LOGGER.debug("HQReport exit - email and  refererId are null. " + req);
				return;

			}

			if (email != null) {

				String originalReferrerId = refererId;
				refererId = getValidRefererId(refererId, req);

				// create new record
				// Distributors and Associates can view the Healthprint report
				if (originalReferrerId != null && refererId != null && !originalReferrerId.equals(refererId)) {
					LOGGER.debug("HQReport creating by email flow for two referer ids new referer - " + refererId
							+ " original referer - " + originalReferrerId + req);
					hqReportDAO.create(refererId, hqId, 0);
					hqReportDAO.create(originalReferrerId, hqId, 1);

				} else {
					LOGGER.debug(
							"HQReport creating by email flow only for  original referer - " + originalReferrerId + req);
					hqReportDAO.create(refererId, hqId, 1);
				}

				return;
			}

			// email flow is over proceed with userId
			updateByUserId(refererId, userId, hqId);

		} catch (Exception e) {
			LOGGER.error("HQReport error :  refererId : " + refererId + " userId : " + userId + " email : " + email, e);
		}

	}

	private UserData getExtUser(final String email) {
		try {
			return distributorDAO.findByEmail(email);
		} catch (Exception e) {
			return null;
		}
	}

	private String getValidRefererId(String refererId, final String req) {
		// make sure the reeferId is a BL, otherwise get the real BL
		if (refererId != null && userDAO.isBL(refererId) == false) {
			refererId = userDAO.getUplineBL(refererId);
			LOGGER.debug("HQReport refererId is not a BL uplineBL. " + req);
		}
		return refererId;
	}

	public void updateByUserId(String refererId, String userId, final long hqId) {
		try {
			final String req = " HQReport : refererId : " + refererId + " userId : " + userId + "hqId : " + hqId;

			if (userId != null) {
				// check if the userId id BL, then exit
				if (userDAO.isBL(userId)) {
					LOGGER.debug("HQReport exit - userId is a BL. " + req);
					return;
				}

				String originalRefererId = refererId;

				refererId = getValidRefererId(refererId, req);
				// create record
				// if userId is not null and refererId is null, get the BL
				// of the userId

				if (refererId == null) {
					refererId = userDAO.getUplineBL(userId);
					originalRefererId = userDAO.getUplineDist(userId);
				}
				LOGGER.debug("HQReport creating by userId. refererId : " + refererId + req);

				// Distributors and Associates can view the Healthprint
				if (originalRefererId != null && refererId != null && !originalRefererId.equals(refererId)) {
					LOGGER.debug("HQReport creating by userId for two referer ids new referer : " + refererId
							+ " refererIdUpline -  " + originalRefererId + req);
					hqReportDAO.create(refererId, hqId, 0);
					hqReportDAO.create(originalRefererId, hqId, 1);
				} else {
					LOGGER.debug("HQReport creating by userId for only one referer : " + refererId + req);
					hqReportDAO.create(refererId, hqId, 1);

				}
				return;
			}

		} catch (

		Exception e) {
			LOGGER.error(
					"HQReport update error :  refererId : " + refererId + " userId : " + userId + " hqId : " + hqId, e);
		}
	}

	public List<HealthPrintReportDetails> getHealthPrintReport(final String blId) {
		return hqReportDAO.getHealthPrintReport(blId);
	}

	public HealthPrintSummaryDetails getHealthPrintSummaryCountReport(final String shakleeID) {
		HealthPrintSummaryDetails summary = new HealthPrintSummaryDetails();
		summary.last_seven_days = hqReportDAO.getHealthPrintSummaryCountReport(shakleeID, 7);
		summary.last_thirty_days = hqReportDAO.getHealthPrintSummaryCountReport(shakleeID, 30);
		summary.overall = hqReportDAO.getHealthPrintSummaryCountReport(shakleeID, 180);
		return summary;
	}

	/**
	 * Method to check the existance by passed in HQId
	 */

	public boolean isExist(final long hqId) {
		final long count = hqReportDAO.getCountByHQId(hqId);
		return count > 0;
	}

	/**
	 * Method to provide the basic HQ report details
	 */
	public List<HQUsers> getBasicReport(final String blId) {
		return hqReportDAO.getBasicReport(blId);
	}

	public FollowupDateResponse updateFollowupDate(FollowupDate followupDate) {
		final boolean success = userDataStorageDAO.updateFollowupDateByHealthProfileID(followupDate);
		if (success)
			return new FollowupDateResponse(StatusResponse.SUCCESS);

		return new FollowupDateResponse(StatusResponse.SERVER_ERROR, "Update follow up date crashed!");
	}
}