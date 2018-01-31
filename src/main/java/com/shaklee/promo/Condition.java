package com.shaklee.promo;

import com.shaklee.shared.validation.InputValidationException;

/**
 * Evaluate a data object. This is the Rule part of the promo engine.
 * 
 * @author Elli Albek
 */
public interface Condition<T> {

	/**
	 * Evaluate the rule on the cart and return true or false. It is assumed
	 * that rules cannot fail. Throw an exception on an error, to stop the
	 * engine immediately.
	 */
	boolean evaluate(T t) throws InputValidationException;
}