package com.shaklee.common.util.cache;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Loader implementation that delegates to a loader and caches the results. The
 * cache supports loading data in collections, where some entry can be in the
 * cache, and some retrieved from origin.
 * 
 * @author Elli Albek
 */
public class MultiCachingLoader<K, V> implements Loader<Collection<K>, Map<K, V>> {

	// can be replaced
	private static final Logger logger = LoggerFactory.getLogger(MultiCachingLoader.class);
	private static final boolean debugEnabled = logger.isDebugEnabled();

	final TimeBoundCache<K, V> cache;
	final Loader<Collection<K>, Map<K, V>> origin;

	public MultiCachingLoader(Loader<Collection<K>, Map<K, V>> origin, int expiresSec) {
		cache = new TimeBoundCache<K, V>(expiresSec);
		this.origin = origin;
	}

	/**
	 * Loader instance with small local cache
	 */
	@Override
	public Map<K, V> get(Collection<K> keys) {

		HashMap<K, V> results = new HashMap<K, V>(keys.size());

		Iterator<K> ki = keys.iterator();
		while (ki.hasNext()) {
			final K key = ki.next();
			V value = cache.get(key);
			if (value != null) {
				results.put(key, value);
				ki.remove();
			}
		}

		if (debugEnabled) {
			if (results.size() > 0)
				logger.debug("HIT  " + results.keySet());
		}

		if (keys.isEmpty())
			// quick exit
			return results;

		// cache miss
		if (debugEnabled)
			logger.debug("MISS " + keys);

		// simple non synchronized access, allows multiple threads to load
		// the same object and access the datasource (origin) concurrently,
		// possibly loading the same object more than once by two threads
		// (extremely unlikely)
		Map<K, V> values = origin.get(keys);
		if (values != null) {
			for (Map.Entry<K, V> e : values.entrySet()) {
				cache.put(e.getKey(), e.getValue());
			}
			results.putAll(values);
		}

		return results;
	}

	public void clear() {
		cache.clear();
	}
}