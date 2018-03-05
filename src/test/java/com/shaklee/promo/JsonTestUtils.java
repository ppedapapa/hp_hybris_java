package com.shaklee.promo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shaklee.common.util.JsonLoader;
import com.shaklee.common.util.ClasspathFileLoader;

/**
 * Utilities to testing values in Json.
 * 
 * @author Elli Albek
 */
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/applicationContext.xml" })
public class JsonTestUtils {

	/**
	 * Check that a value equals, deep in the json tree.
	 */
	public static Object assertJson(JSONObject obj, Object value, String... keys) {
		Object actual = opt(obj, keys);
		if (actual instanceof BigDecimal || actual instanceof Double) {
			// special case for high precision floating point numbers
			assertEquals(keys[keys.length - 1], ((Number) value).doubleValue(), ((Number) actual).doubleValue(),
					0.0000000000000001);
		} else
			assertEquals(keys[keys.length - 1], value, actual);
		return actual;
	}

	/**
	 * Check that a value exists, deep in the json tree.
	 */
	public static Object assertFound(JSONObject obj, String... keys) {
		Object actual = opt(obj, keys);
		if (actual == null)
			fail("Cannot find value for \""+ StringUtils.join(keys, '.') + "\" in json");
		//assertNotNull(keys[keys.length - 1], actual);
		return actual;
	}

	public static Object opt(JSONObject obj, String... keys) {
		for (int i = 0; i < keys.length - 1; i++) {
			try {
				obj = obj.getJSONObject(keys[i]);
			} catch (JSONException e) {
				fail("Value for key \"" + keys[i] + "\" is missing or not a json object\n" + obj.toString());
			}
		}
		Object actual = obj.opt(keys[keys.length - 1]);
		return actual;
	}

	public static Object opt(Object json, Object... keys) {
		for (int i = 0; i < keys.length; i++) {
			try {
				if (json instanceof JSONObject)
					json = ((JSONObject) json).get(keys[i].toString());
				else
					json = ((JSONArray) json).get((Integer) keys[i]);
			} catch (JSONException e) {
				fail("missing key " + keys[i] + '\n' + json.toString());
			}
		}
		// Object actual = obj.opt(keys[keys.length - 1]);
		// return actual;
		return json;
	}

	/**
	 * Search for a path in the json. If the path does not fully exist in the
	 * document, return null. This is good for getting optional parts of a
	 * document.
	 */
	public static Object search(Object json, Object... keys) {
		for (int i = 0; i < keys.length; i++) {
			if (json instanceof JSONObject)
				json = ((JSONObject) json).opt(keys[i].toString());
			else
				json = ((JSONArray) json).opt((Integer) keys[i]);
			if (json == null)
				return null;
		}
		return json;
	}

	/**
	 * Check that a value equals, deep in the json tree.
	 */
	public static Object assertJson(JSONObject obj, Object value, Object... keys) {
		Object actual = assertFound(obj, keys);
		assertEquals(value, actual);
		return actual;
	}

	private static Object assertFound(final JSONObject obj, Object... keys) {
		Object child = obj;

		for (int i = 0; i < keys.length; i++) {
			Object key = keys[i];
			try {
				if (key instanceof Integer)
					child = ((JSONArray) child).get((Integer) key);
				else
					child = ((JSONObject) child).get(key.toString());
				// obj = obj.getJSONObject(keys[i]);
			} catch (JSONException e) {
				fail("missing key " + keys[i] + '\n' + obj.toString());
			}
		}
		return child;
	}

	public static void assertNotFound(final JSONObject obj, Object... path) {
		Object o;
		try {
			o = assertFound(obj, path);
		} catch (AssertionError e) {
			// expected, the path should be missing
			return;
		}
		fail("Found " + o + " in path " + Arrays.asList(path));
	}

	/**
	 * Check that a value is missing (immediate child).
	 */
	public static void assertMissing(JSONObject obj, String key) {
		Object child = obj.opt(key);
		assertNull(key, child);
	}

	public static <T> T loadJsonFile(String fileName, Class<T> targetClass, Class<?> callingClass, JsonLoader loader)
			throws IOException, InstantiationException, IllegalAccessException {
		String json = ClasspathFileLoader.load(fileName, callingClass);
		T data = targetClass.newInstance();
		loader.deserialize(data, json);
		return data;
	}
}
