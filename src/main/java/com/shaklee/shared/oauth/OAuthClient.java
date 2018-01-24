package com.shaklee.shared.oauth;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.shaklee.shared.oauth.CommonHttpClient.SimpleResponse;

/**
 * Simple fixed values auth token generator.
 * 
 * @author Elli Albek
 */
public class OAuthClient implements ObjectSource<String> {

	private static final Logger logger = Logger.getLogger(OAuthClient.class);

	CommonHttpClient client;
	private final HttpUriRequest request;
	/** Factor that is applied to expiration time **/
	private final int expirationFactor;

	public final String clientID;

	/**
	 * Create client with typical oAuth parameters.
	 * 
	 * @param expirationFactor
	 *            0-1.0 a factor that is applied on expiration time. EG 0.8
	 *            means that the expiration will be cut down by 20%. It is used
	 *            for creating safety margins around token expiration times.
	 */
	public OAuthClient(CommonHttpClient client, String url, String client_id, String client_secret,
			String refresh_token, float expirationFactor) {

		this.client = client;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("grant_type", "refresh_token");
		this.clientID = client_id;
		params.put("client_id", client_id);
		params.put("client_secret", client_secret);
		params.put("refresh_token", refresh_token);

		request = CommonHttpClient.postRequest(url, params);
		this.expirationFactor = (int) (expirationFactor * 1000);
	}

	@Override
	public ExpiringObject<String> get() throws IOException {
		final SimpleResponse<String> response = client.textRespose(request);
		CommonHttpClient.assertStatus(response, 200);

		try {
			JSONObject body = new JSONObject(response.body);
			String token = body.getString("access_token");
			long expires = System.currentTimeMillis() + expirationFactor * body.getInt("expires_in");

			if (logger.isDebugEnabled())
				logger.debug("Created new oAuth token, expires at " + new Date(expires));

			return new ExpiringObject<String>(token, expires);
		} catch (JSONException e) {
			throw new RuntimeException(e);
		}
	}
}