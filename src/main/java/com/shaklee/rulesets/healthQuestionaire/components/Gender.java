package com.shaklee.rulesets.healthQuestionaire.components;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.shaklee.promo.Condition;
import com.shaklee.promo.PromoRequest;
import com.shaklee.promo.basic.AbstractComponent;
import com.shaklee.rulesets.healthQuestionaire.Questions;
import com.shaklee.shared.validation.InputValidationException;

@Component
public class Gender  extends AbstractComponent<Questions> implements Condition<PromoRequest<Questions>> {
	
	@NotNull
	public com.shaklee.rulesets.healthQuestionaire.Questions.Gender gender;

	@Override
	public boolean evaluate(PromoRequest<Questions> q) throws InputValidationException {
		return gender == q.request.gender;
	}
}