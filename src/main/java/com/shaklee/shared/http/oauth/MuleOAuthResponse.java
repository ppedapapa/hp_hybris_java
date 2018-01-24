package com.shaklee.shared.http.oauth;

/**
 * A successful Mulesoft OAuth response containing tokens. This class is
 * abstract so subclasses can add @JsonIgnoreProperties(ignoreUnknown = true)
 * according to their jackson version
 * 
 * @author Elli Albek
 */
// @JsonIgnoreProperties(ignoreUnknown = true)
public abstract class MuleOAuthResponse extends OAuthResponse {

	public ClientAuth simple_client;

	public static class ClientAuth {
		public String envId, orgId;
	}
}