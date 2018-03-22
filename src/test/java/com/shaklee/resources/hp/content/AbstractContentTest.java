package com.shaklee.resources.hp.content;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.sql.DataSource;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Assert;
import org.springframework.context.annotation.Bean;

import com.shaklee.DAO.UserDataStorageDAO;
import com.shaklee.DAOImpl.UserDAOImpl;
import com.shaklee.common.util.ClasspathFileLoader;
import com.shaklee.common.util.JsonLoader;
import com.shaklee.healthPrint.data.Bundle;
import com.shaklee.healthPrint.data.HealthPrintContentRequest;
import com.shaklee.healthPrint.data.SKU;
import com.shaklee.model.HealthQuestionnaireModel;
import com.shaklee.promo.PromoEngine;
import com.shaklee.promo.PromoRequest;
import com.shaklee.promo.basic.PromoMessage;
import com.shaklee.promo.impl.DefaultPromoDatabase;
import com.shaklee.promo.impl.PromoLoader;
import com.shaklee.promo.util.PromoJsonLoader;
import com.shaklee.resources.HealthQuestionnaireResource;
import com.shaklee.resources.HealthQuestionnaireResource.HQResponse;
import com.shaklee.rulesets.healthQuestionaire.HQService;
import com.shaklee.rulesets.healthQuestionaire.Questions;

/**
 * 
 * There are some test helper functions to help JUnit and integration test
 * cases.
 * 
 * Moreover, it has mock config class that mocks the actual implementation using
 * the Mockito mock objects.s
 *
 * @author ekoca
 */
public class AbstractContentTest {
	protected final static String SECTION_NAME = "EMRE";
	protected final static String[] KEYS = { "mock_message_key", "mock-exercise-frequency-0",
			"mock-exercise-intensity-0", "mock-energy-0" };

	/**
	 * Load JSON file to memory
	 * 
	 * @param fileName
	 * @param targetClass
	 * @param callingClass
	 * @param loader
	 * @return
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	protected <T> T loadJsonFile(String fileName, Class<T> targetClass, Class<?> callingClass, JsonLoader loader)
			throws IOException, InstantiationException, IllegalAccessException {
		String json = ClasspathFileLoader.load(fileName, callingClass);
		T data = targetClass.newInstance();
		loader.deserialize(data, json);
		return data;
	}

	/**
	 * Assert the lifestyle section that includes key, bullet keys and lifestyle
	 * score
	 * 
	 * @param response
	 * @param key
	 * @param bulletKeys
	 * @throws JSONException
	 */
	public void assertSectionAndKeys(JSONObject response, final String sectionName, final int score,
			final String[] keys) throws JSONException {
		final JSONObject section = assertSection(response, sectionName);
		final JSONObject params = assertContentKeys(section, keys);
		assertSectionScore(params, score);
	}

	/**
	 * Assert bmi section result that includes key, bullet keys, bmi score,
	 * min_desirable_weight and max_desirable_weight
	 * 
	 * @param response
	 * @param key
	 * @param score
	 * @param min
	 * @param max
	 * @throws JSONException
	 */
	public void assertBMI(JSONObject response, final String key, final double score, int min, int max)
			throws JSONException {
		JSONObject section = assertSection(response, "bmi");
		JSONObject params = assertContentKeys(section, key);
		assertSectionScore(params, score, min, max);
	}

	/**
	 * Assert the lifestyle section that includes key, bullet keys and lifestyle
	 * score
	 * 
	 * @param response
	 * @param key
	 * @param bulletKeys
	 * @throws JSONException
	 */
	public void assertLifeStyle(JSONObject response, final int score, final String key, final String... bulletKeys)
			throws JSONException {
		final JSONObject section = assertSection(response, "lifestyle");
		final JSONObject params = assertContentKeys(section, key, bulletKeys);
		assertSectionScore(params, score);
	}

	/**
	 * Assert the diet section that includes key, bullet keys and diet score
	 * 
	 * @param response
	 * @param key
	 * @param bulletKeys
	 * @throws JSONException
	 */
	public void assertDiet(JSONObject response, final int score, final String key, final String... bulletKeys)
			throws JSONException {
		final JSONObject section = assertSection(response, "diet");
		final JSONObject params = assertContentKeys(section, key, bulletKeys);
		assertSectionScore(params, score);
	}

	/**
	 * Assert true if there is a content result. Otherwise, fail.
	 * 
	 * @param JSONObject
	 *            response
	 * @throws JSONException
	 */
	protected void assertNoContent(JSONObject response) throws JSONException {
		try {
			response.getJSONObject("content");
			fail("Content found!");
		} catch (JSONException ex) {
		}
	}

	/**
	 * Assert true if there is a content result. Otherwise, fail.
	 * 
	 * @param JSONObject
	 *            response
	 * @throws JSONException
	 */
	protected JSONArray assertContent(JSONObject response) throws JSONException {
		try {
			return response.getJSONArray("content");
		} catch (JSONException ex) {
			fail("Content not found");
		}
		return null;
	}

	/**
	 * Assert each section of content scope like bmi, lifestyle or diet
	 * 
	 * @param JSONObject
	 *            response
	 * @param String
	 *            section
	 * @return JSONObject params
	 * @throws JSONException
	 */
	protected JSONObject assertSection(JSONObject response, final String section) throws JSONException {
		try {
			JSONArray contents = response.getJSONArray("content");
			for (int i = 0; i < contents.length(); i++) {
				JSONObject sec = contents.getJSONObject(i);
				JSONObject params = sec.getJSONObject("params");
				final String actualSection = params.getString("section");
				if (actualSection.equals(section)) {
					return sec;
				}
			}
		} catch (JSONException ex) {
			fail(section + " section not found");
		}
		return null;
	}

	/**
	 * Assert all the keys for content scope like header key and bullet keys
	 * 
	 * @param JSONObject
	 *            section
	 * @param String
	 *            key
	 * @param String[]
	 *            bulletKeys
	 * @return
	 * @throws JSONException
	 */
	protected JSONObject assertContentKeys(JSONObject section, final String[] keys) throws JSONException {
		return assertContentKeys(section, keys[0], keys[1], keys[2], keys[3]);
	}

	/**
	 * Assert all the keys for content scope like header key and bullet keys
	 * 
	 * @param JSONObject
	 *            section
	 * @param String
	 *            key
	 * @param String[]
	 *            bulletKeys
	 * @return
	 * @throws JSONException
	 */
	protected JSONObject assertContentKeys(JSONObject section, final String key, final String... bulletKeys)
			throws JSONException {
		final String actualKey = section.getString("key");
		Assert.assertEquals(key, actualKey);
		JSONObject params = section.getJSONObject("params");
		if (bulletKeys.length == 3) {
			JSONArray bullet_keys = params.getJSONArray("bullet_keys");
			for (int i = 0; i < bullet_keys.length(); i++) {
				final String bullet_key = bullet_keys.getString(i);
				Assert.assertEquals(bulletKeys[i], bullet_key);
			}
		}
		return params;
	}

	/**
	 * Assert given lifestyle or diet score
	 * 
	 * @param JSONObject
	 *            params
	 * @param int
	 *            score
	 * @throws JSONException
	 */
	protected void assertSectionScore(JSONObject params, int score) throws JSONException {
		assertSectionScore(params, score, 0, 0);
	}

	/**
	 * Assert given any section's score like bmi (float), lifestyle (int) or diet
	 * score (int)
	 * 
	 * @param JSONObject
	 *            params
	 * @param T
	 *            score
	 * @param int
	 *            min
	 * @param int
	 *            max
	 * @throws JSONException
	 */
	protected <T> void assertSectionScore(JSONObject params, T score, int min, int max) throws JSONException {
		@SuppressWarnings("unchecked")
		final T actualScore = (T) params.get("score");
		if (!score.equals(actualScore))
			fail("Expected score: " + score + " but got: " + actualScore + " for the section: "
					+ params.getString("section"));
		if (min > 0) {
			final int actualMin = params.getInt("min_desirable_weight");
			Assert.assertEquals(min, actualMin);
		}
		if (max > 0) {
			final int actualMax = params.getInt("max_desirable_weight");
			Assert.assertEquals(max, actualMax);
		}
	}

	/**
	 * Verify given key and value pair matches with actual result.
	 * 
	 * @deprecated please use assertSectionScore instead.
	 * 
	 * @param response
	 * @throws JSONException
	 */
	@SuppressWarnings("unused")
	@Deprecated
	protected void assertScoreKeyValue(JSONObject response, String key, float value) throws JSONException {
		JSONObject score = response.getJSONObject("score");
		float actualValue = (float) score.getDouble(key);
		if (value != actualValue)
			fail("Expected: " + value + " but got: " + actualValue + " for the given key: " + key);
	}

	/**
	 * Mock config
	 * 
	 * @author ekoca
	 *
	 */
	protected static class MockConfig {
		@Bean
		@SuppressWarnings("unchecked")
		public PromoEngine<PromoRequest<Questions>> getPromoEngine() {
			return mock(PromoEngine.class);
		}

		@Bean
		public HQService getHQService() {
			return mock(HQService.class);
		}

		@Bean
		public DataSource getDataSource() {
			return mock(DataSource.class);
		}

		@Bean
		public HealthQuestionnaireResource getHealthQuestionnaireResource() {
			return mock(HealthQuestionnaireResource.class);
		}

		@Bean
		public UserDAOImpl getUserDAOImpl() {
			return mock(UserDAOImpl.class);
		}

		@Bean
		public HealthQuestionnaireModel getHealthQuestionnaireModel() {
			return mock(HealthQuestionnaireModel.class);
		}

		@Bean
		public UserDataStorageDAO getUserDataStorageDAO() {
			return mock(UserDataStorageDAO.class);
		}

		@SuppressWarnings("unchecked")
		@Bean
		public DefaultPromoDatabase<Questions> getDefaultPromoDatabase() {
			return mock(DefaultPromoDatabase.class);
		}

		@SuppressWarnings("unchecked")
		@Bean
		public PromoLoader<PromoRequest<Questions>> getPromoLoader() {
			return mock(PromoLoader.class);
		}

		@Bean
		public JsonLoader getJsonLoader() {
			return mock(JsonLoader.class);
		}

		@Bean
		public PromoJsonLoader getPromoJsonLoader() {
			return mock(PromoJsonLoader.class);
		}
	}

	/**
	 * Don't chance this unless you make a actual changes in content provider such
	 * as implementation of content provider or its rules have been changed!
	 * 
	 * @return
	 */
	protected HQResponse mockHQResponse() {
		HQResponse response = new HQResponse(0);
		response.content = new ArrayList<>();
		response.content.add(createContentSection(SECTION_NAME));
		return response;
	}

	private PromoMessage createContentSection(String name) {
		PromoMessage mockMessage = new PromoMessage();
		mockMessage.key = KEYS[0];
		mockMessage.params = new HashMap<>();
		mockMessage.params.put("bullet_keys", Arrays.asList(KEYS[1], KEYS[2], KEYS[3]));
		mockMessage.params.put("section", name);
		mockMessage.params.put("score", 100);
		return mockMessage;
	}

	protected HealthPrintContentRequest<Questions, Bundle, SKU> mockHQRequest(Questions question) {
		return new HealthPrintContentRequest<>(question, null, null);
	}
}