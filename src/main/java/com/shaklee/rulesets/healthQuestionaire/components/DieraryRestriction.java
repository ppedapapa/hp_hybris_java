package com.shaklee.rulesets.healthQuestionaire.components;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.shaklee.promo.Condition;
import com.shaklee.promo.PromoRequest;
import com.shaklee.promo.basic.AbstractComponent;
import com.shaklee.rulesets.healthQuestionaire.Questions;
import com.shaklee.rulesets.healthQuestionaire.Questions.DietaryRestriction;

@Component
public class DieraryRestriction extends AbstractComponent<Questions> implements Condition<PromoRequest<Questions>> {

	public boolean restriction = true;

	@NotNull
	public DietaryRestriction diet;

	@Override
	public boolean evaluate(PromoRequest<Questions> q) {
		if (restriction)
			return hasDietaryRestriction(q, diet);
		else
			return !hasDietaryRestriction(q, diet);
	}

	static boolean hasDietaryRestriction(PromoRequest<Questions> q, DietaryRestriction diet) {
		if (q.request.dietary_restrictions != null)
			return q.request.dietary_restrictions.contains(diet);

		return false;
	}
}