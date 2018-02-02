package com.shaklee.promo;

/**
 * Interface that allows different components to add request level log messages.
 * Acts as a log context to the request.
 * 
 * @author Elli Albek
 */
public interface RequestLog {
	public void debug(Object message);
}
