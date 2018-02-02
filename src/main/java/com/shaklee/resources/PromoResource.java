package com.shaklee.resources;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.shaklee.common.util.JSONSerializer;
import com.shaklee.common.util.validation.BeanValidator;
import com.shaklee.common.util.UTCDateUtils;
import com.shaklee.promo.PromoRequest;
import com.shaklee.promo.PromoRequest.PromoAction;
import com.shaklee.promo.PromoService;
import com.shaklee.promo.basic.cart.Cart;
import com.shaklee.promo.impl.DefaultPromoDatabase;
import com.shaklee.shared.util.StatusResponse;
import com.shaklee.shared.validation.InputValidationException;

/**
 * Promo engine web services. Wrap the actual PromoService class with a web
 * service interface.
 * 
 * @author Elli Albek
 */
@Path("/promos")
@Repository
public class PromoResource {

	private static final Logger logger = LoggerFactory.getLogger(PromoResource.class);

	/**
	 * Hard coded time overide from properies file, optional.
	 */
	@Value("${promos.time}")
	static Long TIME_OVERRIDE;

	@Autowired
	PromoService<Cart> service;

	@Autowired
	DefaultPromoDatabase<?> db;

	//@Autowired
	//PromoTrackingDAO trackingDAO;

	/**
	 * Calculates promotions on a shopping cart.
	 * 
	 * @param time
	 *            A time override for debugging, allows testing in the past or
	 *            in the future (for promos that did not start yet or have
	 *            already finished).
	 * 
	 * @return PromoResponse with all the matched promotions.
	 */
	@POST
	@Path("/calc")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML })
	@Produces(MediaType.APPLICATION_JSON)
	public StatusResponse runPromoEngine(Cart cart, @QueryParam("time") String time) throws InputValidationException {
		if (logger.isDebugEnabled()) {
			String msg = time == null ? "Request (System Time):\n" : "Request: " + time + "\n";
			logger.debug(msg + JSONSerializer.toJacksonJaxbJson(cart, true));
		}
		final long start = System.currentTimeMillis();
		try {

			// input validation
			BeanValidator.INSTANCE.assertValid(cart);

			Long now = TIME_OVERRIDE;
			if (time != null)
				now = parseTime(time, "time").getTime();

			PromoRequest<Cart> req = new PromoRequest<Cart>(cart, cart, now);
			req = service.runPromoEngine(req);
			String message = "No promos matched";
			if (req.response != null)
				message = "Matched " + req.response.size() + " promos";

			DebugPromoResponse response = new DebugPromoResponse(message);
			response.data = req.response;
			response.ms = System.currentTimeMillis() - start;
			response.debug = req.log;

			// Date d = new Date(now);
			// logger.debug("Now: "+ d.getTimezoneOffset());
			if (logger.isDebugEnabled())
				logger.debug("Response:\n" + JSONSerializer.toJacksonJaxbJson(response, true));
			return response;
		} catch (InputValidationException e) {
			// handled by the framework.
			throw e;
		} catch (Throwable e) {
			String msg = "Promo engine crashed for user " + cart.user_id;
			try {
				String json = JSONSerializer.toJacksonJaxbJson(cart, true);
				msg = msg + " cart:\n" + json;
			} catch (Exception e1) {
				logger.error("Error generating json from cart (for logging purpose)", e1);
				// add the error string to the m
				msg = msg + " cart: " + e1.toString();
			}

			logger.error(msg, e);

			return StatusResponse.serverError(toString("Promo Engine crashed.", e).toString());
		}
	}

	
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	public static class PromoResponse extends StatusResponse {

		public List<PromoAction> data;

		public PromoResponse(String message) {
			super(SUCCESS, message);
		}
	}

	/**
	 * Adds debug information to PromoResponse. This class is necessary due to
	 * restrictions in auto schema generation that do not allow Object in json
	 * schema.
	 * 
	 * @author Elli Albek
	 */
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	static class DebugPromoResponse extends PromoResponse {

		public Object debug;
		public long ms;

		public DebugPromoResponse(String message) {
			super(message);
		}
	}

	static Date parseTime(String time, String fieldName) throws InputValidationException {
		if (time == null)
			return null;

		try {
			return UTCDateUtils.parseUTCDate(time);
		} catch (Exception e) {
			throw InputValidationException.oneFieldError(e.getMessage(), fieldName, UTCDateUtils.DATETIME_FORMAT);
		}
	}

	public static StringBuilder toString(CharSequence s, Throwable cause) {
		StringBuilder sb = new StringBuilder();
		sb.append(s).append('\n');
		return toString(sb, cause);
	}

	static StringBuilder toString(StringBuilder sb, Throwable cause) {
		sb.append(cause.getClass().getSimpleName()).append(": ").append(cause.getMessage());

		while ((cause = cause.getCause()) != null) {
			sb.append('\n').append(cause.getClass().getSimpleName()).append(": ").append(cause.getMessage());
		}

		return sb;
	}
}
