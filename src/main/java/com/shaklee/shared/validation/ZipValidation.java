package com.shaklee.shared.validation;

import static com.shaklee.shared.validation.Validators.INVALID_ERROR;
import static com.shaklee.shared.validation.Validators.MISSING_ERROR;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Zip code validation is complex enough to require a separate class.
 * 
 * @author Elli Albek
 */
public class ZipValidation {

	public static final Pattern USA_ZIP = Pattern.compile(
			"(\\d\\d\\d\\d\\d)[\\ -]?(\\d\\d\\d\\d)?", Pattern.MULTILINE);
	public static final Pattern CANADA_ZIP = Pattern.compile(
			"([A-Z]\\d[A-Z])[\\ -]?(\\d[A-Z]\\d)", Pattern.MULTILINE);

	/**
	 * Validate and normalize zip code for USA and Canada. US zip is normalized
	 * to just digits (5 or 9) with no separation
	 * 
	 * @param country
	 *            USA or CAN
	 * @param fieldName
	 *            for reporting and error
	 * @return normalized zip code
	 * @throws InputValidationException
	 */
	public static String normalizeValidateUsaCaZip(String zip, String country,
			String fieldName) throws InputValidationException {
		return normalizeValidateUsaCaZip(zip, country, fieldName, false);
	}

	/**
	 * Validate and normalize zip code for USA and Canada.
	 * 
	 * @param country
	 *            USA or CAN
	 * @param fieldName
	 *            for reporting and error
	 * @return normalized zip code
	 * @throws InputValidationException
	 */
	public static String normalizeValidateUsaCaZip(String zip, String country,
			String fieldName, boolean usaDashSeparator)
			throws InputValidationException {
		if (zip == null)
			return null;

		if (country == null)
			InputValidationException.oneFieldError("Zip requires a country",
					fieldName, MISSING_ERROR);

		if ("USA".equals(country)) {
			Matcher m = USA_ZIP.matcher(zip);
			if (!m.matches())
				InputValidationException.oneFieldError(
						"Invalid USA zip " + zip, fieldName, INVALID_ERROR);
			if (m.start(2) <= 0)
				// 5 digits zip
				return m.group(1);
			else {
				// full 9 digits zip
				if (usaDashSeparator)
					return m.group(1) + '-' + m.group(2);
				else
					return m.group(1) + m.group(2);
			}
		}

		else if ("CAN".equals(country)) {
			Matcher m = CANADA_ZIP.matcher(zip);
			if (m.matches()) {
				return zip.substring(m.start(1), m.end(1)) + ' '
						+ zip.substring(m.start(2), m.end(2));
			}

			InputValidationException.oneFieldError("Invalid Canada zip " + zip,
					fieldName, INVALID_ERROR);
		}

		InputValidationException.oneFieldError("Contry not supported: "
				+ country, fieldName, INVALID_ERROR);

		return null;
	}

	public static String formatZip(String zip, String country) {
		if ("USA".equals(country) && zip.length() > 5) {
			return new StringBuilder(zip).insert(5, '-').toString();
			// return zip.substring(0,4) + '-' + zip.substring(5);
		}
		return zip;
	}
}