package com.shaklee.common.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Small library of collections utilities, similar to Guava
 * 
 * @author Elli Albek
 */
public class CollectionFunctions {

	/**
	 * Flatten two level collections tree (collection of child collections ) to
	 * one list.
	 */
	public static <F, T> List<T> flatten(Collection<F> l, Function<F, List<T>> mapper, Predicate<T> filter) {
		ArrayList<T> t = new ArrayList<T>(l.size());
		for (F from : l) {
			List<T> to = mapper.apply(from);
			for (T t1 : to)
				if (filter.accept(t1))
					t.add(t1);
		}

		return t;
	}

	/**
	 * Flatten two level collections tree (collection of child collections ) to
	 * one list.
	 */
	public static <F, T> List<T> flatten(Collection<F> l, Function<F, List<T>> mapper) {
		ArrayList<T> t = new ArrayList<T>(l.size());
		for (F from : l) {
			List<T> to = mapper.apply(from);
			t.addAll(to);
		}

		return t;
	}

	/**
	 * Filter collection. Mapper should return null if the element should be
	 * filtered out.
	 */
	public static <T> List<T> filter(List<T> l, Predicate<T> filter) {
		ArrayList<T> to = new ArrayList<T>(l.size() / 2 + 1);
		for (T el : l)
			if (filter.accept(el))
				to.add(el);

		return to;
	}

	/**
	 * Filter collection. Mapper should return null if the element should be
	 * filtered out.
	 */
	public static <F, T> List<T> filter(List<F> l, Function<F, T> mapper) {
		ArrayList<T> t = new ArrayList<T>(l.size() / 2 + 1);
		for (F el : l) {
			T to = mapper.apply(el);
			if (to != null)
				t.add(to);
		}
		return t;
	}

	/**
	 * Filter collection for one match. If nothing matches, return null. If more
	 * than one match, throw {@link IllegalArgumentException}
	 */
	public static <T> T one(Collection<T> l, Predicate<T> filter) {
		T match = null;
		for (T el : l) {
			if (filter.accept(el))
				if (match == null)
					// first match
					match = el;
				else
					throw new IllegalArgumentException("Expecting exactelly one, got " + match + " and " + el);
		}
		return match;
	}

	/**
	 * Returns true if at least one element in the list matches the condition.
	 */
	public static <T> boolean anyMatch(Collection<T> l, Predicate<T> filter) {
		for (T el : l) {
			if (filter.accept(el))
				return true;
		}
		return false;
	}

	/**
	 * Filter collection for one and only one match. If the number of matches is
	 * not exactly one, throw {@link IllegalArgumentException}
	 */
	public static <T> T oneAndOnlyOne(Collection<T> l, Predicate<T> filter) {
		T match = one(l, filter);
		if (match == null)
			throw new IllegalArgumentException("Expecting exactelly one match, found none");

		return match;
	}

	/**
	 * Group elements in a list by key
	 */
	public static <K, T> Map<K, List<T>> group(Collection<T> l, Function<T, K> keyMapper) {
		MultiValueMap<K, T> map = new MultiValueMap<K, T>();

		for (T el : l) {
			K key = keyMapper.apply(el);
			map.add(key, el);
		}

		return map;
	}

	/**
	 * Organize the objects in the list in a tree. Each level is by a different
	 * key in the map. this is effectively the reverse of a DB join that
	 * flattens a data tree.
	 */
	// public static <T> Object tree(List<Map<String, T>> l, String... keys) {
	//
	// Get get = new Get<Object>(keys[keys.length - 1]);
	// Map m = group(l, get);
	//
	// for (int i = keys.length - 2; i >= 0; i--) {
	// final String key = keys[i];
	// m = group(m.values(), new Function<Map, Object>() {
	// @Override
	// public Object apply(Map input) {
	// return ((Map) input.get(0)).get(key);
	// }
	// });
	// }
	//
	// return m;
	// }

	/**
	 * Converts the values of a map. Returns a new copy of the map.
	 * 
	 * @param <K>
	 *            Key type
	 * @param <F>
	 *            From value
	 * @param <T>
	 *            To value
	 */
	public static <K, F, T> Map<K, T> map(Map<K, F> from, Function<Entry<K, F>, T> mapper) {
		Map<K, T> to = new LinkedHashMap<K, T>(from.size() / 2 + 1);

		for (Entry<K, F> e : from.entrySet()) {
			T value = mapper.apply(e);
			if (value != null)
				to.put(e.getKey(), value);
		}

		return to;
	}

	/**
	 * Converts the values of a list. Returns a new list.
	 */
	public static <F, T> List<T> map(Collection<F> from, Function<F, T> mapper) {
		ArrayList<T> to = new ArrayList<T>(from.size() / 2 + 1);

		for (F e : from) {
			T value = mapper.apply(e);
			if (value != null)
				to.add(value);
		}

		return to;
	}

	public interface Predicate<T> {
		boolean accept(T t);
	}

	public interface Function<F, T> {
		T apply(F input);
	}

	/**
	 * Simple implementation of Function that gets a value from a map.
	 */
	public static class Get<T> implements Function<Map<String, T>, T> {

		public String name;

		public Get(String name) {
			this.name = name;
		}

		@Override
		public T apply(Map<String, T> input) {
			return input.get(name);
		}
	}

	public static CharSequence toString(List<?> l, String indent) {
		if (indent == null)
			indent = "";

		StringBuilder sb = new StringBuilder(l.size() * 3 + 1);
		for (Object o : l) {
			sb.append(toString(o, indent));
		}
		return sb;
	}

	public static CharSequence toString(Map<?, ?> m, String indent) {
		if (indent == null)
			indent = "";

		StringBuilder sb = new StringBuilder(m.size() * 10 + 1);
		for (Map.Entry<?, ?> e : m.entrySet()) {
			sb.append(indent).append(e.getKey()).append(":\t");
			sb.append(toString(e.getValue(), indent));
		}
		return sb;
	}

	public static CharSequence toString(Object o, String indent) {
		if (indent == null)
			indent = "";

		StringBuilder sb = new StringBuilder();

		if (o instanceof List<?>) {
			List<?> l = (List<?>) o;
			sb.append("[\n").append(toString(l, indent + "  ")).append(indent).append(']');
		} else if (o instanceof Map<?, ?>) {
			Map<?, ?> m = (Map<?, ?>) o;
			sb.append(indent).append("{\n").append(toString(m, indent + "  ")).append(indent).append('}');
		} else
			sb.append(indent).append(o);

		sb.append('\n');
		return sb;
	}

	/**
	 * Subtract one list from another. Unlike collection.delete, this method
	 * will create a new collection for the results, and the original
	 * collections are not modified.
	 */
	public static <T> List<T> substract(Collection<T> list, Collection<T> substract) {
		ArrayList<T> result = new ArrayList<T>(list.size() / 2 + 1);
		for (T el : list)
			if (substract.contains(el) == false)
				result.add(el);

		return result;
	}
}
