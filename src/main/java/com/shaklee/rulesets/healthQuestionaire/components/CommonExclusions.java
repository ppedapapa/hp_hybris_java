package com.shaklee.rulesets.healthQuestionaire.components;

import org.springframework.stereotype.Component;

import com.shaklee.promo.Condition;
import com.shaklee.promo.PromoRequest;
import com.shaklee.promo.basic.AbstractComponent;
import com.shaklee.rulesets.healthQuestionaire.Questions;
import com.shaklee.shared.validation.InputValidationException;

/**
 * Common exclusions that are shared among all goals.
 * 
 * @author Elli Albek
 */
@Component
public class CommonExclusions extends AbstractComponent<Questions> implements Condition<PromoRequest<Questions>> {

	public boolean blah;
	
	private Kosher kosher;
	private Pregnant pregnant;

	public CommonExclusions() {
		kosher = new Kosher();
		pregnant = new Pregnant();
	}

	@Override
	public boolean evaluate(PromoRequest<Questions> q) throws InputValidationException {

		if (kosher.evaluate(q))
			return false;

		if (pregnant.evaluate(q))
			return false;

		return true;
	}
}