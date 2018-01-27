package com.shaklee.common.user;

/**
 * Package private exception for invalid cookies, such as invalid user names and
 * signatures.
 * 
 * @author Elli Albek
 */
class CookieException extends Exception {
	private static final long serialVersionUID = 13425L;

	CookieException(String msg) {
		super(msg);
	}
}
