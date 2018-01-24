package com.shaklee.rulesets.healthQuestionaire.components;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.shaklee.promo.Condition;
import com.shaklee.promo.PromoRequest;
import com.shaklee.promo.basic.AbstractComponent;
import com.shaklee.rulesets.healthQuestionaire.Questions;
import com.shaklee.shared.validation.InputValidationException;

@Component
public class NotPregnant extends AbstractComponent<Questions> implements Condition<PromoRequest<Questions>> {

	@NotNull
	public List<Questions.Pregnant> pregnant = Pregnant.IS_PREGNANT;

	@Override
	public boolean evaluate(PromoRequest<Questions> q) throws InputValidationException {
		return !pregnant.contains(q.request.pregnant);
	}
}