package com.shaklee.shared.oauth;

import java.io.IOException;
import java.io.Serializable;

/**
 * General interface for a factory of objects and tokens that can expire.
 * 
 * @author Elli Albek
 * 
 * @param <T>
 *            the type of the object.
 */
public interface ObjectSource<T> {

	public ExpiringObject<T> get() throws IOException;

	/**
	 * This wrapper around object includes expiration date.
	 * 
	 * @author Elli Albek
	 * 
	 * @param <T>
	 *            the type fo the object.
	 */
	public static class ExpiringObject<T> implements Serializable {

		private static final long serialVersionUID = 1L;

		/* the actual timestamp of expiration */
		public final long expires;
		public final T object;

		public ExpiringObject(T object, long expires) {
			this.object = object;
			this.expires = expires;
		}
	}
}
