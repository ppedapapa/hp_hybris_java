package com.shaklee.itrack.common.util;

/**
 * Converts hex strings to byte arrays and back.
 * 
 * @author Elli Albek
 */
public class HexBytesConversion {

	/**
	 * This implementation was copied from some old code, and is very inefficient.
	 */
	public static String byteArrayToHexString(byte[] b) {
		String result = "";

		for (int i = 0; i < b.length; i++) {
			result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
		}

		return result;
	}

	public static byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];

		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character
					.digit(s.charAt(i + 1), 16));
		}

		return data;
	}

}
