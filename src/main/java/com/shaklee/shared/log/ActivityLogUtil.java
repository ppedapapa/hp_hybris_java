package com.shaklee.shared.log;

import org.apache.log4j.Logger;

import com.shaklee.common.util.JSONSerializer;

/**
 * Utilities for activity log
 * 
 * @author Elli Albek
 */
public class ActivityLogUtil {

	private static final Logger logger = Logger.getLogger(ActivityLogUtil.class);
	
	public static String toJson(Object request, Object response) {
		request = toJson(request);
		response = toJson(response);
		if (request == null)
			return (String) response;
		if (response == null)
			return (String) request;
		return "{\"request\":" + request + ",\n\"response\":" + response + '}';
	}

	public  static String toJson(Object obj) {
		try {
			if (obj == null)
				return null;
			return JSONSerializer.toJacksonJaxbJson(obj, false);
		} catch (Exception e) {
			String error = "Cannot convert " + obj.getClass().getSimpleName() + " to json";
			logger.error(error, e);
			return "{\"error\":\"" + e.toString() + "\"}";
		}
	}

}
