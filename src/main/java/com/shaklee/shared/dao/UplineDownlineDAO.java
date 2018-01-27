package com.shaklee.shared.dao;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.shaklee.common.util.PropertiesObject;

/**
 * UplineDownlineDAO
 * 
 * @author jkuriakos
 * 
 */
@Repository
public class UplineDownlineDAO {

	final JdbcTemplate jdbct;
	final static PropertiesObject props = new PropertiesObject(
			UplineDownlineDAO.class);
	final static String IS_DOWNLINE = props.getProperty("IS_DOWNLINE"),
			GET_USERNAME = props.getProperty("GET_USERNAME");

	@Autowired
	public UplineDownlineDAO(DataSource dataSource) {
		this.jdbct = new JdbcTemplate(dataSource);
	}

	public Map<String, Object> getUserName(String downlineId){
		Map<String, Object> downLineMap = new HashMap<String, Object>();
		try {
			downLineMap = jdbct.queryForMap(GET_USERNAME, downlineId);
			return downLineMap;
		} catch (EmptyResultDataAccessException e) {
			return downLineMap;
		}
	}

	public boolean isDownline(String downlineId, String uplineId) {
		try {
			return jdbct.queryForInt(IS_DOWNLINE, downlineId, uplineId,
					uplineId) == 1;
		} catch (EmptyResultDataAccessException e) {
			return false;
		} 
	}
}
