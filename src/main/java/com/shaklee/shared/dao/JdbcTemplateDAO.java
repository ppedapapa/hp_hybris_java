package com.shaklee.shared.dao;

import javax.sql.DataSource;

import com.shaklee.itrack.common.util.PropertiesObject;

/**
 * Base class for DAOs with some utilities.
 * 
 * When adding methods to this class keep it as similar as possible to its
 * origin, JdbcTemplate, including error handling. Since this class is generic
 * and used by many packages, it should be minimal and not contain any data
 * specific code.
 * 
 * @author Elli Albek
 */
public abstract class JdbcTemplateDAO extends BaseJdbcTemplateDAO {

	protected final PropertiesObject props;

	public JdbcTemplateDAO(DataSource dataSource) {
		super(dataSource);
		props = new PropertiesObject(getClass());
	}
}