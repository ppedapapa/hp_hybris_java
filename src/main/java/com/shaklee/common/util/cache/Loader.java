package com.shaklee.common.util.cache;

/**
 * Simple interface for an object source, such as a database, web service,
 * cache, etc.
 * 
 * TODO: Define some standard errors
 * 
 * @author Elli Albek
 */
public interface Loader<K, V> {

	/**
	 * Return the object V or null if it is not found.
	 */
	V get(K key);
}