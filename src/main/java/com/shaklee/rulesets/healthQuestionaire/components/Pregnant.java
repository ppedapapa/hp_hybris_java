package com.shaklee.rulesets.healthQuestionaire.components;

import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.shaklee.promo.Condition;
import com.shaklee.promo.PromoRequest;
import com.shaklee.promo.basic.AbstractComponent;
import com.shaklee.rulesets.healthQuestionaire.Questions;
import com.shaklee.shared.validation.InputValidationException;

@Component
public class Pregnant extends AbstractComponent<Questions> implements Condition<PromoRequest<Questions>> {

	public static final List<Questions.Pregnant> IS_PREGNANT = Arrays.asList(Questions.Pregnant.PREGNANT,
			Questions.Pregnant.TRYING);

	@NotNull
	public List<Questions.Pregnant> pregnant = IS_PREGNANT;

	@Override
	public boolean evaluate(PromoRequest<Questions> q) throws InputValidationException {
		if (q.request.pregnant != null)
			return pregnant.contains(q.request.pregnant);

		return false;
	}
}