package com.shaklee.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.jdbc.core.SqlProvider;
import org.springframework.jdbc.core.SqlTypeValue;
import org.springframework.jdbc.core.StatementCreatorUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.util.Assert;

public class BaseJDBCTemplateDAO {
	
	@Autowired
    public JdbcTemplate jdbcTemplate;
	
	private static class SimplePreparedStatementCreator implements PreparedStatementCreator, SqlProvider {

		private final String sql;
		private final Object[] args;

		public SimplePreparedStatementCreator(String sql, Object... args) {
			Assert.notNull(sql, "SQL must not be null");
			this.sql = sql;
			this.args = args;
		}

		public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
			final PreparedStatement ps = con.prepareStatement(this.sql, Statement.RETURN_GENERATED_KEYS);
			setValues(ps);
			return ps;
		}

		public String getSql() {
			return this.sql;
		}

		public void setValues(PreparedStatement ps) throws SQLException {
			if (this.args != null) {
				for (int i = 0; i < this.args.length; i++) {
					Object arg = this.args[i];
					doSetValue(ps, i + 1, arg);
				}
			}
		}

		protected void doSetValue(PreparedStatement ps, int parameterPosition, Object argValue) throws SQLException {
			if (argValue instanceof SqlParameterValue) {
				SqlParameterValue paramValue = (SqlParameterValue) argValue;
				StatementCreatorUtils.setParameterValue(ps, parameterPosition, paramValue, paramValue.getValue());
			} else {
				StatementCreatorUtils.setParameterValue(ps, parameterPosition, SqlTypeValue.TYPE_UNKNOWN, argValue);
			}
		}
	}
	
	/**
	 * Performs an insert to a table that has auto generated key, and return the
	 * new key. Supports insert of ONE record at a time.
	 */
	protected long insert(String sql, Object... params) {
		GeneratedKeyHolder key = new GeneratedKeyHolder();
		int inserts = jdbcTemplate.update(new SimplePreparedStatementCreator(sql, params), key);
		if (inserts == 0)
			throw new EmptyResultDataAccessException("Insert did not create any rows\n" + sql, inserts);

		if (inserts != 1)
			throw new IncorrectResultSizeDataAccessException(
					"Insert for a single row created " + inserts + " rows\n" + sql, 1, inserts);

		return key.getKey().longValue();
	}

}
