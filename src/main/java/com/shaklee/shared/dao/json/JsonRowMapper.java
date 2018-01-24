package com.shaklee.shared.dao.json;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.core.RowMapper;

import com.shaklee.common.util.StringUtils;

/**
 * A row mapper that returns a row as json tree. The column names are converted
 * to json by naming convention:
 * 
 * # (pound) is used to separate parent and child json objects. _ (underscore)
 * is used to separate words inside one property name. This will result in camel
 * casing.
 * 
 * Example:
 * 
 * ABC#DEF_GHI_JKL#MNO
 * 
 * Will result in a json field
 * 
 * abc.defGhiJkl.mno
 * 
 * @author Elli Albek
 */
public class JsonRowMapper implements RowMapper<JSONObject> {

	private final Map<String, String[]> parsedPathCache = new ConcurrentHashMap<String, String[]>();

	@Override
	public JSONObject mapRow(final ResultSet rs, final int index)
			throws SQLException {
		final ResultSetMetaData meta = rs.getMetaData();
		final int cols = meta.getColumnCount();
		final JSONObject json = new JSONObject();

		try {
			for (int i = 1; i <= cols; i++) {
				String name = meta.getColumnName(i);
				Object value = normalize(name, rs.getObject(i));
				if (value != null) {
					String jsonPath[] = getJsonPath(name);
					set(json, value, jsonPath);
				}
			}
			return json;
		} catch (JSONException e) {
			throw new RuntimeException(e);
		}
	}

	public String[] getJsonPath(final String path) {
		String[] jsonPath = parsedPathCache.get(path);
		if (jsonPath == null) {
			jsonPath = createJsonPath(path);
			parsedPathCache.put(path, jsonPath);
		}

		return jsonPath;
	}

	protected String[] createJsonPath(final String path) {
		String[] jsonPath;
		jsonPath = getPath(path);
		lowerCase(jsonPath);
		String last = jsonPath[jsonPath.length - 1];
		if (last.endsWith("_boolean")) {
			jsonPath[jsonPath.length - 1] = last
					.substring(0, last.length() - 8);
		}
		return jsonPath;
	}

	public static Object normalize(String name, Object value) {
		if (value == null)
			return null;
		if (name.endsWith("BOOLEAN")) {
			if (value instanceof Number)
				return !((Number) value).equals(0);
			if (value instanceof String)
				return ("Y".equals(value));

			throw new InvalidDataAccessApiUsageException("Not a boolean: '"
					+ value + "'. Return 0/1 instead.");
		}
		if (value instanceof Number)
			return value;
		return StringUtils.clean(value.toString());
	}

	public static String[] getPath(String column) {
		ArrayList<String> path = new ArrayList<String>(3);
		int i = 0, j = column.indexOf('#');
		while (j > 0) {
			path.add(column.substring(i, j));
			i = j + 1;
			j = column.indexOf('#', i);
		}
		if (i < column.length())
			path.add(column.substring(i));

		return path.toArray(new String[path.size()]);
	}

	static void camelCase(String[] names) {
		for (int i = 0; i < names.length; i++) {
			names[i] = camelCase(names[i]);
		}
	}

	static void lowerCase(String[] names) {
		for (int i = 0; i < names.length; i++) {
			names[i] = names[i].toLowerCase();
		}
	}

	static String camelCase(String name) {
		final int len = name.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = name.charAt(i);
			if (c == '_')
				sb.append(Character.toUpperCase(name.charAt(++i)));
			else
				sb.append(Character.toLowerCase(c));
		}

		return sb.toString();
	}

	public static void set(JSONObject obj, Object value, String... path)
			throws JSONException {

		// optimize for common case
		if (path.length == 1) {
			obj.put(path[0], value);
			return;
		}

		JSONObject[] objectsPath = new JSONObject[path.length];
		objectsPath[0] = obj;

		for (int i = 0; i < path.length - 1; i++) {
			String key = path[i];
			JSONObject child = (JSONObject) obj.opt(key);
			if (child == null) {
				child = new JSONObject();
				obj.put(key, child);
			}
			// objectsPath.add(child);
			objectsPath[i + 1] = child;
			obj = child;
		}
		obj.put(path[path.length - 1], value);

		// bug workaround in JsonObject, reset from bottom of the tree to top
		// otherwise children dont get added
		// skip the last
		for (int i = objectsPath.length - 2; i >= 0; i--) {
			JSONObject parent = objectsPath[i], child = objectsPath[i + 1];
			parent.put(path[i], child);
		}
	}
}
