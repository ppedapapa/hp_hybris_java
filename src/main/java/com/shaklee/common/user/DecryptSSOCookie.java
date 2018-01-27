package com.shaklee.common.user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.shaklee.common.util.Blowfish;
import com.shaklee.common.util.HexBytesConversion;
import com.shaklee.common.util.Md5;

/**
 * Decrypts the user name from cookie values, and verifies the signature cookie.
 * 
 * @author Elli Albek
 */
public class DecryptSSOCookie {
	private static final Logger logger = Logger
			.getLogger(DecryptSSOCookie.class);

	// Shaklee's secret key. Do not share.
	// Per Greg, should not be in property file.
	static final String sKey = "cfcd208495d565ef66e7dff9f98764da";
	public static final byte[] keyBytes = HexBytesConversion
			.hexStringToByteArray(sKey);

	private static final Pattern userNamePattern = Pattern
			.compile("[A-Z][A-Z][0-9][0-9][0-9][0-9][0-9]=");

	private final String sSSOCookie;
	private final String sCookieHash;
	private final String sSessionId;

	// This technically can also be static, but not 100% clear from the API
	// private final Key key = new SecretKeySpec(keyBytes, "Blowfish");

	public DecryptSSOCookie(String sSSOCookie, String sCookieHash,
			String sSessionId) {
		this.sSSOCookie = sSSOCookie;
		this.sCookieHash = sCookieHash;
		this.sSessionId = sSessionId;
	}

	public String decrypt() {
		try {
			if (sSSOCookie == null)
				throw new CookieException("Missing SSO Cookie");
			if (sCookieHash == null)
				throw new CookieException("Missing Cookie Hash");
			if (sSessionId == null)
				throw new CookieException("Missing Session Id");

			String sShakleeId = Blowfish.decrypt(keyBytes, sSSOCookie);

			sShakleeId = sShakleeId.toUpperCase();

			Matcher m = userNamePattern.matcher(sShakleeId);

			if (!m.find())
				throw new CookieException("Invalid decrypted Shaklee Id |"
						+ sShakleeId + '|');

			// remove the trailing '='
			sShakleeId = sShakleeId.substring(0, sShakleeId.length() - 1);

			String calculatedMD5Hash = Md5.asHexString(sShakleeId + sSSOCookie
					+ sSessionId);

			if (sCookieHash.toLowerCase().equals(calculatedMD5Hash) == false) {
				throw new CookieException("Hash Cookie Mismatch for "
						+ sShakleeId + ": sent |" + sCookieHash
						+ "| calculated |" + calculatedMD5Hash + '|');
			}

			return sShakleeId;
		} catch (Exception e) {
			logger.error("Decryption error for sSSOCookie=|" + sSSOCookie
					+ "| sCookieHash=|" + sCookieHash + "| sSessionId=|"
					+ sSessionId + '|', e);
			return null;
		}
	}

	/**
	 * Simple accessor method that takes both cookies and returns the user name
	 * if decryption was successful.
	 */
	public static String getUsername(final String req_php_myshaklee,
			final String req_ginger_mojo) {
		if (req_php_myshaklee != null && req_ginger_mojo != null
				&& req_ginger_mojo.length() > 32) {
			final String sCookieHash = req_ginger_mojo.substring(0, 32);
			final String sPayLoad = req_ginger_mojo.substring(32);
			final DecryptSSOCookie sso = new DecryptSSOCookie(sPayLoad,
					sCookieHash, req_php_myshaklee);
			return sso.decrypt();
		}

		return null;
	}
}
