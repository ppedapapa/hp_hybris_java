package com.shaklee.DAOImpl;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.shaklee.DAO.UserDAO;
import com.shaklee.DAO.UserDataStorageDAO.UserDataResponse;
import com.shaklee.entity.User;
import com.shaklee.shared.dao.BaseJdbcTemplateDAO;

@Component
@PropertySource(value = "classpath:props/User.properties")
public class UserDAOImpl extends BaseJdbcTemplateDAO implements UserDAO {

	private static final BeanPropertyRowMapper<User> userRowMapper = BeanPropertyRowMapper.newInstance(User.class);

	public UserDAOImpl(DataSource dataSource) {
		super(dataSource);
	}

	private static Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

	@Value("${GET_ID_BY_EMAIL}")
	String GET_ID_BY_EMAIL;

	@Value("${GET_BY_EMAIL}")
	String GET_BY_EMAIL;

	@Value("${GET_BY_ID}")
	String GET_BY_ID;

	@Override
	public User findUser(String userId) {

		User user = jdbcTemplate.queryForObject(GET_BY_ID, userRowMapper, userId);
		return user;
	}

	@Override
	public String getIdForEmail(String email) {

		String userId = null;
		try {
			userId = jdbcTemplate.queryForObject(GET_ID_BY_EMAIL, String.class, email);
		} catch (IncorrectResultSizeDataAccessException e) {

		}
		return userId;
	}

	@Override
	public User findByEmail(String email) {

		try
		{
			User user = jdbcTemplate.queryForObject(GET_BY_EMAIL, userRowMapper, email);
			return user;
		}
		catch (EmptyResultDataAccessException e)
		{
			return null;
		}
	
	}

}
