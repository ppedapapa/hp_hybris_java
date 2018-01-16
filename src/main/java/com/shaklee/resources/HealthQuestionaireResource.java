package com.shaklee.resources;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.security.Principal;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shaklee.DAO.UserDataStorageDAO.UserDataResponse;
import com.shaklee.model.HealthQuestionnaireModel;
import com.shaklee.util.StatusResponse;
import com.shaklee.util.Questions;



@RestController
@RequestMapping("/public/hp")
public class HealthQuestionaireResource {
	
	@Autowired
	HealthQuestionnaireModel healthQuestionnaireModel;
	
	// Logger
	private static final Logger LOG = LoggerFactory
				.getLogger(HealthQuestionaireResource.class);

	
	/*
	@RequestMapping(path = "/questionsByHealthProfileId", method = POST)
	public ResponseEntity< List<Map<String,Object>> > questionsByHealthProfileId(@RequestBody UserRequest request)
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    String currentUserName = authentication.getName();
		}
		
		 List<Map<String,Object>>  data = userDataStorageDAO.getQuestions(request.health_profile_id);
		
		LOG.info("Got data from cloud: " + data.size());
		
		return new ResponseEntity< List<Map<String,Object>> >( data, HttpStatus.OK);
	}
	*/
	
	@RequestMapping(path = "/testUserId", method = GET)
	public String testUserId(Principal principal)
	{
		String currentUserName = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    currentUserName = authentication.getName();
		}
		
		if (currentUserName == null)
			return "user_not_logged";
		
		return principal.getName();
		
	}

	@RequestMapping(path = "/getAllHealthPrints", method = POST)
	public MultipleHealthProfilesResponse getAllHealthPrints(
			@RequestBody UserRequestForGetAllHealthPrints request)
	{
		String currentUserName = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    currentUserName = authentication.getName();
		}
		
		return healthQuestionnaireModel.getAllHealthPrints(currentUserName, request.email, request.downline_id);
	}

	public static class UserRequestForGetAllHealthPrints {
		// @ShakleeID
		// public String user_id;
		public String email;
		public String downline_id;
	}
	
	public static final class MultipleHealthProfilesResponse extends StatusResponse {

		public List<UserDataResponse> data;

		public MultipleHealthProfilesResponse(List<UserDataResponse> data) {
			super(SUCCESS);
			this.data = data;
		}

		public MultipleHealthProfilesResponse(int status) {
			super(status);
		}
	}


	public static final class QuestionsResponse  {

		public String data;

		QuestionsResponse(String data) {
			this.data = data;
		}
	}
		
		public static class UserRequest {
			public String health_profile_id;
		}

		public static class BaseStorageRequest extends CommonStorageRequest {
			public String user_id;
		}
		
		public static class CommonStorageRequest {
			public String email;

			@Size(max = 50)
			public String first_name;

			@Size(max = 50)
			public String last_name;

			@NotNull
			public Questions questions;
		}
}
