package com.shaklee.promo.basic.message;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.shaklee.promo.PromoComponent;
import com.shaklee.promo.PromoRequest;
import com.shaklee.promo.basic.AbstractComponent;
import com.shaklee.promo.basic.MessageAction;
import com.shaklee.promo.basic.PromoActionUtils;
import com.shaklee.promo.basic.PromoMessage;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Message<T> extends AbstractComponent<T> implements PromoComponent<T>, MessageAction<T> {

	@NotNull
	public String key;
	public HashMap<String, Object> params;

	@Override
	public void exec(PromoRequest<T> req) {
		PromoMessage message = createPromoMessage(req);
		PromoActionUtils.addMessage(req, ruleset, message);
	}

	protected PromoMessage createPromoMessage(PromoRequest<T> req) {
		PromoMessage message = new PromoMessage();
		message.key = key;
		if (params != null && params.isEmpty() == false)
			message.params = (Map<String, Object>) params.clone();

		return message;
	}
}
