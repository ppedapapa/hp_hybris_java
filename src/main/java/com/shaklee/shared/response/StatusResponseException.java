package com.shaklee.shared.response;

import com.shaklee.shared.util.StatusResponse;

/**
 * Wrapper exception that can be thrown from any place in the code and handle by
 * the framework as a normal response. Good for generic and common errors.
 * 
 * @author Elli Albek
 */
public class StatusResponseException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public final StatusResponse r;

	public StatusResponseException(StatusResponse r) {
		this.r = r;
	}

	public StatusResponseException(int status, String message) {
		r = new StatusResponse(status, message);
	}
}
