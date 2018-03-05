package com.shaklee.recaptcha;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.methods.HttpUriRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.shaklee.common.util.ExceptionUtils.FatalServiceRuntimeException;
import com.shaklee.common.util.JSONSerializer;
import com.shaklee.resources.ReCaptchaResource.ReCaptchaData;
import com.shaklee.resources.ReCaptchaResource.ReCaptchaRequest;
import com.shaklee.resources.ReCaptchaResource.ReCaptchaResponse;
//import com.shaklee.shared.dao.ActivityLog;
import com.shaklee.shared.oauth.CommonHttpClient;
import com.shaklee.shared.oauth.CommonHttpClient.SimpleResponse;
import com.shaklee.shared.util.StatusResponse;
import com.shaklee.shared.validation.InputValidationException;

/**
 * A simple model for Google ReCaptcha service.
 * 
 * @author ekoca <ekoca@shaklee.com>
 *
 */
@Component
@PropertySource(value = "classpath:props/recaptcha.properties")
public class ReCaptcha {
	private static final Logger LOGGER = LoggerFactory.getLogger(ReCaptcha.class);
	/**
	 * Connections constants can be overridden from property file.
	 */
	@Value("${recaptcha.site.key}")
	String siteKey;
	
	@Value("${recaptcha.secret.key}")
	String secretKey;
	
	@Value("${recaptcha.endpoint.url}")
	String endPoint;

	@Autowired
	public CommonHttpClient client;

	//@Autowired
	//ActivityLog activityLog;

	/**
	 * Returns reCAPTCHA's site-key for client-side integration
	 * 
	 * @return ReCaptchaResponse
	 */
	@Deprecated
	public ReCaptchaResponse get() {
		ReCaptchaResponse response = ReCaptchaResponse.success();
		response.data = new ReCaptchaData();
		response.data.public_key = siteKey;
		//_logErrorToActivityLog("ReCaptcha GET service service is deprecated so it should not be called from anywhere!",	null);
		return response;
	}

	/**
	 * reCAPTCHA server side integration; when users submit the form where you
	 * integrated reCAPTCHA, you'll get as part of the payload a string with the
	 * name "recaptcha_response". In order to check whether Google has verified
	 * that user, this send a POST request with these parameters to Google to do
	 * a server side validation
	 * 
	 * Use generic {@link #assertReCaptchaVerified(String, String)
	 * assertReCaptchaVerified} method to do a validation on user response by
	 * ignoring all the exceptions silently
	 * 
	 * @see {@link #assertVerified(String, String) assertVerified}
	 * @return ReCaptchaResponse
	 * @throws ReCaptchaServiceException
	 */
	public ReCaptchaResponse validate(ReCaptchaRequest request) {
		SimpleResponse<String> reCaptchaReply = runRequest(request);
		return translateReCaptchaReply(reCaptchaReply, request);
	}

	/**
	 * Runs reCapthca server side validation. Silently handles all the
	 * exceptions thrown by ReCaptcha. For instance; if the request fails due to
	 * Google reCaptcha service is unavailable, client could continue to process
	 * without failing
	 * 
	 * @param String
	 *            email
	 * @param String
	 *            recaptchaResponse
	 * @throws ReCaptchaVerificationFailedException
	 * @throws FatalServiceRuntimeException
	 */
	public void assertVerified(ReCaptchaRequest request, final boolean failSliently)
			throws ReCaptchaVerificationFailedException {
		try {
			ReCaptchaResponse response = validate(request);
			if (response.status == StatusResponse.RECAPTCHA_VALIDATION_FAILED && !response.data.recaptcha_verified) {
				// reCapthca verification failed
				throw new ReCaptchaVerificationFailedException("Google reCaptha could not verify user");
			}
		} catch (FatalServiceRuntimeException e) {
			// Fatal, non recoverable error due to a bug in our code or
			// something went very wrong with Google reCapthca API.
			if (!failSliently) {
				throw e;
			}
		}
	}

	/**
	 * This method send a POST request with these parameters to Google to do a
	 * server side validation
	 * 
	 * @param ReCaptchaRequest
	 * @return SimpleResponse<String>
	 * @throws FatalServiceRuntimeException
	 */
	private SimpleResponse<String> runRequest(ReCaptchaRequest request) {
		try {
			HttpUriRequest httpRequest = createPostRequest(request);
			return client.textRespose(httpRequest);
		} catch (IOException e) {
			throw new FatalServiceRuntimeException("Error in sending a POST request", e);
		}
	}

	/**
	 * Create a HTTP request with the required parameters to do a server side
	 * validation
	 * 
	 * @param ReCaptchaRequest
	 * @return HttpUriRequest
	 */
	private HttpUriRequest createPostRequest(ReCaptchaRequest request) {
		Map<String, String> query = new HashMap<String, String>();
		query.put("secret", secretKey);
		query.put("response", request.recaptcha_response);
		return CommonHttpClient.postRequest(endPoint, query);
	}

	/**
	 * This method translate Google reply into classic ITRACK status response.
	 * 
	 * @param SimpleResponse<String>
	 * @return ReCaptchaResponse
	 * @throws ReCaptchaServiceException
	 * @throws InputValidationException
	 * @throws FatalServiceRuntimeException
	 */
	private ReCaptchaResponse translateReCaptchaReply(final SimpleResponse<String> reCaptchaReply,
			ReCaptchaRequest request) {
		// valid response from Google reCaptca service
		if (reCaptchaReply.status == HttpStatus.OK.value()) {
			Map<String, Object> replyMap = covertJsonToMap(reCaptchaReply.body);
			Object success = replyMap.get("success");
			// verified
			if (success != null && (Boolean) success) {
				return ReCaptchaResponse.verified();
			}
			// not verified
			// log first
			if (reCaptchaReply != null && request != null) {
				final String csRequestJson = JSONSerializer.toJacksonJaxbJson(request, false);
				final String csResponseJson = JSONSerializer.toJacksonJaxbJson(reCaptchaReply, false);
				final String json = "{\"recapthca_request\":" + csRequestJson + ",\n" + "\"recapthca_reply\":"
						+ csResponseJson + "\n}";
				//_logErrorToActivityLog("ReCaptcha validation failed", json);
			}
			// return
			return ReCaptchaResponse.notVerified();
		}
		throw new FatalServiceRuntimeException(
				"Something went wrong with Google reCaptcha service; HTTP status: " + reCaptchaReply.status);
	}

	/**
	 * Converts Google reply (JSON) to object map
	 * 
	 * @param SimpleResponse<String>
	 * @return Map<String, Object>
	 * @throws FatalServiceRuntimeException
	 */
	@SuppressWarnings("unchecked")
	private Map<String, Object> covertJsonToMap(final String responseBody) {
		Map<String, Object> replyMap;
		try {
			ObjectMapper mapper = new ObjectMapper();
			replyMap = mapper.readValue(responseBody, Map.class);
		} catch (Exception e) {
			throw new FatalServiceRuntimeException(e.getMessage(), e);
		}

		if (replyMap == null) {
			final String message = "ReCapthca Error in converting json to Object map";
			//_logErrorToActivityLog(message, responseBody);
			throw new FatalServiceRuntimeException(message);
		}
		return replyMap;
	}

	/**
	 * Log the Google ReCaptcha response
	 * 
	 * @param httpResponse
	 * @throws InputValidationException
	 */
	/*private void _logErrorToActivityLog(final String message, final String jsonAttachement) {
		try {
			activityLog.insert(null, (String) null, null, "UNKNOWN", "CAPTCHA", null, "VALIDATION", message,
					jsonAttachement);
		} catch (Exception e) {
			LOGGER.error(message, e);
		}
	}*/

	/**
	 * Fatal, non recoverable error. It might be bug in our code or something
	 * went wrong with the Google ReCaptcha API.
	 * 
	 * @author Emre Koca
	 */
	public static class ReCaptchaVerificationFailedException extends Exception {

		private static final long serialVersionUID = 1L;

		public ReCaptchaVerificationFailedException(String message) {
			super(message);
		}

		public ReCaptchaVerificationFailedException(String message, Throwable cause) {
			super(message, cause);
		}

		public ReCaptchaVerificationFailedException(Throwable cause) {
			super(cause.getMessage(), cause);
		}
	}
}
