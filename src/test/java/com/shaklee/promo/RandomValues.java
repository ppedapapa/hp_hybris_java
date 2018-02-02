package com.shaklee.promo;

import java.util.Random;

import com.shaklee.util.StringUtil;

/**
 * Random values for testing.
 * 
 * @author Elli Albek
 */
public class RandomValues {

	private static final Random RANDOM = new Random();

	/**
	 * Returns a random shaklee ID
	 */
	public static String shakleeID() {
		return "JU" + StringUtil.padNum(nextInt(99999), 5);
	}

	/**
	 * Returns a random integer between 0 and max.
	 */
	public static int nextInt(int max) {
		return RANDOM.nextInt(max);
	}

	public static String randomSSN() {
		int i = RANDOM.nextInt();
		i = Math.abs(i);
		i = 600000000 + i % 99999999;
		return Integer.toString(i);
	}

	public static String randomDriverLicense() {
		int i = RANDOM.nextInt();
		i = Math.abs(i);
		i = 10000000 + i % 90000000;
		return "TST" + i;
	}
}
