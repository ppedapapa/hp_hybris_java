package com.shaklee.promo.basic;

import com.shaklee.common.util.JSONSerializer;
import com.shaklee.promo.PromoComponent;
import com.shaklee.promo.RuleSet;

/**
 * Abstract cloneable component that contains ruleset information.
 * 
 * @author ekoca
 */
public class AbstractComponent<T> implements PromoComponent<T>, Cloneable {

	protected RuleSet<T> ruleset;

	@SuppressWarnings("unchecked")
	@Override
	public PromoComponent<T> clone(RuleSet<T> ruleset) {
		AbstractComponent<T> other;
		try {
			other = (AbstractComponent<T>) super.clone();
			other.ruleset = ruleset;
		} catch (CloneNotSupportedException e) {
			throw new Error(e);
		}

		return other;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + JSONSerializer.toJacksonJaxbJson(this, false);
	}
}