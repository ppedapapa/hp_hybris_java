package com.shaklee.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.shaklee.util.BaseJDBCTemplateDAO;


@Component
@PropertySource(value = "classpath:UserDataStorage.properties")
public class UserDataStorageDAO extends BaseJDBCTemplateDAO {
	
	
   
	@Value("${GET_QUESTIONS}")
	private String GET_QUESTIONS;
	

	public List getQuestions(final String healthProfileId) {

		
		// make the SQL based on userId OR healthProfileId
		final String sql = GET_QUESTIONS.replace("??", "S.HEALTH_PROFILE_ID");
		String param = healthProfileId;

		List data = jdbcTemplate.queryForList(sql, new Object[] { param});
		
		return data;
	}
	
	/*
	public <T> long insert(final String userId, final String email, final String firstName, final String lastName,
			final String healthProfileId, T data, final Boolean optIn, final String hostName, final Date followUp,
			final String referrerCode, final Boolean shareWithDistributors) {

		final String json = JSONSerializer.toJacksonJaxbJson(data, false);
		
		return insert(CREATE, userId, email, firstName, lastName, healthProfileId, json, optIn, hostName, followUp,
				referrerCode, shareWithDistributors);
	}
	*/
	
}
