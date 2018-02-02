package com.shaklee.promo.basic;

import java.util.Map;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.shaklee.promo.PromoRequest;

/**
 * Promo response for an message action.
 * 
 * @author Elli Albek
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class PromoMessage implements PromoRequest.Action {
	public String key;
	public Map<String, Object> params;
}