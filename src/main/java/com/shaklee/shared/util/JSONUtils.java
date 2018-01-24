package com.shaklee.shared.util;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * Utilities for standard json responses and errors from web services.
 * 
 * @author Elli Albek
 */
public class JSONUtils {

	/**
	 * Copy the "from" object on top of "to". JSONObject.NULL values in from
	 * will DELETE the same keys in "to".
	 * 
	 * @throws JSONException
	 */
	public static void copyInto(JSONObject from, JSONObject to) throws JSONException {
		for (Iterator<?> keys = from.keys(); keys.hasNext();) {
			final String key = keys.next().toString();
			final Object value = from.get(key);

			if (value == null)
				// no value
				continue;

			// not necessary, handled by simple put
			// if (value == JSONObject.NULL)
			// explicit null
			// to.remove(key);
			else if (value instanceof JSONObject) {
				// child object
				JSONObject fromChild = (JSONObject) value;
				JSONObject toChild = to.optJSONObject(key);
				if (toChild == null) {
					toChild = new JSONObject();
					to.put(key, toChild);
				}
				copyInto(fromChild, toChild);
			} else {
				// primitive
				to.put(key, value);
			}
		}
	}

	public static JSONObject fromMultiMap(Map<String, List<String>> multivaluedMap) {
		try {
			JSONObject j = new JSONObject();
			for (Entry<String, List<String>> value : multivaluedMap.entrySet()) {
				int size = value.getValue().size();
				if (size == 0)
					j.put(value.getKey(), JSONObject.NULL);
				else if (size == 1)
					j.put(value.getKey(), value.getValue().get(0));
				else
					// size > 1
					j.put(value.getKey(), value.getValue());
			}
			return j;
		} catch (JSONException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Convert a json object to order preserving map. Good for loading
	 * configuration which must preserve order
	 */
	public static Map<String, String> toMap(JSONObject j) throws JSONException {
		Map<String, String> m = new LinkedHashMap<String, String>();

		for (@SuppressWarnings("unchecked")
		Iterator<String> keys = j.keys(); keys.hasNext();) {
			String key = keys.next();
			String val = j.getString(key);
			m.put(key, val);
		}
		return m;
	}
}
