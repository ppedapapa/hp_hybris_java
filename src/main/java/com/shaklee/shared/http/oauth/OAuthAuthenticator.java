package com.shaklee.shared.http.oauth;

import org.apache.http.client.methods.HttpRequestBase;

import com.shaklee.common.util.cache.CachingSingletonLoader;

/**
 * Utility class which is using {@link OAuthClient} to add authentication to
 * HTTP requests.
 * 
 * @author Elli Albek
 */
public class OAuthAuthenticator<R extends OAuthResponse> {

	protected final CachingSingletonLoader<R> oAuthClient;

	public OAuthAuthenticator(OAuthClient<R> oAuth, int expirationSeconds) {
		this.oAuthClient = new CachingSingletonLoader<R>(oAuth, expirationSeconds, true);
	}

	public R get() {
		return oAuthClient.get();
	}

	/**
	 * Authenticate request, and also return the authentication that was used.
	 * This is the main way to use this class.
	 */
	public R authenticateRequest(final HttpRequestBase request) {
		R auth = oAuthClient.get();
		authenticateRequest(request, auth);
		return auth;
	}

	public void authenticateRequest(final HttpRequestBase request, R auth) {
		String[] headers = oAuthHeaders(auth);
		for (int i = 0; i < headers.length; i += 2) {
			request.addHeader(headers[i], headers[++i]);
		}
	}

	public String[] oAuthHeaders(R auth) {
		return new String[] { "Authorization", "Bearer " +  auth.access_token};
	}

}
