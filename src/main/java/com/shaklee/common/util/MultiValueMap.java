package com.shaklee.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Implementation of multimap that is using array lists for values. This
 * replaces usage of similar class in spring, since it contains only one method.
 * 
 * @author Elli Albek
 */
public class MultiValueMap<K, V> extends HashMap<K, List<V>> {

	private static final long serialVersionUID = 1L;

	public MultiValueMap() {
		super();
	}

	public MultiValueMap(int initialCapacity) {
		super(initialCapacity);
	}

	/**
	 * Add an element to a key.
	 */
	public void add(K key, V value) {
		List<V> l = get(key);
		if (l == null){
			l = new ArrayList<V>(4);
			put(key, l);
		}

		l.add(value);
	}

	/**
	 * Get the first element of a key, or null if there are no elements for that
	 * key.
	 */
	// public V getFirst(K key) {
	// List<V> l = get(key);
	// if (l == null || l.isEmpty())
	// return null;
	//
	// return l.get(0);
	// }
}
