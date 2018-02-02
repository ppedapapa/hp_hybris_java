package com.shaklee.common.util;

import java.nio.charset.Charset;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Simple string utilities.
 * 
 * @author Elli Albek
 */
public class StringUtils {

	public static final Charset UTF_8 = Charset.forName("utf8");

	private static final Pattern WHITE_SPACE = Pattern.compile("\\s+");

	/**
	 * Return a cleaned string or null (if the cleaned string is empty). This
	 * method never returns an empty string or a whitespace string. In those
	 * cases it will return null. Useful from preventing bad data in the DB,
	 * such as empty strings or whitespace.
	 */
	public static String clean(String s) {
		if (s == null)
			return null;
		s = s.trim();
		if (s.length() == 0)
			return null;

		return s;
	}

	public static String normalizeEmptyAndWhitespace(String s) {
		s = StringUtils.clean(s);
		if (s == null)
			return null;

		s = WHITE_SPACE.matcher(s).replaceAll(" ");
		return s;
	}

	/**
	 * Returns a string that is a list of a single character. Example:
	 * characterList('?', ',', 3)
	 * 
	 * returns the string
	 * 
	 * "?,?,?"
	 */
	public static CharSequence characterList(char c, char sep, int count) {
		if (count == 0)
			return "";
		if (count == 1)
			return Character.toString(c);

		StringBuilder sb = new StringBuilder(count * 2 - 1).append(c);

		for (int i = 1; i < count; i++)
			sb.append(sep).append(c);

		return sb;
	}

	/**
	 * Join the list items to one string using the separator character.
	 */
	public static CharSequence join(List<? extends Object> list, char separator) {
		int size = list.size();
		if (size == 0)
			return "";

		StringBuilder sb = new StringBuilder();
		sb.append(list.get(0));

		for (int i = 1; i < size; i++) {
			sb.append(separator).append(list.get(i));
		}
		return sb;
	}
	
	/**
	 * Returns true if the string represents and integer number.
	 */
	public static boolean isStringInt(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}

	/**
	 * Checks that the String S starts with the string other, case INSENSITIVE.
	 */
	public static boolean startsWithIgnoreCase(String s, final String other) {
		return s.regionMatches(true, 0, other, 0, other.length());
	}

	/**
	 * Checks that the String S ends with the string other, case INSENSITIVE.
	 */
	public static boolean endsWithIgnoreCase(String s, String ending) {
		return s.regionMatches(true, s.length() - ending.length(), ending, 0, ending.length());
	}
}