package com.shaklee.rulesets.healthQuestionaire.components;

import org.springframework.stereotype.Component;

import com.shaklee.promo.Condition;
import com.shaklee.promo.PromoRequest;
import com.shaklee.promo.basic.AbstractComponent;
import com.shaklee.rulesets.healthQuestionaire.Questions;
import com.shaklee.shared.validation.InputValidationException;

@Component
public class Age extends AbstractComponent<Questions> implements Condition<PromoRequest<Questions>> {

	public Integer min, max;

	@Override
	public boolean evaluate(PromoRequest<Questions> q) throws InputValidationException {
		if (min == null && max == null)
			throw new InputValidationException("min or max cannot be null");
		
		if(q.request.age == null)
			return false;
		
		if (min != null && q.request.age < min)
			return false;

		if (max != null && q.request.age > max)
			return false;

		return true;
	}
}