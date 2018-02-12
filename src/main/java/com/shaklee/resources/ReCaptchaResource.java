package com.shaklee.resources;

import javax.validation.constraints.NotNull;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shaklee.common.util.JSONSerializer;
import com.shaklee.common.util.validation.BeanValidator;
import com.shaklee.recaptcha.ReCaptcha;
import com.shaklee.shared.log.ActivityLogUtil;
import com.shaklee.shared.util.StatusResponse;
import com.shaklee.shared.validation.InputValidationException;

/**
 * a resource for Google reCAPTCHA web service
 * 
 * @author ekoca
 *
 */
@Path("/non-authenticated/recaptcha")
@Component
public class ReCaptchaResource {
	private static final Logger LOGGER = LoggerFactory.getLogger(ReCaptchaResource.class);

	@Autowired
	ReCaptcha reCaptcha;
	
	//@Autowired
	//ActivityLog activityLog;

	@POST
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public ReCaptchaResponse get() {
		return reCaptcha.get();
	}

	@POST
	@Path("/validate")
	@Produces(MediaType.APPLICATION_JSON)
	public ReCaptchaResponse validate(ReCaptchaRequest recaptcha) throws InputValidationException {
		_logRequest(recaptcha);
		// input validation
		BeanValidator.INSTANCE.assertValid(recaptcha);
		return reCaptcha.validate(recaptcha);
	}

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	public static class ReCaptchaRequest {
		@NotNull
		public String email;
		@NotNull
		public String recaptcha_response;
	}

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	public static class ReCaptchaResponse extends StatusResponse {
		public ReCaptchaData data;

		public ReCaptchaResponse(int status) {
			super(status);
		}

		public static ReCaptchaResponse success() {
			return new ReCaptchaResponse(SUCCESS);
		}

		public static ReCaptchaResponse validationFailed() {
			return new ReCaptchaResponse(RECAPTCHA_VALIDATION_FAILED);
		}

		public static ReCaptchaResponse verified() {
			ReCaptchaResponse response = ReCaptchaResponse.success();
			response.data = new ReCaptchaData();
			response.data.recaptcha_verified = true;
			return response;
		}

		public static ReCaptchaResponse notVerified() {
			ReCaptchaResponse response = ReCaptchaResponse.validationFailed();
			response.data = new ReCaptchaData();
			response.data.recaptcha_verified = false;
			return response;
		}
	}

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	public static class ReCaptchaData {
		public String public_key;
		public Boolean recaptcha_verified;
	};

	private void _logRequest(final Object obj) {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug(JSONSerializer.toJacksonJaxbJson(obj, true));
	}

	void logActivity(String userId, String eventType, String message, Object request, Object response)
			throws InputValidationException {
		try {
			String json = ActivityLogUtil.toJson(request, response);
			//activityLog.insert(userId, (String) null, null, "UNKNOWN", "HQ", null, eventType, message, json);
		} catch (Exception e) {
			LOGGER.error("HQ Activity log failed", e);
		}
	}
}
