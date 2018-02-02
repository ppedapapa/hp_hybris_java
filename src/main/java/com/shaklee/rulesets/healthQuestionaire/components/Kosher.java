package com.shaklee.rulesets.healthQuestionaire.components;

import static com.shaklee.rulesets.healthQuestionaire.components.DieraryRestriction.hasDietaryRestriction;

import org.springframework.stereotype.Component;

import com.shaklee.promo.Condition;
import com.shaklee.promo.PromoRequest;
import com.shaklee.promo.basic.AbstractComponent;
import com.shaklee.rulesets.healthQuestionaire.Questions;
import com.shaklee.rulesets.healthQuestionaire.Questions.DietaryRestriction;
import com.shaklee.shared.validation.InputValidationException;

/**
 * Added specific rule for kosher since this is the most widely used dietary
 * restriction.
 * 
 * @author Elli Albek
 */
@Component
public class Kosher extends AbstractComponent<Questions> implements Condition<PromoRequest<Questions>> {

	public boolean kosher = true;

	@Override
	public boolean evaluate(PromoRequest<Questions> t) throws InputValidationException {
		if (kosher)
			return hasDietaryRestriction(t, DietaryRestriction.KOSHER);
		else
			return !hasDietaryRestriction(t, DietaryRestriction.KOSHER);
	}
}