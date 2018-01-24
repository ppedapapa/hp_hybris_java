package com.shaklee.shared.dao.prop;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import com.shaklee.common.util.cache.CachingLoader;
import com.shaklee.common.util.cache.Loader;
import com.shaklee.shared.dao.JdbcTemplateDAO;

/**
 * Application properties which are stored in the database.
 */
@Component
public class ApplicationPropertiesDAO extends JdbcTemplateDAO {

	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private final String GET_BY_NAME = props.getSql("GET_BY_NAME");

	@Autowired
	public ApplicationPropertiesDAO(DataSource dataSource) {
		super(dataSource);
	}

	private String getByName(final String name) {
		try {
			return jdbcTemplate.queryForObject(GET_BY_NAME, STRING_ROW_MAPPER, name);
		} catch (EmptyResultDataAccessException e) {
			throw new IllegalArgumentException("Application property " + name + " is missing");
		}
	}

	/**
	 * Cache properties for specified amount of time.
	 */
	private final CachingLoader<String, String> cache = new CachingLoader<String, String>(new Loader<String, String>() {
		@Override
		public String get(String key) {
			return getByName(key);
		}
	}, 10, null, true);

	/**
	 * Return a cached version of the property
	 */
	public String getProperty(String name) {
		return cache.get(name);
	}

	public boolean getBoolean(String name) {
		String val = cache.get(name);
		if ("true".equals(val))
			return true;
		if ("false".equals(val))
			return false;

		throw new IllegalArgumentException("Application property " + name + " is not a boolean (" + val + ")");
	}

	public int getInt(String name) {
		String val = cache.get(name);
		try {
			return Integer.parseInt(val);
		} catch (Exception e) {
			throw new IllegalArgumentException("Application property " + name + " is not an integer (" + val + ")");
		}
	}

	public Date getDate(String name) {
		String val = cache.get(name);
		try {
			java.util.Date date;
			synchronized (DATE_FORMAT) {
				date = DATE_FORMAT.parse(val);
			}
			return new Date(date.getTime());
		} catch (Exception e) {
			throw new IllegalArgumentException("Application property " + name + " is not a date (" + val + ")");
		}
	}

	public Timestamp getTimestamp(String name) {
		String val = cache.get(name);
		try {
			java.util.Date date;
			synchronized (DATETIME_FORMAT) {
				date = DATETIME_FORMAT.parse(val);
			}
			return new Timestamp(date.getTime());
		} catch (Exception e) {
			throw new IllegalArgumentException("Application property " + name + " is not a timestamp (" + val + ")");
		}
	}
}
