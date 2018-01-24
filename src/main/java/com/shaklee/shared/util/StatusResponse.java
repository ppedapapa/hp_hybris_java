package com.shaklee.shared.util;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.shaklee.common.util.JSONSerializer;

/**
 * Basic standard web service response with status code.
 * 
 * @author Elli Albek
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class StatusResponse {

	/** Generic codes that apply to all services **/
	public static final int SUCCESS = 0, INPUT_VALIDATION_ERROR = 9997,
			NOT_FOUND = 9996, SERVER_ERROR = 9999, EXISTING_CONTANT = 9995,
			BACKEND_SERVICE_ERROR = 9994, RECAPTCHA_VALIDATION_FAILED = 9993;

	public final int status;
	public String message;

	@SuppressWarnings("unused")
	private StatusResponse() {
		this(0);
	}

	public StatusResponse(int status) {
		this.status = status;
	}

	public StatusResponse(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public static StatusResponse success() {
		return new StatusResponse(SUCCESS);
	}

	public static StatusResponse serverError() {
		return new StatusResponse(SERVER_ERROR);
	}

	@SuppressWarnings("unchecked")
	public static <T extends StatusResponse> T serverError(String message) {
		return (T) new StatusResponse(SERVER_ERROR, message);
	}

	public static <T extends StatusResponse> T dataNotFound(String message) {
		return (T) new StatusResponse(NOT_FOUND, message);
	}

	public static StatusResponse existingContact(String message) {
		return new StatusResponse(EXISTING_CONTANT, message);
	}

	public static StatusResponse inputValidationException(String message) {
		return new StatusResponse(INPUT_VALIDATION_ERROR, message);
	}

	public StatusResponse message(String message) {
		this.message = message;
		return this;
	}

	/**
	 * This simulates the actual code that is used inside Jersey to generate
	 * json from the object. This guarantees the same generation mechanism, as
	 * different object to json conversion classes normally produce slightly
	 * different results.
	 * 
	 * Indent is added for readability, since it will only be used in testing.
	 */
	@Override
	public String toString() {
		return JSONSerializer.toJacksonJaxbJson(this, true);
	}

	public JSONObject toJSON() {
		try {
			return new JSONObject(toString());
		} catch (JSONException e) {
			throw new RuntimeException(e);
		}
	}
}