package com.shaklee.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


@Component
public class UserDataStorageDAO {
	
	@Autowired
    JdbcTemplate jdbcTemplate;
	
   
	/*@Autowired
    PropertiesObject props;*/

	
	private String GET_QUESTIONS = "SELECT S.USER_ID, S.EMAIL, S.FIRST_NAME AS s_first_name, S.LAST_NAME  AS s_last_name, S.EMAIL AS s_email, S.OPT_IN, " + 
			"S.ANSWERS_JSON, S.HEALTH_PROFILE_ID " + 
			"FROM HQ_USER_DATA_STORAGE S WHERE ??=? "; 
	

	public List getQuestions(final String healthProfileId) {

		
		// make the SQL based on userId OR healthProfileId
		final String sql = GET_QUESTIONS.replace("??", "S.HEALTH_PROFILE_ID");
		String param = healthProfileId;

		List data = jdbcTemplate.queryForList(sql, param);
		
		return data;
	}
	
	
}
