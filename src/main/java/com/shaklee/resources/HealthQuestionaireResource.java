package com.shaklee.resources;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shaklee.DAO.UserDataStorageDAO;

@RestController
public class HealthQuestionaireResource {
	
	@Autowired 
	UserDataStorageDAO userDataStorageDAO;
	
	@RequestMapping(path = "/get", method = POST)
	public ResponseEntity<Object> get(@RequestBody UserRequest request)
	{
		List data = userDataStorageDAO.getQuestions(request.health_profile_id);
		
		return new ResponseEntity<Object>( new QuestionsResponse("Got the data" + data.size()), HttpStatus.OK);
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

}
