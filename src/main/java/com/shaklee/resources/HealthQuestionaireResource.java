package com.shaklee.resources;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shaklee.DAO.UserDataStorageDAO;

@RequestMapping("/public")
@Controller
public class HealthQuestionaireResource {
	
	@Value("${server.port}")
	private int port;

	
	@Autowired 
	UserDataStorageDAO userDataStorageDAO;
	
	@RequestMapping(path = "/hp/get", method = GET)
	public String get()
	{
		return "redirect:/public/hp/getByHp";
		/*List data = userDataStorageDAO.getQuestions(request.health_profile_id);
		
		return new ResponseEntity<Object>( new QuestionsResponse("Got the data" + data.size()), HttpStatus.OK);
		*/
	}

	@RequestMapping(path = "/hp/getByHp", method = GET)
	public ResponseEntity<Object> getByHp()
	{
		List data = userDataStorageDAO.getQuestions("a5he2ptf2bjnspk0444exjv76");
		
		return new ResponseEntity<Object>( new QuestionsResponse("Got the data" + data.size() +"port:" + port), HttpStatus.OK);
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
