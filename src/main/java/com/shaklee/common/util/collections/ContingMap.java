package com.shaklee.common.util.collections;

import java.util.HashMap;

public class ContingMap<K> extends HashMap<K, Integer> {

	private static final long serialVersionUID = -5734271597167387476L;

	public ContingMap() {
	}

	public int increment(K key) {
		Integer v = get(key);
		if (v == null)
			v = 0;

		v++;
		put(key, v);
		return v;
	}

	public int getValue(K key) {
		Integer v = super.get(key);
		if (v == null)
			return 0;

		return v;
	}
}
