package com.shaklee.shared.validation;

import static com.shaklee.common.util.StringUtils.clean;

import java.util.regex.Pattern;

public class Validators extends com.shaklee.common.util.validation.Validators {

	private static Pattern SHAKLEE_ID_REGEX = Pattern.compile("[A-Z][A-Z]\\d\\d\\d\\d\\d");

	public static Pattern BANNED_PWD_REGEX = Pattern.compile("password|shaklee", Pattern.CASE_INSENSITIVE);

	public static void assertShakleeID(String value, String paramName, boolean required)
			throws InputValidationException {
		if (required && clean(value) == null)
			throw InputValidationException.oneFieldError(paramName + " is required", paramName, MISSING_ERROR);

		// not required
		if (value == null)
			return;

		if (SHAKLEE_ID_REGEX.matcher(value).matches() == false)
			throw InputValidationException.oneFieldTwoErrors("Invalid shaklee ID", paramName, INVALID_ERROR,
					"Shaklee ID must be uppercased with no whitespace");
	}

	public static String assertIsShakleeID(String value, String paramName, boolean required)
			throws InputValidationException {
		value = clean(value);
		if (required && value == null)
			throw InputValidationException.oneFieldError(paramName + " is required", paramName, MISSING_ERROR);

		if (value != null && SHAKLEE_ID_REGEX.matcher(value).matches() == false)
			throw InputValidationException.oneFieldErrors("Invalid shaklee ID", paramName, INVALID_ERROR,
					"Shaklee ID must be uppercased with no whitespace");

		return value;
	}

	/**
	 * Asset and convert boolean in one shot.
	 * 
	 * @throws InputValidationException
	 */
	public static Boolean assertBoolean(String value, String paramName, boolean required)
			throws InputValidationException {
		value = assertRequired(value, paramName, required);

		if (value == null)
			// not required and null
			return null;

		if ("true".equalsIgnoreCase(value))
			return true;

		if ("false".equalsIgnoreCase(value))
			return true;

		throw InputValidationException.oneFieldError(paramName + " should be true or false", paramName, INVALID_ERROR);
	}

	public static int assertIntegerRange(Integer value, int min, int max, String paramName)
			throws InputValidationException {
		assertRequired(value, paramName);

		if (value < min)
			throw InputValidationException.oneFieldError(paramName + " should be bigger than or equal to " + min,
					paramName, INVALID_ERROR);

		if (value >= max)
			throw InputValidationException.oneFieldError(paramName + " should be less than " + max, paramName,
					INVALID_ERROR);

		return value;
	}

	protected static String assertRequired(final String value, final String paramName, final boolean required)
			throws InputValidationException {
		if (required)
			return assertRequired(value, paramName);
		else
			return assertClean(value, paramName);
	}

	/**
	 * Checks that a value does not contain whitespace. Unlike other methods,
	 * this method will not return a cleaned string, since it is assumed that it
	 * must be clean or fail with an exception.
	 */
	public static void assertNoWhitespace(final String value, final String paramName) throws InputValidationException {
		if (hasWhitespace(value))
			throw InputValidationException.oneFieldError(paramName + " contains whitespace", paramName, INVALID_ERROR);
	}

	public static boolean isValidShakleeIDFormat(String shakleeId) {
		boolean result = false;
		if (shakleeId != null) {
			result = SHAKLEE_ID_REGEX.matcher(shakleeId).matches();
		}
		return result;
	}

	// public static String assertPassword(String value, String paramName,
	// boolean required) throws InputValidationException {
	// value = clean(value);
	// if (value == null) {
	// if (required)
	// throw InputValidationException.oneFieldError(paramName
	// + " is required", paramName, MISSING_ERROR);
	// else
	// // not required
	// return null;
	// }
	//
	// String error = isPassword(value);
	//
	// if (error != null)
	// throw InputValidationException.oneFieldTwoErrors(
	// "Invalid password", paramName, INVALID_ERROR, error);
	// return value;
	// }

	public static String isPassword(String value) {
		if (value.length() < 6)
			return "short";

		if (value.length() > 50)
			return "long";

		if (BANNED_PWD_REGEX.matcher(value).find())
			return "blacklisted";

		return validatePasswordText(value);
	}

	private static boolean hasWhitespace(String value) {
		final int length = value.length();
		for (int i = 0; i < length; i++) {
			char c = value.charAt(i);
			if (Character.isWhitespace(c))
				return true;
		}

		return false;
	}

	private static String validatePasswordText(String value) {
		final int length = value.length();
		boolean hasLower = false, hasUpper = false, hasNonLetter = false;

		for (int i = 0; i < length; i++) {
			char c = value.charAt(i);
			if (Character.isWhitespace(c))
				return "noWhitespace";
			if (Character.isLetter(c)) {
				// letter
				if (Character.isUpperCase(c))
					// upper
					hasUpper = true;
				else
					// lower
					hasLower = true;
			} else
				// not a letter
				hasNonLetter = true;
		}

		if (hasLower == false)
			return "noLower";
		if (hasUpper == false)
			return "noUpper";
		if (hasNonLetter == false)
			return "noNonLetter";

		return null;
		// return hasLower && hasUpper && hasNonLetter;
	}

	// private static boolean hasNonLetter(String value) {
	// final int length = value.length();
	// for (int i = 0; i < length; i++) {
	// char c = value.charAt(i);
	// if (Character.isLetter(c) == false)
	// return true;
	// }
	//
	// return false;
	// }
}
