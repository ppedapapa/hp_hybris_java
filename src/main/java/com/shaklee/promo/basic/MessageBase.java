package com.shaklee.promo.basic;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.shaklee.promo.PromoComponent;
import com.shaklee.promo.PromoRequest;
import com.shaklee.promo.RuleSet;

/**
 * Message object is an action. This implementation can be loaded from DB.
 * 
 * @author Elli Albek
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public abstract class MessageBase<T> extends HashMap<String, Object> implements PromoComponent<T>, MessageAction<T> {

	private static final String KEY_PARAM = "key";

	private static final long serialVersionUID = 1L;

	@NotNull
	public String key;

	protected RuleSet<T> ruleset;

	public MessageBase() {
		super(4);
	}

	public MessageBase(Map<? extends String, ? extends String> m) {
		super(m);
		setKey(this);
	}

	@Override
	public Object put(String k, Object v) {
		if (k.equals(KEY_PARAM) && v != null)
			return key = v.toString();
		else
			return super.put(k, v);
	}

	@Override
	public void putAll(Map<? extends String, ? extends Object> m) {
		setKey(m);
		super.putAll(m);
	}

	protected Map<? extends String, ? extends Object> setKey(Map<? extends String, ? extends Object> m) {
		Object k = m.remove(KEY_PARAM);
		if (k != null)
			key = k.toString();
		return m;
	}

	@Override
	public void exec(PromoRequest<T> req) {
		PromoMessage message = createPromoMessage(req);
		PromoActionUtils.addMessage(req, ruleset, message);
	}

	protected PromoMessage createPromoMessage(PromoRequest<T> req) {
		PromoMessage message = new PromoMessage();
		message.key = key;
		if (!isEmpty())
			message.params = new HashMap<String, Object>(this);
		return message;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PromoComponent<T> clone(RuleSet<T> ruleset) {
		MessageBase<T> other = (MessageBase<T>) super.clone();
		other.ruleset = ruleset;
		return other;
	}
}