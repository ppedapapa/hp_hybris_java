package com.shaklee.promo.impl;

class LoaderException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	LoaderException(String message) {
		super(message);
	}

	LoaderException(Throwable cause) {
		super(cause);
	}

	LoaderException(String message, Throwable cause) {
		super(message, cause);
	}
}