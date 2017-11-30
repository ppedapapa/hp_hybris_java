package com.shaklee.util;

import java.io.ByteArrayOutputStream;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.jaxrs.json;

import org.springframework.stereotype.Component;



/**
 * Utilities for standard json responses and errors from web services.
 * 
 * @author Elli Albek
 */
public class JSONSerializer {

	/*
	 * This simulates the actual code that is used inside Jersey to generate
	 * json from the object. This guarantees the same generation mechanism, as
	 * different object to json conversion classes normally produce slightly
	 * different results.
	 * 
	 * Indent is added for readability, since it will only be used in testing.
	 */
	public static String toJacksonJaxbJson(Object o, boolean indent) {
		return toJacksonJaxbJson(o, indent, false);
	}

	/**
	 * This simulates the actual code that is used inside Jersey to generate
	 * json from the object. This guarantees the same generation mechanism, as
	 * different object to json conversion classes normally produce slightly
	 * different results.
	 * 
	 * Indent is added for readability, since it will only be used in testing.
	 */
	public static String toJacksonJaxbJson(Object o, boolean indent, boolean escape) {
		try {
			JacksonJsonProvider jaxbProvider = new com.jayway.jsonpath.spi.json.JacksonJsonProvider();
			if (indent)
				jaxbProvider.configure(Feature.INDENT_OUTPUT, true);
			if (escape)
				jaxbProvider.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);

			jaxbProvider.configure(Feature.WRITE_NULL_PROPERTIES, false);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			jaxbProvider.writeTo(o, o.getClass(), o.getClass(), null, null, null, out);
			return new String(out.toByteArray(), "UTF8");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
