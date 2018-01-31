package com.shaklee.promo;

import com.shaklee.shared.validation.InputValidationException;

/**
 * Evaluate a data object
 * 
 * @author Elli Albek
 */
public interface Action<T> {

	/**
	 * Execute a task on the request, after a rule was matched
	 */
	void exec(T t) throws InputValidationException;
}