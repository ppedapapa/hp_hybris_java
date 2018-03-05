package com.shaklee.shared.oauth;

import java.io.IOException;

/**
 * An object source that caches the results of another source.
 * 
 * @author Elli Albek
 */
public class CachingObjectSource<T> implements ObjectSource<T> {

	private final ObjectSource<T> origin;
	private volatile ExpiringObject<T> last;

	public CachingObjectSource(ObjectSource<T> origin) {
		this.origin = origin;
	}

	@Override
	public ExpiringObject<T> get() throws IOException {
		ExpiringObject<T> current = getLast();
		if (current == null) {
			last = current = origin.get();
		}
		return current;
	}

	public ExpiringObject<T> getLast() {
		final ExpiringObject<T> current = this.last;
		if (current != null && current.expires < System.currentTimeMillis()) {
			// last object has expired
			this.last = null;
			return null;
		}
		// return whatever is cached (a valid object or null)
		return current;
	}
}
