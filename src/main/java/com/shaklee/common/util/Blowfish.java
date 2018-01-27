package com.shaklee.common.util;

import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * Encrypt and decrypt blowfish
 * 
 * @author Elli Albek
 */
public class Blowfish {

	static final Charset blowfishCharset = Charset.forName("UTF-8");

	public static String decrypt(byte[] key, String message)
			throws NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException {
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(
					"Cannot find Blowfish/ECB/PKCS5Padding algorithm", e);
		}

		cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "Blowfish"));
		byte[] clearText = cipher.doFinal(HexBytesConversion
				.hexStringToByteArray(message));

		return new String(clearText, blowfishCharset);
	}
}
