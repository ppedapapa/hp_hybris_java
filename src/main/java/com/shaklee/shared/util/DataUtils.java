package com.shaklee.shared.util;

import org.apache.commons.lang3.text.WordUtils;

import com.shaklee.common.util.StringUtils;

/**
 * Utilities for data values
 * 
 * @author Elli Albek
 */
public class DataUtils {

	public static String titleCase(String s) {
		s = StringUtils.clean(s);
		if (s == null)
			return null;
		return WordUtils.capitalize(s.toLowerCase());
	}
}
