package com.shaklee.DAOImpl;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.shaklee.DAO.UserDAO;
import com.shaklee.entity.User;
import com.shaklee.shared.dao.BaseJdbcTemplateDAO;

@Component
@PropertySource(value = "classpath:props/User.properties")
public class UserDAOImpl extends BaseJdbcTemplateDAO implements UserDAO  {
	
	public UserDAOImpl(DataSource dataSource) {
		super(dataSource);
	}

	private static Logger logger = LoggerFactory
			.getLogger(UserDAOImpl.class);

	@Value("${GET_ID_BY_EMAIL}")
	String GET_ID_BY_EMAIL;

	@Override
	public User findUser(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer findUserRank(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findSponsoring(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getRelativeLevel(String rootUserId, String downlineUserId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateEmailAddress(User user, String emailAddress) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateTelephoneNumber(User user, String telephoneNumber) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEmailUnique(User user, String emailAddress) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isUplineManger(String managerId, String downlineUserId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getUplineId(String customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getExistingUser(String email, String lastName, String addressLine1, String postalCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getIdForEmail(String email) {
		
		String userId = null;
		try
		{
			userId = jdbcTemplate.queryForObject(GET_ID_BY_EMAIL,String.class, email);
		}
		catch(IncorrectResultSizeDataAccessException e)
		{
			
		}
		return userId;
	}

	@Override
	public String getPartyId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUplineBL(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUplineDist(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTitleCode(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isBL(String userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getUserId(String partyId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isDownline(String sponsorId, String downlineId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkValidContactCreator(String contactId, String shakleeId) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getUserRank(String shakleeId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
