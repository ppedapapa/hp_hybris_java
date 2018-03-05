package com.shaklee.promo.impl;

import java.util.List;
import java.util.Map;

import com.shaklee.common.util.CollectionUtils;
import com.shaklee.common.util.StringUtils;
import com.shaklee.common.util.validation.Validators;;

/**
 * Indicates that an input field to a web service is missing.
 * 
 * @author Elli Albek
 */
public class InputValidationRuntimeException extends RuntimeException {

	public final Map<String, List<String>> errors;

	private static final long serialVersionUID = 1;

	public InputValidationRuntimeException(String message, Map<String, List<String>> errors) {
		super(message);
		this.errors = errors;
	}

	public InputValidationRuntimeException(String message) {
		this(message, null);
	}

	public static InputValidationRuntimeException oneFieldError(String message, String field, String error)
			throws InputValidationRuntimeException {
		Map<String, List<String>> errors = CollectionUtils.createMultivaluedMap(field, error);
		throw new InputValidationRuntimeException(message, errors);
	}

	@Override
	public String toString() {
		return super.toString() + '\n' + errors;
	}

	public static String assertRequired(String value, String paramName) throws InputValidationRuntimeException {
		final String clean = StringUtils.clean(value);
		if (clean == null)
			throw oneFieldError(paramName + " is required", paramName, Validators.MISSING_ERROR);

		// not null, should not have leading / trailing whitespace
		if (clean.length() != value.length())
			throw oneFieldError(paramName + " contains whitespace", paramName, Validators.INVALID_ERROR);

		return clean;
	}
}