package com.shaklee.entity;

import java.util.HashMap;
import java.util.Map;

public class AttributeEntity {
	private Map<String, Object> attributes = new HashMap<String, Object>();

	public void putAttribute(String name, Object value) {
		attributes.put(name, value);
	}

	public Object getAttribute(String name) {
		return attributes.get(name);
	}
}
