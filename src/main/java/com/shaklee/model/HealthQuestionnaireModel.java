package com.shaklee.model;


import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.shaklee.DAO.UserDataStorageDAO;
import com.shaklee.DAO.UserDataStorageDAO.UserDataResponse;
import com.shaklee.resources.HealthQuestionnaireResource.MultipleHealthProfilesResponse;
import com.shaklee.shared.util.StatusResponse;

@Controller
public class HealthQuestionnaireModel {
	
	@Autowired 
	UserDataStorageDAO userDataStorageDAO;
	
	
	public MultipleHealthProfilesResponse getAllHealthPrints(String shakleeId, String email, String downline_id)
		{

		String request = "shakleeId: " + shakleeId + ", email: " + email + " , downline_id:" + downline_id;
		
		email = normalizeEmail(email);
		
		try {

			final List<UserDataResponse> data = getAllHealthProfileIds(shakleeId, email, downline_id);

			if (data == null) {
				
				return new MultipleHealthProfilesResponse(StatusResponse.NOT_FOUND);
			}

			MultipleHealthProfilesResponse r = new MultipleHealthProfilesResponse(data);

		
			return r;
		  } catch (Exception e) {
			String msg = "FAILED for shakleeId: " + shakleeId + ", email: " + email
					+ " , downline_id:" + downline_id;

			
			return (MultipleHealthProfilesResponse) new MultipleHealthProfilesResponse(StatusResponse.SERVER_ERROR);
		}
	}

	public static String normalizeEmail(String s) {
		s = StringUtils.trim(s);
		if (s != null)
			return s.toLowerCase();

		return s;
	}
	
	public List<UserDataResponse> getAllHealthProfileIds(final String userId, final String email,
			final String downlineId) {
		return userDataStorageDAO.getTop20HealthPrints(userId, email, downlineId);
	}

}
