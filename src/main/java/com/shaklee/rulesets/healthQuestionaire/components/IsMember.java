package com.shaklee.rulesets.healthQuestionaire.components;

import org.springframework.stereotype.Component;

import com.shaklee.promo.Condition;
import com.shaklee.promo.PromoRequest;
import com.shaklee.promo.basic.AbstractComponent;
import com.shaklee.rulesets.healthQuestionaire.Questions;
import com.shaklee.shared.validation.InputValidationException;

@Component
public class IsMember extends AbstractComponent<Questions> implements Condition<PromoRequest<Questions>> {

	public boolean member = true;

	@Override
	public boolean evaluate(PromoRequest<Questions> q) throws InputValidationException {
		boolean notMember = !member(q.request);
		return member ^ notMember;
	}

	public boolean member(Questions q) {
		if (q.is_guest != null)
			return !q.is_guest;

		// legacy
		return q.user_id != null;
	}
}