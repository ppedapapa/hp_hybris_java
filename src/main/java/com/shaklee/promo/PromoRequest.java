package com.shaklee.promo;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Simple holder for entire request processing data, which includes an object
 * for the incoming data and a list of response objects.
 * 
 * @author Elli Albek
 */
public class PromoRequest<T> implements RequestLog {

	/** Time in ms **/
	public final Long now;
	public final T request;
	public List<PromoAction> response;
	public List<Object> log;
	
	public LocalizedData promoGroupIdentifier;
	
	
	public PromoRequest(T request, LocalizedData groupIdentifier, Long time) {
		this.request = request;
		this.now = time;
		this.promoGroupIdentifier = groupIdentifier;
	}

	public PromoRequest(T request, LocalizedData groupIdentifier) {
		this(request, groupIdentifier, null);
	}

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	public static class PromoAction {
		public int promo_id;
		public int ruleset_id;
		/** Promo code that is sent to SDS, OFFER_CODE in DB2 **/
		public String promo_code;

		@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
		public List<Action> actions;

		@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
		public List<Action> messages;
	}

	public static interface Action {
	}

	@Override
	public void debug(Object message) {
		if (log == null)
			log = new ArrayList<Object>(4);
		log.add(message);
	}
}
