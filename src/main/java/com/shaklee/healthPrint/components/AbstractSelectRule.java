package com.shaklee.healthPrint.components;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.shaklee.promo.Condition;
import com.shaklee.promo.PromoRequest;
import com.shaklee.promo.basic.AbstractComponent;
import com.shaklee.shared.validation.InputValidationException;

@Component
public abstract class AbstractSelectRule<Q> extends AbstractComponent<Q> implements Condition<PromoRequest<Q>> {

	@NotNull
	public int[] value;

	@Override
	public boolean evaluate(PromoRequest<Q> q) throws InputValidationException {
		return checkValue(value, getValue(q.request));
	}

	public abstract int getValue(Q q);

	static boolean checkValue(int[] expected, int actual) {
		// has positions array, goal must be in a certain place in the input
		// array as dictated by positions.
		for (int i = 0; i < expected.length; i++) {
			if (expected[i] == actual)
				return true;
		}

		return false;
	}
}
