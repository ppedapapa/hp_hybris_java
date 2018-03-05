package com.shaklee.common.util;

/**
 * Common exceptions for services
 * 
 * @author Emre Koca
 */
public class ExceptionUtils {
	/**
	 * Fatal, non recoverable error. It may be a bug in our code or something
	 * went very wrong with Google reCapthca API. Client should not retry.
	 * 
	 * @author Emre Koca
	 */
	public static class FatalServiceRuntimeException extends RuntimeException {

		private static final long serialVersionUID = 1L;

		public FatalServiceRuntimeException(String message) {
			super(message);
		}

		public FatalServiceRuntimeException(String message, Throwable cause) {
			super(message, cause);
		}

		public FatalServiceRuntimeException(Throwable cause) {
			super(cause.getMessage(), cause);
		}
	}
}