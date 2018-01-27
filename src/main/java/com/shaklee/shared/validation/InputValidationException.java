package com.shaklee.shared.validation;

import static com.shaklee.common.util.CollectionUtils.addValues;
import static com.shaklee.common.util.CollectionUtils.createMultivaluedMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Indicates that an input field to a web service is missing.
 * 
 * @author Elli Albek
 */
public class InputValidationException extends Exception {

	public final Map<String, List<String>> errors;

	private static final long serialVersionUID = 1;

	public InputValidationException(String message,
			Map<String, List<String>> errors) {
		super(message);
		this.errors = errors;
	}

	public InputValidationException(String message) {
		this(message, null);
	}

	public static InputValidationException oneFieldError(String message,
			String field, String error) throws InputValidationException {
		Map<String, List<String>> errors = createMultivaluedMap(field, error);
		throw new InputValidationException(message, errors);
	}

	@Deprecated
	/**
	 * Use oneFieldErrors instead, this method contains a bug.
	 */
	public static InputValidationException oneFieldTwoErrors(String message,
			String field, String error1, String error2)
			throws InputValidationException {
		Map<String, List<String>> errors = createMultivaluedMap(field, error1,
				field, error2);
		throw new InputValidationException(message, errors);
	}

	public static InputValidationException oneFieldErrors(String message,
			String field, String... errors) throws InputValidationException {
		HashMap<String, List<String>> m = new HashMap<String, List<String>>(2);
		addValues(m, field, errors);

		throw new InputValidationException(message, m);
	}

	@Override
	public String toString() {
		return super.toString() + '\n' + errors;
	}
}