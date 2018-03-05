package com.shaklee.common.util.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shaklee.common.util.cache.TimeBoundCache.TimeCacheEntry;

/**
 * Loader that loads a single object, and caches it for a certain amount of
 * time.
 * 
 * Delegates actual loading to a secondary loader and caches the results. The
 * cache supports synchronization of the data source (origin), for cases where
 * accessing the data is extremely heavy and slow on the CPU or back end
 * systems. The synchronization prevents loading the same object twice
 * concurrently, which is normally not a problem for fast loading objects.
 * 
 * @author Elli Albek
 */
public class CachingSingletonLoader<V> implements Loader<Object, V> {

	// can be replaced
	Logger logger = LoggerFactory.getLogger(CachingSingletonLoader.class);

	private final boolean synchronoizeOrigin;
	private final int expiresMs;

	private volatile TimeBoundCache.TimeCacheEntry<V> cache;
	final Loader<Object, V> origin;

	public CachingSingletonLoader(Loader<Object, V> origin, int expiresSec, boolean synchronoizeOrigin) {
		this.origin = origin;
		this.synchronoizeOrigin = synchronoizeOrigin;
		expiresMs = expiresSec * 1000;
	}

	public CachingSingletonLoader(Loader<Object, V> origin, int expiresSec) {
		this(origin, expiresSec, false);
	}

	public V get() {
		return get(null);
	}

	@Override
	public V get(Object key) {

		TimeBoundCache.TimeCacheEntry<V> value = this.cache;
		if (value != null && System.currentTimeMillis() < (value.created + expiresMs)) {
			// case: in cache
			logger.debug("HIT");
			return value.value;
		}

		if (synchronoizeOrigin)
			return loadSynchronized();

		// cache miss
		logger.debug("MISS");

		// simple non synchronized access, allows multiple threads to load
		// the same object and access the datasource (origin) concurrently,
		// possibly loading the same object more than once by two threads
		// (extremely unlikely)
		return _load();
	}

	/**
	 * load the object in a synchrnonized fashion, which involves checking that
	 * another thread did not do it already while this thread blocks.
	 */
	synchronized V loadSynchronized() {
		// check that another thread did not do it already.
		TimeBoundCache.TimeCacheEntry<V> value = this.cache;
		if (value != null && System.currentTimeMillis() < (value.created + expiresMs)) {
			logger.debug("HIT");
			return value.value;
		}
		// still not in the cache
		// cache miss
		logger.debug("MISS");

		return _load();
	}

	private V _load() {
		V val = origin.get(null);
		cache = new TimeCacheEntry<V>(val);
		return val;
	}
	

	public void clear() {
		cache = null;
	}
}