package com.shaklee.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


@Component
@PropertySource(value = "classpath:UserDataStorage.properties")
public class UserDataStorageDAO {
	
	@Autowired
    JdbcTemplate jdbcTemplate;
	
   
	@Value("${GET_QUESTIONS}")
	private String GET_QUESTIONS;

	public List getQuestions(final String healthProfileId) {

		
		// make the SQL based on userId OR healthProfileId
		final String sql = GET_QUESTIONS.replace("??", "S.HEALTH_PROFILE_ID");
		String param = healthProfileId;

		List data = jdbcTemplate.queryForList(sql, param);
		
		return data;
	}
	
	
}
