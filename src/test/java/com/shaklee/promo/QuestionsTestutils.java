package com.shaklee.promo;

import static com.shaklee.promo.JsonTestUtils.assertFound;
import static com.shaklee.promo.JsonTestUtils.assertJson;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.hamcrest.CoreMatchers;

import com.shaklee.common.util.JSONSerializer;
import com.shaklee.resources.HealthQuestionnaireResource;
import com.shaklee.resources.HealthQuestionnaireResource.DebugHQResponse;
import com.shaklee.promo.PromoRequest;
import com.shaklee.promo.PromoRequest.PromoAction;
import com.shaklee.promo.PromoTestUtils;
import com.shaklee.rulesets.healthQuestionaire.HQService;
import com.shaklee.rulesets.healthQuestionaire.Questions;
import com.shaklee.rulesets.healthQuestionaire.Questions.DietaryRestriction;
import com.shaklee.rulesets.healthQuestionaire.Questions.Gender;
import com.shaklee.rulesets.healthQuestionaire.Questions.HealthGoal;
import com.shaklee.rulesets.healthQuestionaire.components.SKUs;
import com.shaklee.healthPrint.data.Bundle;
import com.shaklee.shared.validation.InputValidationException;

import junit.framework.Assert;

public class QuestionsTestutils {

	public static PromoRequest<Questions> createQuestionsRequest(int age, Gender gender) {
		Questions q = createQuestions(age, gender);
		PromoRequest<Questions> request = new PromoRequest<Questions>(q, q);
		return request;
	}

	public static Questions createQuestions(int age, Gender gender) {
		Questions q = new Questions();
		q.age = age;
		q.country_code = "US";
		q.gender = gender;

		q.energy = 0;
		q.stress = 0;
		q.sleep = 0;
		q.memory = 0;
		q.exercise_frequency = 0;
		q.exercise_intensity = 0;

		q.water = 0;
		q.breakfast = 0;
		q.dairy = 0;
		q.fruits = 0;
		q.grains = 0;
		q.junk_food = 0;
		q.organic = 0;
		q.sugar_drinks = 0;
		q.vegetables = 0;

		q.toxins = 0;
		q.spending = 0;

		// q.price_tier = PriceTier.DN;
		return q;
	}

	/**
	 * Assert that the list actual contains all the values in expected. It may
	 * contain more.
	 */
	public static <T> void assertThatListContains(String message, JSONArray actual, String... expected) {
		List<String> s;
		try {
			s = toListOfString(actual, "sku");
			assertThatListContains(message, s, expected);
		} catch (JSONException e) {
			fail("Array does not contain strings " + actual.toString());
		}
	}

	public static <T> void assertThatListDoesNotContain(String message, JSONArray actual, String... expected) {
		List<String> s;
		try {
			s = toListOfString(actual, "sku");
			assertThatListDoesNotContain(message, s, expected);
		} catch (JSONException e) {
			fail("Array does not contain strings " + actual.toString());
		}
	}

	public static List<String> toListOfString(JSONArray a) throws JSONException {
		ArrayList<String> s = new ArrayList<String>(a.length());
		for (int i = 0; i < a.length(); i++)
			s.add(a.getString(i));
		return s;
	}

	public static List<String> toListOfString(JSONArray a, String key) throws JSONException {
		ArrayList<String> s = new ArrayList<String>(a.length());
		for (int i = 0; i < a.length(); i++)
			s.add(a.getJSONObject(i).getString(key));
		return s;
	}

	/**
	 * Assert that the list actual contains all the values in expected. It may
	 * contain more.
	 */
	public static <T> void assertThatListContains(Collection<T> actual, Collection<T> expected) {
		for (T val : expected) {
			assertThat("contains " + val, actual.contains(val), CoreMatchers.is(true));
		}
	}

	public static <T> void assertThatListContains(String message, Collection<T> actual, T... expected) {
		for (T val : expected) {
			assertTrue(message + " does not contain '" + val + '\'', actual.contains(val));
		}
	}

	public static <T> void assertThatListDoesNotContain(String message, Collection<T> actual, T... expected) {
		for (T val : expected) {
			assertTrue(message + " contains '" + val + '\'', !actual.contains(val));
		}
	}

	public static void assertSku(PromoRequest<Questions> response, Bundle bundle, String... expectedSku) {
		List<String> skus = getSkus(response, bundle);
		assertThatListContains(bundle + " skus", skus, expectedSku);
	}

	public static void assertSkuMissing(PromoRequest<Questions> response, Bundle bundle, String... expectedSku) {
		List<String> skus = getSkus(response, bundle);
		assertThatListDoesNotContain(bundle + " skus", skus, expectedSku);
	}

	private static List<String> getSkus(PromoRequest<Questions> response, Bundle bundle) {
		if (response.response == null)
			return Collections.emptyList();

		ArrayList<String> skus = new ArrayList<String>(4);
		for (PromoAction pa : response.response) {
			if (pa.actions == null) {
				//System.out.println(pa.promo_code);
				continue;
			}
			for (com.shaklee.promo.PromoRequest.Action action : pa.actions) {
				if (action instanceof SKUs) {
					SKUs addSku = (SKUs) action;
					if (addSku.bundle == bundle)
						if (addSku.sku != null)
							skus.addAll(addSku.sku);
				}
			}
		}

		return skus;
	}

	/**
	 * Search an array for a child object that has a certain value.
	 */
	public static JSONObject getByValue(JSONArray a, String name, String value) {
		for (int i = 0; i < a.length(); i++) {
			JSONObject child;
			try {
				child = a.getJSONObject(i);
			} catch (JSONException e) {
				fail("Not a json object in position " + i + '\n' + a.toString());
				return null;
			}
			String v;
			try {
				v = child.getString(name);
				if (value.equals(v))
					return child;
			} catch (JSONException e) {
				fail("missing key " + name + '\n' + child.toString());
			}
		}
		return null;
	}

	public static PromoRequest<Questions> runSimple(HQService service, PromoRequest<Questions> request)
			throws InputValidationException {
		PromoRequest<Questions> resp = service.runPromoEngine(request);
		System.out.println(JSONSerializer.toJacksonJaxbJson(resp, true));

		if (resp.log != null)
			for (Object log : resp.log)
				System.out.println(log);
		return resp;
	}

	public static JSONObject callQuestions(HealthQuestionnaireResource resource, Questions questions)
			throws InputValidationException {
		HealthQuestionnaireResource.HQResponse resp = resource.runQuestionnaire(questions);
		if (resp instanceof DebugHQResponse) {
			DebugHQResponse debug = (DebugHQResponse) resp;
			if (debug.debug instanceof List<?>) {
				List<?> log = (List<?>) debug.debug;
				for (Object match : log)
					System.out.println(match);
			}
		}

		JSONObject r = resp.toJSON();
		assertJson(r, 0, "status");
		return r;
	}

	public static void assertBundleContains(JSONObject response, Bundle bundle, String... skus) {
		JSONObject b = assertTier(response, bundle);
		assertThatListContains(bundle.name(), (JSONArray) assertFound(b, "skus"), skus);
	}

	public static JSONObject assertTier(JSONObject response, Bundle bundle) {
		JSONArray a = (JSONArray) assertFound(response, "bundles");
		JSONObject b = getByValue(a, "bundle", bundle.name());
		assertThat("Bundle " + bundle, b, not(nullValue()));
		return b;
	}

	public static void assertBundleMissing(JSONObject response, Bundle bundle, String... skus) {
		JSONArray a = (JSONArray) assertFound(response, "bundles");
		JSONObject b = getByValue(a, "bundle", bundle.name());
		if (b == null)
			return;
		// assertThat(b, not(nullValue()));
		assertThatListDoesNotContain(bundle.name(), (JSONArray) assertFound(b, "skus"), skus);
	}

	public static void assertBundleContainsSubSkus(JSONObject response, Bundle bundle, String sku,
			String subSkusExpected) throws JSONException {
		JSONArray a = (JSONArray) assertFound(response, "bundles");
		JSONObject b = getByValue(a, "bundle", bundle.name());
		// assertThat(b, not(nullValue()));

		JSONArray skus = (JSONArray) assertFound(b, "skus");
		assertThat(bundle + " sub skus", skus, not(nullValue()));
		JSONObject pack = getByValue(skus, "sku", sku);
		assertThat(sku, pack, not(nullValue()));

		JSONArray subSkuActual = (JSONArray) assertFound(pack, "sub_sku");
		assertStringArray(subSkuActual, subSkusExpected);
	}

	public static void assertStringArray(JSONArray a, String expected) throws JSONException {
		for (int i = 0; i < a.length(); i++) {
			String actual = a.getString(i);
			if (expected.equals(actual))
				return;

		}
		fail(a.toString() + " does not contain \"" + expected + '"');
	}

	public static void assertCategory(JSONObject response, Bundle bundle, String sku, String category)
			throws JSONException {
		JSONObject b = assertTier(response, bundle);
		final JSONArray skus = (JSONArray) assertFound(b, "skus");
		JSONObject item = findByProperty(skus, "sku", sku);
		assertJson(item, category, "category");
	}

	static JSONObject findByProperty(JSONArray array, String name, String value) throws JSONException {
		for (int i = 0; i < array.length(); i++) {
			JSONObject obj = array.getJSONObject(i);
			Object actual = obj.get(name);
			if (value.equals(actual))
				return obj;
		}
		return null;
	}

	public static Questions createContentQuestions(int age, Gender gender, int weight, int height) {
		Questions q = new Questions();
		q.age = age;
		q.country_code = "US";
		q.gender = gender;
		q.weight_lbs = weight;
		q.height_inches = height;
		createRestrictions(q);
		createGoals(q);
		// lifestyle
		q.energy = 0;
		q.stress = 0;
		q.sleep = 0;
		q.memory = 0;
		q.exercise_frequency = 0;
		q.exercise_intensity = 0;
		// diet
		q.fruits = 0;
		q.vegetables = 0;
		q.grains = 0;
		q.dairy = 0;
		q.healthy_fats = 0;
		q.water = 0;
		q.sugar_drinks = 0;
		q.junk_food = 0;
		q.breakfast = 0;
		// others
		q.organic = 0;
		q.toxins = 0;
		q.spending = 0;
		return q;
	}

	public static void createRestrictions(Questions q) {
		q.dietary_restrictions = new ArrayList<Questions.DietaryRestriction>(3);
		q.dietary_restrictions.add(DietaryRestriction.KOSHER);
		q.dietary_restrictions.add(DietaryRestriction.DAIRY);
		q.dietary_restrictions.add(DietaryRestriction.VEGETERIAN);
	}

	public static void createGoals(Questions q) {
		q.health_goals = new ArrayList<Questions.HealthGoal>(3);
		q.health_goals.add(HealthGoal.OVERALL);
		q.health_goals.add(HealthGoal.HEART);
		q.health_goals.add(HealthGoal.WEIGHT);
	}

	/**
	 * Verify if the bundle is empty or not
	 */
	public static JSONObject assertTierEmpty(JSONObject response, Bundle bundle) {
		JSONArray a = (JSONArray) assertFound(response, "bundles");
		JSONObject b = getByValue(a, "bundle", bundle.name());
		JSONArray skus = (JSONArray) assertFound(b, "skus");
		assertThat("Bundle " + bundle, skus.length(), CoreMatchers.is(0));
		return b;
	}

	/**
	 * Proxy method to {@link PromoTestUtils#assertMessage assertMessage}
	 */
	public static JSONObject assertMessageKey(final String promoCode, final String messageKey, JSONObject resp,
			int position) throws JSONException {
		return PromoTestUtils.assertMessage(promoCode, messageKey, resp, position);
	}

	/**
	 * @ekoca
	 * 
	 * 		Proxy method to {@link PromoTestUtils#assertMessage assertMessage}
	 */
	public static void assertMessageKeys(final String promoCode, JSONObject resp, String... expectedMessageKeys)
			throws JSONException {
		PromoTestUtils.assertMessages(promoCode, Arrays.asList(expectedMessageKeys), resp);
	}

	/**
	 * @ekoca
	 * 
	 * 		Proxy method to {@link PromoTestUtils#assertMessage assertMessage}
	 */
	public static void assertMessageKeysByOrder(final String promoCode, JSONObject resp, String... expectedMessageKeys)
			throws JSONException {
		PromoTestUtils.assertMessagesByOrder(promoCode, expectedMessageKeys, resp);
	}

	/**
	 * Proxy method to {@link PromoTestUtils#assertPromo assertPromo}
	 */
	public static JSONObject assertPromo(final String promoCode, final String messageKey, JSONObject resp)
			throws JSONException {
		return PromoTestUtils.assertPromo(resp, promoCode);
	}

	/**
	 * @ekoca
	 * 
	 * 		Proxy method to {@link PromoTestUtils#assertPromo assertPromo}
	 */
	public static void assertNoPromo(final String promoCode, JSONObject resp) throws JSONException {
		PromoTestUtils.assertNoPromo(resp, promoCode);
	}
}
