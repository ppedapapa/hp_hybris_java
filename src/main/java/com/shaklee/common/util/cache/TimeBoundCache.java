package com.shaklee.common.util.cache;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Cache for SMALL amount of objects that are frequently used, but are heavy to
 * load. Example is system configuration that is saved in the DB. This is not a
 * cache for runtime objects of request specific data.
 * 
 * @author Elli Albek
 */
class TimeBoundCache<K, V> implements Loader<K, V> {

	private int expiresMs;

	private final ConcurrentHashMap<K, TimeCacheEntry<V>> map = new ConcurrentHashMap<K, TimeCacheEntry<V>>();

	public TimeBoundCache(int expiresSec) {
		expiresMs = expiresSec * 1000;
	}

	@Override
	public V get(K key) {
		TimeCacheEntry<V> v = map.get(key);

		if (v != null)
			if (System.currentTimeMillis() < (v.created + expiresMs))
				// case: in cache
				return v.value;
			else
				// case: in cache but expired
				map.remove(key);

		// not found
		return null; // converts to None
	}

	void put(K key, V value) {
		// no point putting nothing with time expiration
		if (value == null)
			map.remove(key);
		else
			map.put(key, new TimeCacheEntry<V>(value));
	}

	/**
	 * Internal wrapper for map entry with insertion time
	 */
	static class TimeCacheEntry<V> {

		final long created;
		final V value;

		TimeCacheEntry(long created, V value) {
			this.created = created;
			this.value = value;
		}

		TimeCacheEntry(V value) {
			this(System.currentTimeMillis(), value);
		}
	}

	public void clear() {
		map.clear();
	}
}