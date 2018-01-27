package com.shaklee.shared.dao.json;

import static com.shaklee.shared.dao.json.ParsedQueryCache.createQuery;
import static com.shaklee.shared.dao.json.ParsedQueryCache.getParamValuesFromMultiMap;
import static com.shaklee.shared.util.StatusResponse.serverError;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.shaklee.common.util.ClasspathFileLoader;
import com.shaklee.shared.dao.json.ParsedQueryCache.ParsedQuery;
import com.shaklee.shared.util.StatusResponse;
import com.shaklee.shared.validation.InputValidationException;

/**
 * Model for web services that simply call SQL and return the results as json.
 * 
 * The service cleans the SQL, removing comments and adding " FOR READ ONLY"
 * when necessary.
 * 
 * @author Elli Albek
 */
public class JsonSqlServiceModel {

	private final Logger logger;

	public static final JsonRowMapper JSON_ROW_MAPPER = new JsonRowMapper();

	JdbcTemplate jdbct;
	final ClasspathFileLoader sqlFiles;
	final Map<String, ParsedQuery> queryCache = new ConcurrentHashMap<String, ParsedQuery>();

	public JsonSqlServiceModel(Class<?> clazz) {
		sqlFiles = new ClasspathFileLoader(clazz);
		logger = Logger.getLogger(clazz);
	}

	public JsonSqlServiceModel(Class<?> clazz, DataSource dataSource) {
		this(clazz);
		setDataSource(dataSource);
	}

	public void setDataSource(DataSource dataSource) {
		jdbct = new JdbcTemplate(dataSource);
	}

	/**
	 * Execute SQL and return results as JSON, including error handling in JSON.
	 */
	public JSONObject call(String name,
			final Map<String, List<String>> parameters) {
		try {
			return executeSql(name, parameters);
		} catch (InputValidationException e) {
			return new StatusResponse(StatusResponse.INPUT_VALIDATION_ERROR,
					e.getMessage()).toJSON();
		} catch (Throwable e) {
			logger.error("Query failed: " + name + ' ' + parameters, e);
			return serverError(stripRuntimeException(e).toString()).toJSON();
		}
	}

	/**
	 * Execute SQL and return results as JSON, including error handling in JSON.
	 */
	public JSONObject call(String name) {
		try {
			return executeSql(name);
		} catch (Throwable e) {
			logger.error("Query failed: " + name, e);
			return serverError(stripRuntimeException(e).toString()).toJSON();
		}
	}

	/**
	 * Raw execution of SQL that may throws various errors. This method does not
	 * handle exceptions.
	 */
	public JSONObject executeSql(String name,
			final Map<String, List<String>> parameters)
			throws InputValidationException, JSONException {
		final long time = System.currentTimeMillis();
		final ParsedQuery q = getQuery(name);
		String[] paramValues = getParamValuesFromMultiMap(parameters,
				q.parameters);

		final JSONObject result = queryForObject(q.sql, paramValues);
		result.put("time_ms", System.currentTimeMillis() - time);
		return result;
	}

	/**
	 * Raw execution of SQL that may throws various errors. This method does not
	 * pass any in parameters This method does not handle exceptions.
	 */
	public JSONObject executeSql(String name) throws JSONException {
		final long time = System.currentTimeMillis();
		final ParsedQuery q = getQuery(name);

		final JSONObject result = queryForList(q.sql);
		result.put("time_ms", System.currentTimeMillis() - time);
		return result;
	}

	/**
	 * Execute SQL and return results as JSNO, convert errors to JSON, and log
	 * in log file.
	 */
	public JSONObject callAndLog(String name,
			final Map<String, List<String>> parameters) {
		JSONObject r = call(name, parameters);
		if (logger.isDebugEnabled()) {
			try {
				Object time = r.opt("time_ms");
				if (time == null)
					time = "";
				else
					time = " " + time + "ms";

				logger.debug("SQL: " + name + ' ' + parameters + time + '\n'
						+ r.toString(2));
			} catch (JSONException e) {
				throw new RuntimeException(e);
			}
		}
		return r;
	}

	// if ("queryForList".equals(queryType)) {
	// List<JSONObject> r = jdbcTemplate.query(cleanQuery, paramValues,
	// JSON_ROW_MAPPER);
	// // add another level to the tree
	// if (targetParts.length > 2) {
	// // maps all individual items to json
	// HashMap<String, JSONObject> map = new HashMap<String, JSONObject>();
	// for (int i = 1; i < targetParts.length; i++) {
	// map.put(targetParts[i], r.get(i - 1));
	// }
	// // return new JSONObject(map);
	// JSONObject parent = new JSONObject();
	// parent.putAll(map);
	// // newParent.accumulate(targetParts[1], r);
	// // r = newParent;
	// return parent;
	// }
	// }

	/**
	 * standard query does not handle missing data.
	 */
	public JSONObject queryForObject(// final String[] targetParts,
			final String cleanQuery, final Object[] paramValues)
			throws JSONException {
		try {
			JSONObject data = jdbct.queryForObject(cleanQuery, paramValues,
					JSON_ROW_MAPPER);
			return success(data);
		} catch (EmptyResultDataAccessException e) {
			return new StatusResponse(StatusResponse.NOT_FOUND, "Not found")
					.toJSON();
		}
	}

	/**
	 * standard query does not handle missing & input data.
	 */
	public JSONObject queryForList(final String cleanQuery)
			throws JSONException {
		List<JSONObject> data = jdbct.query(cleanQuery, JSON_ROW_MAPPER);

		if (data.size() == 0)
			return new StatusResponse(StatusResponse.NOT_FOUND, "Not found")
					.toJSON();
		return StatusResponse.success().toJSON().put("data", data);
	}

	protected static JSONObject success(JSONObject data) throws JSONException {
		return StatusResponse.success().toJSON().put("data", data);
	}

	/**
	 * Remove a runtime exception wrapper around a read exception. Makes error
	 * logging shorter and more informative.
	 */
	public static Throwable stripRuntimeException(Throwable t) {
		if (t instanceof RuntimeException) {
			Throwable cause = t.getCause();
			if (cause != null)
				return cause;
		}

		return t;
	}

	private ParsedQuery getQuery(final String name) {
		ParsedQuery q = queryCache.get(name);
		if (q == null) {
			final String sql = sqlFiles.get(name + ".sql");
			q = createQuery(sql);
			queryCache.put(name, q);
		}
		return q;
	}

	public JSONObject callForList(String name,
			final Map<String, List<String>> parameters) {
		try {
			return executeSqlForList(name, parameters);
		} catch (InputValidationException e) {
			return new StatusResponse(StatusResponse.INPUT_VALIDATION_ERROR,
					e.getMessage()).toJSON();
		} catch (Throwable e) {
			logger.error("Query failed: " + name + ' ' + parameters, e);
			return serverError(stripRuntimeException(e).toString()).toJSON();
		}
	}

	public JSONObject queryForListWithParams(final String cleanQuery,
			final Object[] paramValues) throws JSONException {
		List<JSONObject> data = null;
		try {
			data = jdbct.query(cleanQuery, paramValues, JSON_ROW_MAPPER);
		} catch (EmptyResultDataAccessException e) {
			return new StatusResponse(StatusResponse.NOT_FOUND, "Not found")
					.toJSON();
		}
		if (data.size() == 0)
			return new StatusResponse(StatusResponse.NOT_FOUND, "Not found")
					.toJSON();
		return StatusResponse.success().toJSON().put("data", data);

	}

	public JSONObject executeSqlForList(String name,
			final Map<String, List<String>> parameters)
			throws InputValidationException, JSONException {
		final long time = System.currentTimeMillis();
		final ParsedQuery q = getQuery(name);
		String[] paramValues = getParamValuesFromMultiMap(parameters,
				q.parameters);

		final JSONObject result = queryForListWithParams(q.sql, paramValues);
		result.put("time_ms", System.currentTimeMillis() - time);
		return result;
	}

}
