package com.shaklee.rulesets.healthQuestionaire.components;

import org.springframework.stereotype.Component;

import com.shaklee.promo.Condition;
import com.shaklee.promo.PromoRequest;
import com.shaklee.promo.basic.AbstractComponent;
import com.shaklee.rulesets.healthQuestionaire.Questions;
import com.shaklee.shared.validation.InputValidationException;

/**
 * A combined condition to simplify weight loss conditions.
 * 
 * @author Elli Albek
 */
@Component
public class Exercise extends AbstractComponent<Questions> implements Condition<PromoRequest<Questions>> {

	public int frequecy = 3, intensity = 3;

	@Override
	public boolean evaluate(PromoRequest<Questions> q) throws InputValidationException {
		return q.request.exercise_frequency >= frequecy && q.request.exercise_intensity >= intensity;
	}
}