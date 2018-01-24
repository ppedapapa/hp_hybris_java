package com.shaklee.itrack.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

/**
 * Properties object that loads from classpath and contains some convenience
 * methods.
 * 
 * @author Elli Albek
 */
public class PropertiesObject extends Properties {

	public static final long serialVersionUID = 123L;

	private PropertiesObject() {
	}

	public PropertiesObject(Properties base, String file, Class<?> c) {
		putAll(base);
		load(file, c);
	}

	public PropertiesObject(String file, Class<?> c) {
		load(file, c);
	}

	private void load(String file, Class<?> c) {
		InputStream in = c.getResourceAsStream(file);
		if (in == null)
			throw new RuntimeException(
					"Cannot find property file in classpath: " + file);

		try {
			try {
				load(in);
			} finally {
				// close the file ASAP
				in.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(
					"Unable to initialize configuration file " + file, e);
		}

		// trim
		cleanValues(this);
	}

	private static final void cleanValues(Map<Object, Object> m) {

		Iterator<Entry<Object, Object>> i = m.entrySet().iterator();

		while (i.hasNext()) {
			Entry<Object, Object> e = i.next();
			Object val = e.getValue();
			if (val instanceof String) {
				String s = ((String) val).trim();
				if (s.length() == 0)
					i.remove();
				else
					e.setValue(s);
			}
		}
	}

	/**
	 * Simple constructor that loads a file from the app classpath. To get a
	 * file from the root, start the file name with /, such as
	 * "/realtime.properties"
	 */
	public PropertiesObject(String file) {
		this(file, PropertiesObject.class);
	}

	/**
	 * Get a property file for a class. Should be in the same folder with the
	 * same name and properties extension.
	 * 
	 * IE com.shaklee.MyClass should have the file MyClass.properties in the
	 * same folder.
	 */
	public PropertiesObject(Class<?> c) {
		this(c.getSimpleName() + ".properties", c);
	}

	public String getSql(String key) {
		return ClasspathFileLoader.cleanSql(getProperty(key));
	}

	public int getInt(String key) throws IllegalArgumentException {
		String val = getProperty(key);
		if (val != null)
			return Integer.parseInt(val);

		throw new IllegalArgumentException(key);
	}

	/**
	 * Get an int properties with default value.
	 */
	public int getInt(String key, int def) {
		String val = getProperty(key);
		if (val != null)
			return Integer.parseInt(val);

		return def;
	}

	public long getLong(String key) throws IllegalArgumentException {
		String val = getProperty(key);
		if (val != null)
			return Long.parseLong(val);

		throw new IllegalArgumentException(key);
	}

	public Long getLongObject(String key, Long defaultValue)
			throws IllegalArgumentException {
		String val = getProperty(key);
		if (val != null)
			return Long.parseLong(val);

		return null;
	}

	public boolean getBoolean(String key, boolean def)
			throws IllegalArgumentException {
		String val = getProperty(key);
		if (val != null)
			return Boolean.valueOf(val);

		return def;
	}

	public String[] getStrings(String key) throws IllegalArgumentException {
		String val = getProperty(key);
		if (val != null)
			return val.split("[, ]+");

		throw new IllegalArgumentException(key);
	}

	public String[] getStrings(String key, String... def)
			throws IllegalArgumentException {
		String val = getProperty(key);
		if (val != null)
			return val.split("[,\\s]+");

		return def;
	}

	public int[] getInts(String key) throws IllegalArgumentException {
		String[] val = getStrings(key);
		int[] i = new int[val.length];
		for (int j = 0; j < i.length; j++)
			i[j] = Integer.parseInt(val[j]);
		return i;
	}

	public PropertiesObject getChildProp(String prefix) {
		PropertiesObject child = new PropertiesObject();
		calcChildProps(prefix, child);
		return child;
	}

	/**
	 * Return child properties as an UNSYNCHRONIZED Map.
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> getChildPropMap(String prefix) {
		@SuppressWarnings("rawtypes")
		Map child = new HashMap<String, String>();
		calcChildProps(prefix, child);
		return (Map<String, String>)child;
	}

	private void calcChildProps(String prefix, Map<Object, Object> child) {
		if (prefix.endsWith(".") == false)
			prefix += '.';

		Set<Map.Entry<Object, Object>> entrySet = this.entrySet();
		for (Map.Entry<Object, Object> e : entrySet) {
			String name = e.getKey().toString();
			if (name.startsWith(prefix)){
				child.put(name.substring(prefix.length()), e.getValue());
			}
		}
	}

	/**
	 * Splits this object to multiple objects based on the first part of the key
	 * (before the first dot)
	 */
	public Map<String, PropertiesObject> split() {
		Map<String, PropertiesObject> map = new HashMap<String, PropertiesObject>();

		Set<Map.Entry<Object, Object>> entrySet = this.entrySet();
		for (Map.Entry<Object, Object> e : entrySet) {
			final String key = e.getKey().toString();
			final int dot = key.indexOf('.');
			if (dot > 0) {
				final String pref = key.substring(0, dot);
				PropertiesObject p = map.get(pref);
				if (p == null) {
					p = new PropertiesObject();
					map.put(pref, p);
				}
				String subkey = key.substring(dot + 1);
				p.put(subkey, e.getValue());
			}
		}

		return map;
	}
}