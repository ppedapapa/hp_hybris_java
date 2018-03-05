package com.shaklee.common.util.validation;

import static com.shaklee.common.util.StringUtils.clean;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.shaklee.shared.validation.InputValidationException;

public class Validators {

	public static final String MISSING_ERROR = "_MISSING", INVALID_ERROR = "_INVALID";

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

	public static Pattern EMAIL_REGEX = Pattern.compile(EMAIL_PATTERN);

	public static String assertRequired(String value, String paramName) throws InputValidationException {
		final String clean = assertClean(value, paramName);

		if (clean == null)
			throw InputValidationException.oneFieldError(paramName + " is required", paramName, MISSING_ERROR);

		return clean;
	}

	public static Object assertRequired(Object value, String paramName) throws InputValidationException {
		if (value instanceof String)
			return assertRequired((String) value, paramName);

		if (value == null)
			throw InputValidationException.oneFieldError(paramName + " is required", paramName, MISSING_ERROR);

		return value;
	}

	public static void assertMissing(Object value, String paramName) throws InputValidationException {
		if (value != null)
			throw InputValidationException.oneFieldError(paramName + " should not be included", paramName,
					INVALID_ERROR);
	}

	public static void assertEquals(Object value, Object expected, String paramName) throws InputValidationException {
		if (value == null && expected == null)
			// both null (equals)
			return;
		if (value != null && expected != null && value.equals(expected))
			// equals
			return;

		// make sure not to include actual data in the error since those end up
		// in the client, log files, etc
		throw InputValidationException.oneFieldError(paramName + " does not match expected value", paramName,
				INVALID_ERROR);
	}

	public static <T extends Enum<T>> T assertEnum(String value, Class<T> clazz, String paramName, boolean required)
			throws InputValidationException {
		if (value == null)
			if (required)
				throw InputValidationException.oneFieldError(paramName + " is required", paramName, MISSING_ERROR);
			else
				return null;
		try {
			return Enum.valueOf(clazz, value);

		} catch (IllegalArgumentException e) {
			throw InputValidationException.oneFieldError("Invalid " + paramName, paramName,
					"Must be one of " + Arrays.asList(clazz.getEnumConstants()));
		}
	}

	/**
	 * Check that multiple values are required in one shot.
	 * 
	 * @param nameValues
	 *            list of name/value pairs, such as [name1, value1, name2,
	 *            value2]
	 */
	public static void assertRequiredValues(Object... nameValues) throws InputValidationException {
		Map<String, List<String>> errors = new HashMap<String, List<String>>();

		for (int i = 0; i < nameValues.length; i++) {
			String paramName = nameValues[i].toString();
			Object value = nameValues[++i];

			try {
				String sValue = value == null ? null : value.toString();
				assertRequired(sValue, paramName);
			} catch (InputValidationException e) {
				errors.putAll(e.errors);
			}
		}
		if (errors.isEmpty() == false)
			throw new InputValidationException("Required inputs are missing", errors);
	}

	public static String assertClean(final String value, final String paramName) throws InputValidationException {
		final String clean = clean(value);
		if (clean == null && (value == null || value.length() == 0))
			// empty string, OK
			return null;

		// not null, should not have leading / trainling whitespace
		if (clean.length() != value.length())
			throw InputValidationException.oneFieldError(paramName + " contains whitespace", paramName, INVALID_ERROR);
		return clean;
	}

	public static boolean isValidEmailID(String emailid) {
		boolean result = false;
		if (emailid != null) {
			result = EMAIL_REGEX.matcher(emailid).matches();
		}
		return result;
	}

	public static void assertEmailID(String value, String paramName, boolean required) throws InputValidationException {
		if (required && clean(value) == null)
			throw InputValidationException.oneFieldError(paramName + " is required", paramName, MISSING_ERROR);

		// not required
		if (value == null)
			return;

		if (isValidEmailID(value) == false)
			throw InputValidationException.oneFieldError("Invalid Email ID", paramName, INVALID_ERROR);
	}
}
