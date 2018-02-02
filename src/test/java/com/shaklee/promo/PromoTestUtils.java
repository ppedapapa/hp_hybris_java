package com.shaklee.promo;

import static com.shaklee.promo.JsonTestUtils.assertFound;
import static com.shaklee.promo.JsonTestUtils.assertJson;
import static com.shaklee.promo.JsonTestUtils.opt;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;

import com.shaklee.resources.PromoResource;
import com.shaklee.promo.RandomValues;
import com.shaklee.promo.basic.cart.Cart;
import com.shaklee.promo.basic.cart.Cart.Item;
import com.shaklee.promo.CartUtils;
import com.shaklee.promo.JsonTestUtils;
import com.shaklee.shared.validation.InputValidationException;

/**
 * Promo test utilities, mostly data cleanup.
 * 
 * @author Elli Albek
 * @author ekoca
 */
public class PromoTestUtils {

	public static final String TEST_USER = "BF30492";
	public static final String COUNTRY_US = "US";
	public static final String COUNTRY_CA = "CA";
	public static final long DAY_MS = 24l * 60 * 60 * 1000;

	public static void deleteTracking(JdbcTemplate jdbct, String shakleeId) {
		jdbct.update("DELETE FROM ICSHKTST.PROMO_TRACKING where shaklee_id=?", shakleeId);
	}

	public static void deleteTrackingByEmail(JdbcTemplate jdbct, String email) {
		jdbct.update("DELETE FROM ICSHKTST.PROMO_TRACKING where email=?", email);
	}

	public static void deleteFromUserGroup(JdbcTemplate jdbct, String shakleeId) {
		jdbct.update("DELETE FROM ICSHKTST.PROMO_USER_GROUP where shaklee_id=?", shakleeId);
	}

	public static void insertToUserGroup(JdbcTemplate jdbct, int groupId, String shakleeId) {
		jdbct.update("INSERT INTO PROMO_USER_GROUP (GROUP_ID, SHAKLEE_ID ) VALUES (?,?)", groupId, shakleeId);
	}

	public static void insertToUserGroup(JdbcTemplate jdbct, int groupId, String shakleeId, String extra) {
		jdbct.update("INSERT INTO PROMO_USER_GROUP (GROUP_ID, SHAKLEE_ID, EXTRA_1) VALUES (?,?,?)", groupId, shakleeId,
				extra);
	}

	public static void activatePromo(JdbcTemplate jdbct, String condition) {
		jdbct.update("UPDATE PROMO SET ACTIVE = 1 WHERE " + condition);
	}

	public static void deactivatePromo(JdbcTemplate jdbct, String condition) {
		jdbct.update("UPDATE PROMO SET ACTIVE = 0 WHERE " + condition);
	}

	

	public static void printRuleMatches(JSONObject resp) throws JSONException {
		String s = resp.getString("debug");
		JSONArray a = new JSONArray(s);
		System.out.println(a.toString(2));
	}

	/**
	 * Call the promo engine and assert that a message exists
	 */
	public static JSONObject callMessage(PromoResource resource, Cart cart, String code, String messageKey,
			final String time) throws InputValidationException, JSONException {
		JSONObject resp = resource.runPromoEngine(cart, time).toJSON();
		return assertMessage(code, messageKey, resp);
	}

	public static JSONObject callNoPromo(PromoResource resource, Cart cart, String code, final String time)
			throws InputValidationException {
		JSONObject resp = resource.runPromoEngine(cart, time).toJSON();

		JSONArray data = resp.optJSONArray("data");
		if (data == null)
			return resp;

		for (int i = 0; i < data.length(); i++) {
			String pc = (String) opt(data, i, "promo_code");
			assertThat(pc, not(code));
		}
		return resp;
	}

	/**
	 * Test that no promos are matched.
	 */
	public static JSONObject callNoPromos(PromoResource resource, Cart cart, final String time)
			throws InputValidationException {
		JSONObject resp = resource.runPromoEngine(cart, time).toJSON();

		JSONArray data = resp.optJSONArray("data");
		if (data == null)
			return resp;

		fail("Expected no matches, but found " + data.length() + " matched");
		return resp;
	}

	public static JSONObject assertMessage(String code, String messageKey, JSONObject resp) throws JSONException {
		JSONObject promo = assertPromo(resp, code);
		assertMessage(messageKey, promo);
		return promo;
	}

	/**
	 * @ekoca
	 * 
	 * 		Assert message on specific position
	 */
	public static JSONObject assertMessage(String code, String messageKey, JSONObject resp, int position)
			throws JSONException {
		JSONObject promo = assertPromo(resp, code);
		assertMessage(messageKey, promo, position);
		return promo;
	}

	/**
	 * @ekoca
	 * 
	 * 		Assert more than one messages in response JSON
	 */
	public static void assertMessages(String code, List<String> expectedMessageKeys, JSONObject resp)
			throws JSONException {
		JSONObject promo = assertPromo(resp, code);
		JSONArray messages = (JSONArray) promo.get("messages");
		for (int mi = 0; mi < messages.length(); mi++) {
			JSONObject msg = messages.getJSONObject(mi);
			String key = (String) assertFound(msg, "key");
			if (!expectedMessageKeys.contains(key))
				fail("Response contains message " + key
						+ ".However, the key was not found in given expected message list! "
						+ expectedMessageKeys.toString());
		}
	}

	/**
	 * @ekoca
	 * 
	 * 		Assert more than one messages in response JSON
	 */
	public static void assertMessagesByOrder(String code, String[] expectedMessageKeys, JSONObject resp)
			throws JSONException {
		JSONArray messages = (JSONArray) PromoTestUtils.assertPromo(resp, code).get("messages");
		for (int i = 0; i < expectedMessageKeys.length && i < messages.length(); i++) {
			JSONObject msg = messages.getJSONObject(i);
			String key = (String) assertFound(msg, "key");
			assertEquals(expectedMessageKeys[i], key);
		}
	}

	public static void assertMessageInAllPromos(String messageKey, JSONObject resp) throws JSONException {
		JSONArray data = (JSONArray) assertFound(resp, "data");
		for (int i = 0; i < data.length(); i++) {
			JSONObject promo = data.getJSONObject(i);
			JSONArray messages = (JSONArray) opt(promo, "messages");
			if (messages != null)
				for (int mi = 0; mi < messages.length(); mi++) {
					JSONObject msg = messages.getJSONObject(mi);
					String key = (String) assertFound(msg, "key");
					if (messageKey.equals(key))
						// match
						return;
				}
		}

		fail("Response does not contain message key " + messageKey);
	}

	public static void assertNoMessage(String messageKey, JSONObject resp) throws JSONException {
		try {
			assertMessageInAllPromos(messageKey, resp);
		} catch (AssertionError e) {
			// expected
			return;
		}
		fail("Response contains message " + messageKey);
	}
	/**
	public static void assertMessage(String messageKey, JSONObject promo) {
		assertJson(promo, messageKey, "messages", 0, "key");
	}
	*/
	public static JSONObject assertMessage(String messageKey, JSONObject promo) {
		JSONArray messages = (JSONArray) JsonTestUtils.assertFound(promo, "messages");

		for (int i = 0; i < messages.length(); i++) {
			Object key = JsonTestUtils.search(messages, i, "key");
			if (messageKey.equals(key))
			{
				return (JSONObject)JsonTestUtils.search(messages, i);
			}
		}
		fail("Response does not contain message "+ messageKey);
		// will not happen
		return null;
	}
	
	public static void assertMessage(String messageKey, JSONObject promo, int position) {
		assertJson(promo, messageKey, "messages", position, "key");
	}

	public static JSONObject assertPromo(JSONObject resp, String code) throws JSONException {
		JSONArray data = (JSONArray) JsonTestUtils.assertFound(resp, "data");
		for (int i = 0; i < data.length(); i++) {
			Object promoCode = JsonTestUtils.opt(data, i, "promo_code");
			if (code.equals(promoCode))
				return data.getJSONObject(i);
		}
		fail("Response does not contain promo " + code);
		return null;
	}

	public static JSONObject assertAction(JSONObject promo, String actionClass) throws JSONException {
		JSONArray actions = (JSONArray) JsonTestUtils.assertFound(promo, "actions");
		for (int i = 0; i < actions.length(); i++) {
			Object action = JsonTestUtils.opt(actions, i, "@class");
			if (actionClass.equals(action))
				return actions.getJSONObject(i);
		}
		fail("Promo " + promo.opt("promo_code") + " does not contain action " + actionClass);
		return null;
	}

	public static JSONObject callOnePromo(PromoResource resource, Cart cart, String code, String time)
			throws InputValidationException, JSONException {
		JSONObject resp = resource.runPromoEngine(cart, time).toJSON();
		assertOnePromo(resp, code);
		return resp;
	}

	public static JSONObject assertOnePromo(JSONObject resp, String code) throws JSONException {
		JSONArray data = (JSONArray) JsonTestUtils.assertFound(resp, "data");
		Object promoCode = JsonTestUtils.opt(data, 0, "promo_code");
		if (code.equals(promoCode))
			return data.getJSONObject(0);
		fail("Response does not contain promo " + code);
		return null;
	}

	public static void assertNoPromo(JSONObject resp, String code) {
		JSONArray data = (JSONArray) resp.optJSONArray("data");
		if (data == null)
			return;
		for (int i = 0; i < data.length(); i++) {
			String promoCode = (String) JsonTestUtils.opt(data, i, "promo_code");
			assertThat(promoCode, is(not(code)));
		}
	}

	/**
	 * Call the promo engine and assert that a message exists
	 */
	public static JSONObject callAction(PromoResource resource, Cart cart, String code, String actionClass,
			final String time) throws InputValidationException, JSONException {
		JSONObject resp = resource.runPromoEngine(cart, time).toJSON();

		// debug info
		JSONArray debug = new JSONArray(resp.getString("debug"));
		System.out.println(debug.toString(2));

		resp = assertPromo(resp, code);
		assertAction(resp, actionClass);
		return resp;
	}

	public static JSONObject callEmptyAction(PromoResource resource, Cart cart, String code, String messageKey,
			final String time) throws InputValidationException, JSONException {
		JSONObject resp = callAction(resource, cart, code, "com.shaklee.promo.basic.cart.EmptyAction", time);
		assertMessage(messageKey, resp);
		return resp;
	}

	/**
	 * Remove promos from results
	 */
	public static void deletePromo(JSONObject response, String... code) throws InputValidationException, JSONException {
		if (code.length == 0)
			return;

		Collection<String> exclude = Arrays.asList(code);
		JSONArray data = response.optJSONArray("data");
		JSONArray replace = new JSONArray();
		if (data == null)
			return;

		for (int i = 0; i < data.length(); i++) {
			String pc = (String) opt(data, i, "promo_code");
			if (!exclude.contains(pc)) {
				replace.put(data.get(i));
			}
		}

		response.put("data", replace);
	}

	/**
	 * Call the promo engine and assert that a single action exists
	 */
	public static JSONObject callOneAction(PromoResource resource, Cart cart, String code, String actionClass,
			final String time, String... exclude) throws InputValidationException, JSONException {
		JSONObject resp = resource.runPromoEngine(cart, time).toJSON();
		assertJson(resp, code, "data", 0, "promo_code");
		assertJson(resp, actionClass, "data", 0, "actions", 0, "@class");

		deletePromo(resp, exclude);
		// actions come first
		assertJson(resp, code, "data", 0, "promo_code");
		JSONArray actions = (JSONArray) opt(resp, "data", 0, "actions");
		assertEquals("Number of actions", 1, actions.length());
		return resp;
	}

	public static Item addItem(Cart cart, String sku, int quantity, String item_price, boolean is_autoship,
			int frequency) {
		Item i = new Item();
		i.sku = sku;
		i.quantity = quantity;
		i.item_price = new BigDecimal(item_price);
		i.frequency = frequency;
		addItem(cart, is_autoship, i);
		return i;
	}

	public static void addItem(Cart cart, boolean is_autoship, Item i) {
		if (is_autoship)
			cart.authship_cart = addItem(cart.authship_cart, i);
		else
			cart.cart = addItem(cart.cart, i);
	}

	public static List<Item> addItem(List<Item> items, Item item) {
		if (items == null)
			items = new ArrayList<Cart.Item>();
		items.add(item);
		return items;
	}

	public static Item getItem(Cart cart, String sku) {
		final List<Item> items = CartUtils.allItems(cart);
		if (items == null)
			return null;
		for (Item i : items) {
			if (sku.equals(i.sku))
				return i;
		}
		return null;
	}

	public static void makeAutoship(Cart cart, Item item) {
		cart.authship_cart.add(item);
		cart.cart.remove(item);
	}

	public static void makeNonAutoship(Cart cart, Item item) {
		cart.cart.add(item);
		cart.authship_cart.remove(item);
	}
}
