package com.shaklee.shared.dao;

import static com.shaklee.shared.validation.Validators.INVALID_ERROR;
import static com.shaklee.shared.validation.Validators.MISSING_ERROR;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shaklee.common.util.StringUtils;
import com.shaklee.shared.validation.InputValidationException;

/**
 * A model for classes that need to log activity directly on the server without
 * going through a web service.
 * 
 * @author Elli Albek
 */
@Component
public class ActivityLog {
	// private static final Logger logger = Logger.getLogger(ActivityLog.class);

	private static final Pattern SHAKLEE_ID_PATTERN = Pattern
			.compile("[A-Z][A-Z][0-9][0-9][0-9][0-9][0-9]");
	@Autowired
	ActivityLoggingDAO dao;

	public void insert(String shakleeID, Long orderID, Integer autoshipID,
			String client_app, String component, String site, String event,
			String message, String jsonAttachement)
			throws InputValidationException {
		String order = null;
		if (orderID != null)
			order = String.valueOf(orderID);
		insert(shakleeID, order, autoshipID, client_app, component, site,
				event, message, jsonAttachement);
	}

	public void insert(String shakleeID, String orderID, Integer autoshipID,
			String client_app, String component, String site, String event,
			String message, String jsonAttachement)
			throws InputValidationException {
		// try {
		shakleeID = getShakleeID(shakleeID, "shaklee_id", false);

		client_app = getParam(client_app, "client_app",
				ActivityLoggingDAO.CLIENT_APPS);

		component = getParam(component, "component",
				ActivityLoggingDAO.COMPONENTS);

		event = getDBParam(event, "event", true);
		site = getLower(site, "site", false);
		message = getParam(message, "message", false);
		jsonAttachement = getJson(jsonAttachement, "json_attachement", false);

		dao.insert(shakleeID, orderID, autoshipID, client_app, component,
				event, site, message, jsonAttachement);
		// } catch (RuntimeException re) {
		// logger.error("Error inserting from " + client_app + ": "
		// + shakleeID + ", " + component + '/' + event + ", "
		// + message, re);
		// }
	}

	public static String getShakleeID(String value, String name,
			boolean required) throws InputValidationException {
		value = getParam(value, name, required);
		if (value != null
				&& SHAKLEE_ID_PATTERN.matcher(value).matches() == false) {
			throw singleError(name, INVALID_ERROR, value
					+ " is not a shaklee ID");
		}
		return value;
	}

	public static String getJson(String value, String name, boolean required)
			throws InputValidationException {
		value = getParam(value, name, required);
		if (value != null) {
			try {
				return new JSONObject(value).toString();
			} catch (JSONException e) {
				throw singleError(name, INVALID_ERROR,
						"Invalid JSON: " + e.getMessage());
			}
		}
		return value;
	}

	public static String getParam(String value, String name, boolean required)
			throws InputValidationException {
		final String s = StringUtils.normalizeEmptyAndWhitespace(value);
		if (required && s == null) {
			throw singleError(name, MISSING_ERROR, "Request parameter '" + name
					+ "' is required");
		}
		return s;
	}

	static InputValidationException singleError(String name, String errorType,
			String message) throws InputValidationException {
		Map<String, List<String>> errors = new HashMap<String, List<String>>();
		errors.put(name, Arrays.asList(errorType, message));
		throw new InputValidationException(message, errors);
	}

	public static String getDBParam(String value, String name, boolean required)
			throws InputValidationException {
		value = getParam(value, name, required);
		if (value == null)
			return null;

		String upper = value.toUpperCase();
		if (upper.equals(value))
			return value;

		throw singleError(name, INVALID_ERROR, name + " must be upper cased");
	}

	public static String getLower(String value, String name, boolean required)
			throws InputValidationException {
		value = getParam(value, name, required);
		if (value == null)
			return null;

		String lower = value.toLowerCase();
		if (lower.equals(value))
			return value;

		throw singleError(name, INVALID_ERROR, name + " must be lower cased");
	}

	public static String getParam(String value, String name,
			String... possibleValues) throws InputValidationException {
		final String s = getParam(value, name, true);

		for (int i = 0; i < possibleValues.length; i++) {
			String p = possibleValues[i];
			if (p.equals(s))
				return s;
		}
		// value not in list
		throw singleError(name, INVALID_ERROR, name + " must be one of "
				+ Arrays.asList(possibleValues));
	}
}
