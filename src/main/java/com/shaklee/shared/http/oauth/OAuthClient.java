package com.shaklee.shared.http.oauth;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.HttpUriRequest;

import com.shaklee.common.util.CollectionUtils;
import com.shaklee.common.util.cache.Loader;
import com.shaklee.shared.http.rest.RestClient;
import com.shaklee.shared.oauth.CommonHttpClient;
import com.shaklee.shared.oauth.CommonHttpClient.SimpleResponse;

/**
 * Mulesoft specific OAuth client.
 * 
 * @author Elli Albek
 */
public abstract class OAuthClient<R extends OAuthResponse> implements Loader<Object, R> {

	private final CommonHttpClient client;
	private final HashMap<String, String> requestParams;

	/** Factor that is applied to expiration time **/
	private final String url;

	/**
	 * Create client with typical oAuth parameters.
	 */
	public OAuthClient(CommonHttpClient client, String baseUrl, String client_id, String client_secret) {
		this(client, baseUrl, CollectionUtils.creatMap("grant_type", "client_credentials", "client_id", client_id,
				"client_secret", client_secret));
	}

	public OAuthClient(CommonHttpClient client, String baseUrl, HashMap<String, String> requestParams) {
		this.client = client;
		this.url = baseUrl;
		this.requestParams = requestParams;
	}

	@Override
	public R get(Object key) {
		try {
			HttpUriRequest request = CommonHttpClient.postRequest(url, requestParams);
			SimpleResponse<String> response = client.textRespose(request);
			RestClient.assertResponse(response);
			return readResponse(response);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected abstract R readResponse(SimpleResponse<String> response) throws IOException;
}