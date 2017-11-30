package com.shaklee.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

import com.shaklee.util.BaseJDBCTemplateDAO;
import com.shaklee.util.JSONSerializer;


@Component
@PropertySource(value = "classpath:UserDataStorage.properties")
public class UserDataStorageDAO extends BaseJDBCTemplateDAO {
	
	
   
	@Value("${GET_QUESTIONS}")
	private String GET_QUESTIONS;
	
	@Value("${CREATE}")
	private String CREATE;


	public List getQuestions(final String healthProfileId) {

		
		// make the SQL based on userId OR healthProfileId
		final String sql = GET_QUESTIONS.replace("??", "S.HEALTH_PROFILE_ID");
		String param = healthProfileId;

		List data = jdbcTemplate.queryForList(sql, param);
		
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
