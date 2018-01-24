package com.shaklee.shared.http.oauth;

/**
 * A successful Mulesoft OAuth response containing tokens.
 * 
 * @author Elli Albek
 */
public class OAuthResponse {

	public String access_token, refresh_token, scope, token_type;
	long expires_in;
}