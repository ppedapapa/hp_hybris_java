package com.shaklee.DAO;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shaklee.DAO.UserDataStorageDAO;
import com.shaklee.DAO.UserDataStorageDAO.UserDataResponse;
import com.shaklee.resources.HealthQuestionaireResource.BaseStorageRequest;
import com.shaklee.rulesets.healthQuestionaire.Questions;
import com.shaklee.util.BaseJDBCTemplateDAO;
import org.springframework.jdbc.core.BeanPropertyRowMapper;


@Component
@PropertySource(value = "classpath:UserDataStorage.properties")
public class UserDataStorageDAO extends BaseJDBCTemplateDAO {

	private static Logger logger = LoggerFactory
			.getLogger(UserDataStorageDAO.class);

	public static final DateFormat dateFormatForCompletedTimeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private static final BeanPropertyRowMapper<UserDataResponse> userRowMapper = BeanPropertyRowMapper
			.newInstance(UserDataResponse.class);


	@Value("${GET_QUESTIONS}")
	private String GET_QUESTIONS;
	
	@Value("${GET_TOP_20_HEALTHPRINTS}")
	private String GET_TOP_20_HEALTHPRINTS;
	
	
	@Value("${GET_TOP_20_HEALTHPRINTS_FOR_DOWNLINES}")
	private String GET_TOP_20_HEALTHPRINTS_FOR_DOWNLINES;
		
	ObjectMapper objectMapper = new ObjectMapper();

	public List<Map<String,Object>> getQuestions(final String healthProfileId) {

		
		// make the SQL based on userId OR healthProfileId
		final String sql = GET_QUESTIONS.replace("??", "S.HEALTH_PROFILE_ID");
		String param = healthProfileId;

		List<Map<String,Object>> data = jdbcTemplate.queryForList(sql, new Object[] { param});
		
		return data;
	}
	
	public <T> List<UserDataResponse> getTop20HealthPrints(final String userId, final String email,
			final String downlineId) {

		List<UserDataResponse> response = null;
		// make the SQL based on email OR downline_Id or user_Id
		final String sql = (email != null) ? GET_TOP_20_HEALTHPRINTS.replace("??", "S.EMAIL")
				: (downlineId != null) ? GET_TOP_20_HEALTHPRINTS_FOR_DOWNLINES.replace("??", "S.USER_ID") : GET_TOP_20_HEALTHPRINTS.replace("??", "S.USER_ID");
		String param = (email != null) ? email : (downlineId != null) ? downlineId : userId;

		logger.debug(sql + "PARAM : " + param + " ,input sent are: userId:" + userId + " ,email:" + email
				+ " ,downlineId:" + downlineId);
		final List<UserDataResponse> top20HealthPrints = jdbcTemplate.query(sql, userRowMapper, param);

	
	
		for (UserDataResponse healthPrintsObject : top20HealthPrints) {
			healthPrintsObject.setQuestions( deserialize(Questions.class, healthPrintsObject.getHealth_profile_id(), healthPrintsObject.getAnswers_json()));
		}
			
		
		return top20HealthPrints;

	}


	
	<T> T deserialize(Class<T> type, String profileId, String answers_json) {
		if (answers_json == null)
			return null;

		try {
			return objectMapper.readValue(answers_json, type);
		} catch (IOException e) {
			logger.error("Deserialization failed for healthprint " + profileId + " " + answers_json, e);
			return null;
		}
	}
	
	public static class UserDataResponse extends UserDataRequest {

		public String completed_time_stamp;

		public Boolean opt_in;
		
		public Boolean share_with_distributors;

		public String getCompleted_time_stamp() {
			return completed_time_stamp;
		}

		public void setCompleted_time_stamp(String completed_time_stamp) {
			this.completed_time_stamp = completed_time_stamp;
		}

		public Questions getQuestions() {
			return questions;
		}

		public void setQuestions(Questions questions) {
			this.questions = questions;
		}

		public Boolean getOpt_in() {
			return opt_in;
		}

		public void setOpt_in(Boolean opt_in) {
			this.opt_in = opt_in;
		}

		public Boolean getShare_with_distributors() {
			return share_with_distributors;
		}

		public void setShare_with_distributors(Boolean share_with_distributors) {
			this.share_with_distributors = share_with_distributors;
		}
		
		
	}
	
	public static class UserDataRequest extends BaseStorageRequest {

		private String health_profile_id;
		
		private String answers_json;
		
		private String s_first_name;
		private String s_last_name;
		private String s_email;
		private String e_first_name;
		private String e_last_name;
		private String e_email;

		@JsonIgnore
		public String getAnswers_json() {
			return answers_json;
		}

		public void setAnswers_json(String answers_json) {
			this.answers_json = answers_json;
		}

		public String getUser_id() {
			return user_id;
		}

		public void setUser_id(String user_id) {
			this.user_id = user_id;
		}

		public String getEmail() {
			return e_email != null ? e_email : s_email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getFirst_name() {
			return s_first_name != null ? s_first_name : e_first_name;
		}

		public void setFirst_name(String first_name) {
			this.first_name = first_name;
		}

		public String getLast_name() {
			return s_last_name != null ? s_last_name : e_last_name;
		}

		public void setLast_name(String last_name) {
			this.last_name = last_name;
		}

		@JsonIgnore
		public String getS_first_name() {
			return s_first_name;
		}

		public void setS_first_name(String s_first_name) {
			this.s_first_name = s_first_name;
		}

		@JsonIgnore
		public String getS_last_name() {
			return s_last_name;
		}

		public void setS_last_name(String s_last_name) {
			this.s_last_name = s_last_name;
		}

		@JsonIgnore
		public String getS_email() {
			return s_email;
		}

		public void setS_email(String s_email) {
			this.s_email = s_email;
		}

		@JsonIgnore
		public String getE_first_name() {
			return e_first_name;
		}

		public void setE_first_name(String e_first_name) {
			this.e_first_name = e_first_name;
		}

		@JsonIgnore
		public String getE_last_name() {
			return e_last_name;
		}

		public void setE_last_name(String e_last_name) {
			this.e_last_name = e_last_name;
		}

		@JsonIgnore
		public String getE_email() {
			return e_email;
		}

		public void setE_email(String e_email) {
			this.e_email = e_email;
		}

		public String getHealth_profile_id() {
			return health_profile_id;
		}

		public void setHealth_profile_id(String health_profile_id) {
			this.health_profile_id = health_profile_id;
		}

	}
	
}
