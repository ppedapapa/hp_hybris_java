package com.shaklee.rulesets.healthQuestionaire.components;

import org.springframework.stereotype.Component;

import com.shaklee.promo.Condition;
import com.shaklee.shared.validation.InputValidationException;

@Component
public class Always implements Condition<Object> {

	public boolean blah;

	@Override
	public boolean evaluate(Object request) throws InputValidationException {
		return true;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}
}