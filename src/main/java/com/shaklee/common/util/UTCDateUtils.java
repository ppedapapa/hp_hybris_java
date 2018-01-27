package com.shaklee.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang3.time.FastDateFormat;

/**
 * Utilities for dealing with UTC dates, including formatting and parsing.
 * 
 * @author Elli Albek
 */
public class UTCDateUtils {

	/**
	 * ISO like data format without the T, similar to SQL timestamp.
	 */
	public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT = "yyyy-MM-dd";

	public static final TimeZone UTC_TIME_ZONE = TimeZone.getTimeZone("GMT");

	/**
	 * Separate parser since FastDateFormat is not parsing
	 */
	private static final SimpleDateFormat datetimeParser = createParser();

	private static SimpleDateFormat createParser() {
		SimpleDateFormat parser = new SimpleDateFormat(DATETIME_FORMAT);
		parser.setTimeZone(UTC_TIME_ZONE);
		return parser;
	}

	public static Date parseUTCDate(String s) throws ParseException {
		// Formats are not thread safe, still quicker than creating a new parser
		// every time
		synchronized (datetimeParser) {
			return datetimeParser.parse(s);
		}
	}

	private static final FastDateFormat UTC_DATETIME_FORMAT = FastDateFormat
			.getInstance(DATETIME_FORMAT, UTC_TIME_ZONE);

	public static String formatUTCDate(Date d) {
		// thread safe
		return UTC_DATETIME_FORMAT.format(d);
	}
}
