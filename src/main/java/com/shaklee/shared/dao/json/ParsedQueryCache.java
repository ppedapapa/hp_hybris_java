package com.shaklee.shared.dao.json;

import static com.shaklee.itrack.common.util.ClasspathFileLoader.removeComments;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.shaklee.common.util.CollectionUtils;
import com.shaklee.shared.validation.InputValidationException;

/**
 * Cleans and parsed queries, returns the results.
 * 
 * Query format is:
 * 
 * select A from B where C=${param1}
 * 
 * or similar. ${param} are replaced with ? and the names are stored in the
 * parameters array.
 * 
 * @author Elli Albek
 */
@Component
public class ParsedQueryCache {

	private static final Pattern READ_ONLY_PATTERN = Pattern.compile(
			"\\s+for\\s+read\\s+only$", Pattern.MULTILINE
					| Pattern.CASE_INSENSITIVE);

	private static final Pattern PARAM_PATTERN = Pattern
			.compile("\\$\\{[_\\.a-zA-Z0-9]+\\}");

	final Map<String, ParsedQuery> queryCache = new ConcurrentHashMap<String, ParsedQuery>();

	public static class ParsedQuery {

		private ParsedQuery(String sql, String[] parameters) {
			this.sql = sql;
			this.parameters = parameters;
		}

		public final String sql;
		public final String[] parameters;
	}

	public ParsedQuery get(String sql) {
		ParsedQuery q = queryCache.get(sql);
		if (q == null) {
			q = createQuery(sql);
			queryCache.put(sql, q);
		}
		return q;
	}

	protected static ParsedQuery createQuery(String sql) {
		String clean = cleanSql(sql);
		clean = cleanQuery(clean);
		String[] parameters = getParams(sql);
		return new ParsedQuery(clean, parameters);
	}

	public static String[] getParams(String query) {
		ArrayList<String> params = new ArrayList<String>(2);
		Matcher m = PARAM_PATTERN.matcher(query);
		while (m.find()) {
			params.add(query.substring(m.start() + 2, m.end() - 1));
		}

		return params.toArray(new String[params.size()]);
	}

	public static String cleanQuery(String query) {
		return PARAM_PATTERN.matcher(query).replaceAll("?");
	}

	public static String cleanSql(String sql) {
		sql = removeComments(sql).trim();
		if (sql.regionMatches(true, 0, "select", 0, 6)
				&& (READ_ONLY_PATTERN.matcher(sql).find() == false))
			sql += "\nFOR READ ONLY";

		return sql;
	}

	public static String[] getParamValuesFromMultiMap(
			Map<String, List<String>> values, String[] paramNames)
			throws InputValidationException {
		String[] paramValues = new String[paramNames.length];
		for (int i = 0; i < paramNames.length; i++) {
			final String value = CollectionUtils
					.getFirst(values, paramNames[i]);
			if (value == null)
				throw new InputValidationException("Missing input parameter '"
						+ paramNames[i] + '\'');
			paramValues[i] = value;
		}

		return paramValues;
	}

	public static Object[] getParamValues(Map<String, Object> values,
			String[] paramNames) throws InputValidationException {
		Object[] paramValues = new Object[paramNames.length];
		for (int i = 0; i < paramNames.length; i++) {
			Object value = values.get(paramNames[i]);
			if (value == null)
				throw new InputValidationException("Missing input parameter '"
						+ paramNames[i] + '\'');
			paramValues[i] = value;
		}

		return paramValues;
	}
}