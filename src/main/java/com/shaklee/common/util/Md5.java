package com.shaklee.itrack.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Calculates MD5 signatures.
 * 
 * @author Elli Albek
 */
public class Md5 {

	/**
	 * Return the MD5 signature as Hex String
	 */
	public static final String asHexString(String s) {
		byte[] asBytes = asBytes(s);
		return HexBytesConversion.byteArrayToHexString(asBytes);
	}

	/**
	 * Return the MD5 signature as byte array
	 */
	public static byte[] asBytes(String s) {
		MessageDigest md5Digest;
		try {
			md5Digest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e.toString());
		}
		return md5Digest.digest(s.getBytes());
	}
}
