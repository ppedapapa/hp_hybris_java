package com.shaklee.common.util.cache;

import org.apache.log4j.Logger;

import com.shaklee.common.util.CollectionFunctions.Function;

/**
 * Loader implementation that delegates to a loader and caches the results. The
 * cache supports synchronization of the data source (origin), for cases where
 * accessing the data is extremely heavy and slow on the CPU or back end
 * systems. The synchronization prevents loading the same object twice
 * concurrently, which is normally not a problem for fast loading objects.
 * 
 * @author Elli Albek
 */
public class CachingLoader<K, V> implements Loader<K, V> {

	// can be replaced
	private static final Logger logger = Logger.getLogger(CachingLoader.class);

	private final boolean synchronoizeOrigin;

	final TimeBoundCache<Object, V> cache;
	final Loader<K, V> origin;
	/** Used when the cache keys are different than the loader keys **/
	final Function<K, Object> keyConverter;

	public CachingLoader(Loader<K, V> origin, int expiresSec, Function<K, Object> keyConverter,
			boolean synchronoizeOrigin) {
		cache = new TimeBoundCache<Object, V>(expiresSec);

		this.origin = origin;
		this.keyConverter = keyConverter;
		this.synchronoizeOrigin = synchronoizeOrigin;
	}

	public CachingLoader(Loader<K, V> origin, int expiresSec) {
		this(origin, expiresSec, null, false);
	}

	/**
	 * Loader instance with small local cache
	 */
	@Override
	public V get(K key) {
		final Object cacheKey = keyConverter == null ? key : keyConverter.apply(key);

		V value = cache.get(cacheKey);
		if (value != null) {
			// cache hit
			if (logger.isDebugEnabled())
				logger.debug("HIT  " + cacheKey);
			return value;
		}

		// cache miss
		if (logger.isDebugEnabled())
			logger.debug("MISS " + cacheKey);

		if (synchronoizeOrigin)
			return loadSynchronized(key, cacheKey);

		// simple non synchronized access, allows multiple threads to load
		// the same object and access the datasource (origin) concurrently,
		// possibly loading the same object more than once by two threads
		// (extremely unlikely)
		value = origin.get(key);
		if (value != null)
			cache.put(cacheKey, value);

		return value;
	}

	/**
	 * load the object in a synchrnonized fashion, which involves checking that
	 * another thread did not do it already while this thread blocks.
	 */
	synchronized V loadSynchronized(final K key, final Object cacheKey) {
		// check the cache again within the synchronized block, another
		// thread may have loaded the object and put it there
		V value = cache.get(cacheKey);
		if (value != null)
			return value;

		// still not in the cache
		value = origin.get(key);

		// add the object to the cache within the synchronized block
		if (value != null)
			cache.put(cacheKey, value);

		return value;
	}

	public void clear() {
		cache.clear();
	}
}