package com.shaklee.shared.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.jdbc.core.SqlProvider;
import org.springframework.jdbc.core.SqlTypeValue;
import org.springframework.jdbc.core.StatementCreatorUtils;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.util.Assert;

import com.shaklee.common.util.CollectionUtils;
import com.shaklee.common.util.StringUtils;

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
public abstract class BaseJdbcTemplateDAO {

	protected final JdbcTemplate jdbcTemplate;

	public BaseJdbcTemplateDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * Return a single row as an object. In case of no match, return null (as
	 * opposed to the spring template that throws an exception)
	 */
	protected <T> T queryForObject(String sql, RowMapper<T> rm, Object... args) {
		try {
			return jdbcTemplate.queryForObject(sql, rm, args);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	/**
	 * Query that contain and IN clause with variable number of parameters. The
	 * syntax is IN(??), will be replaced with the correct number of in
	 * parameters. In parameters must be supplied in a java.util.Collection, as part
	 * of the args, in the correct location.
	 */
	protected <T> List<T> inQuery(String sql, RowMapper<T> rm, Object... args) {
		List<Object> expandedArgs = new ArrayList<Object>(32);
		for (Object arg : args) {
			if (arg instanceof Collection) {
				Collection<Object> l = (Collection<Object>) arg;
				// replace only the first occurrence, there may be more than one list.
				sql = org.apache.commons.lang3.StringUtils.replace(sql, "??",
						StringUtils.characterList('?', ',', l.size()).toString(), 1);
				expandedArgs.addAll(l);
			} else
				expandedArgs.add(arg);
		}

		return jdbcTemplate.query(sql, rm, expandedArgs.toArray());
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

	/**
	 * Batch update. The entire list is inserted as one batch.
	 */
	public <T> void batchUpdate(String sql, final List<T> list, ListBatchPreparedStatementSetter<T> setter) {
		if (list.isEmpty())
			return;
		ListSetterAdapter<T> adapter = new ListSetterAdapter<T>(list, setter);
		jdbcTemplate.batchUpdate(sql, adapter);
	}

	/**
	 * Batch update, where the max size of each batch is specified. If the list
	 * is bigger than that size, it is split to multiple batches that execute
	 * one after the other.
	 */
	public <T> void batchUpdate(String sql, int batchSize, final List<T> list,
			ListBatchPreparedStatementSetter<T> setter) {
		List<List<T>> batches = CollectionUtils.split(list, batchSize);

		for (List<T> batch : batches)
			batchUpdate(sql, batch, setter);
	}

	/**
	 * PreparedStatementCreator specific for insert statements.
	 */
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

	public static final ParameterizedRowMapper<String> STRING_ROW_MAPPER = new ParameterizedRowMapper<String>() {
		@Override
		public String mapRow(ResultSet rs, int i) throws SQLException {
			return rs.getString(1);
		}
	};

	protected static final ParameterizedRowMapper<Long> LONG_ROW_MAPPER = new ParameterizedRowMapper<Long>() {
		@Override
		public Long mapRow(ResultSet rs, int i) throws SQLException {
			return rs.getLong(1);
		}
	};

	protected static final ParameterizedRowMapper<Timestamp> TIMESTAMP_ROW_MAPPER = new ParameterizedRowMapper<Timestamp>() {
		@Override
		public Timestamp mapRow(ResultSet rs, int i) throws SQLException {
			return rs.getTimestamp(1);
		}
	};

	private static class ListSetterAdapter<T> implements BatchPreparedStatementSetter {

		private final List<T> list;
		private final ListBatchPreparedStatementSetter<T> setter;

		private ListSetterAdapter(List<T> list, ListBatchPreparedStatementSetter<T> setter) {
			this.list = list;
			this.setter = setter;
		}

		@Override
		public int getBatchSize() {
			return list.size();
		}

		@Override
		public void setValues(PreparedStatement ps, int i) throws SQLException {
			setter.setValues(ps, list.get(i));
		}
	}
}