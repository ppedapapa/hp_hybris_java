package com.shaklee.shared.http.rest;

import org.apache.http.client.HttpResponseException;

import com.shaklee.shared.oauth.CommonHttpClient.SimpleResponse;

/**
 * Rest specific errors. Includes the body.
 * 
 * @author Elli Albek
 */
public class RestException extends HttpResponseException {

	private static final long serialVersionUID = 1L;

	public final String response;

	public RestException(SimpleResponse<String> response) {
		super(response.status, response.status + " " + response.message);
		this.response = response.body;
	}

	@Override
	public String toString() {
		return super.toString() + '\n' + response;
	}
}