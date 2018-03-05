package com.shaklee.shared.oauth;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Standard HTTP client for calling web services. This client is reentrant and
 * thread safe. It is also stateless, meaning there is no data shared between
 * calls.
 * 
 * @author Elli Albek
 */
@Component
public class CommonHttpClient {

	private static final Logger logger = LoggerFactory.getLogger(CommonHttpClient.class);

	@Value("${PoolingClientConnectionManager.MaxPerRoute:100}")
	int maxPerRoute;
	@Value("${PoolingClientConnectionManager.MaxConnTotal:200}")
	int maxConnTotal;
	@Value("${PoolingClientConnectionManager.Timeout:60}")
	int timeout;
	@Value("${SSLConnectionSocketFactory.enabled:false}")
	boolean sslConnectionFactoryEnabled;

	private volatile HttpClient client;

	public HttpClient client() throws IOException {
		if (client == null)
			client = createClient();

		return client;
	}

	private HttpClient createClient() throws IOException {
		HttpClientBuilder builder = HttpClientBuilder.create();
		builder.disableAuthCaching();
		builder.disableAutomaticRetries();
		builder.setDefaultCookieStore(new EmptyCookieStore());
		builder.disableCookieManagement();
		builder.disableRedirectHandling();
		// The app has very few routes, normally 2-3. Set to roughly one
		// connection per calling thread.
		builder.setMaxConnPerRoute(maxPerRoute);
		builder.setMaxConnTotal(maxConnTotal);

		// timeout
		int timeoutMs = timeout * 1000;
		RequestConfig.Builder requestBuilder = RequestConfig.custom();
		requestBuilder = requestBuilder.setConnectTimeout(timeoutMs);
		requestBuilder = requestBuilder.setConnectionRequestTimeout(timeoutMs);
		requestBuilder.setSocketTimeout(timeoutMs);
		builder.setDefaultRequestConfig(requestBuilder.build());

		if (sslConnectionFactoryEnabled)
			try {
				SSLContext sslContext = SSLContexts.custom().useTLS().build();

				SSLConnectionSocketFactory f = new SSLConnectionSocketFactory(sslContext,
						new String[] { "TLSv1", "TLSv1.1", "TLSv1.2" }, null, null);

				builder.setSSLSocketFactory(f);
			} catch (Exception e) {
				throw new IOException(e);
			}

		return builder.build();
	}

	private static class EmptyCookieStore implements CookieStore {

		@Override
		public void addCookie(Cookie c) {
		}

		@Override
		public void clear() {
		}

		@Override
		public boolean clearExpired(Date d) {
			return false;
		}

		@Override
		public List<Cookie> getCookies() {
			return Collections.emptyList();
		}
	}

	public static HttpUriRequest postRequest(String url, Map<String, String> query) {
		final HttpPost post = new HttpPost(url);
		writeParams(query, post);
		return post;
	}

	/**
	 * Simple post with text body, such as XML, json, etc.
	 */
	public SimpleResponse<String> post(String url, ContentType contentType, String body) throws IOException {
		final HttpPost post = createPostRequest(url, contentType, body);
		return textRespose(post);
	}

	public static HttpPost createPostRequest(String url, ContentType contentType, String body)
			throws UnsupportedEncodingException {
		final HttpPost post = new HttpPost(url);
		post.setHeader("Content-Type", contentType.getMimeType());
		StringEntity bodyEntity = new StringEntity(body);
		post.setEntity(bodyEntity);
		return post;
	}

	/**
	 * Simple put with text body, such as XML, json, etc.
	 */
	public SimpleResponse<String> put(String url, ContentType contentType, String body) throws IOException {
		final HttpPut put = new HttpPut(url);
		put.setHeader("Content-Type", contentType.getMimeType());
		StringEntity xmlEntity = new StringEntity(body);
		put.setEntity(xmlEntity);
		return emptyRespose(put);
	}

	/**
	 * Simple GET.
	 */
	public SimpleResponse<String> get(final String url) throws IOException {
		final HttpGet get = new HttpGet(url);
		return textRespose(get);
	}

	/**
	 * Simple GET FOR RESPONSE STREAM.
	 */
	public SimpleResponse<InputStream> getResStream(final String url) throws IOException {
		final HttpGet get = new HttpGet(url);
		return streamRespose(get);
	}

	/**
	 * Simple DELETE.
	 */
	public SimpleResponse<String> delete(final String url) throws IOException {
		final HttpDelete delete = new HttpDelete(url);
		return emptyRespose(delete);
	}

	/**
	 * Simple post and to return just response status
	 */
	public SimpleResponse<String> postWithEmptyResponse(String url, ContentType contentType, String body)
			throws IOException {
		final HttpPost post = createPostRequest(url, contentType, body);
		return emptyRespose(post);
	}

	public SimpleResponse<String> textRespose(HttpUriRequest request) throws IOException {
		HttpResponse response = client().execute(request);
		int status = response.getStatusLine().getStatusCode();
		// if (status != 200) {
		// throwHttpResponseException(request, response);
		// }

		String message = response.getStatusLine().getReasonPhrase();

		HttpEntity entity = response.getEntity();
		String mimeType = null, body = null;
		if (entity != null) {
			// Response r = new Response();
			body = EntityUtils.toString(entity);
			mimeType = entity.getContentType().getValue();
		}
		return new SimpleResponse<String>(status, message, mimeType, body);
	}

	/**
	 * Response without a body
	 */
	public SimpleResponse<String> emptyRespose(HttpUriRequest request) throws IOException {
		final HttpResponse response = client().execute(request);
		final int status = response.getStatusLine().getStatusCode();
		final String message = response.getStatusLine().getReasonPhrase();
		return new SimpleResponse<String>(status, message, null, null);
	}

	public SimpleResponse<InputStream> streamRespose(HttpUriRequest request) throws IOException {
		HttpResponse response = client().execute(request);
		int status = response.getStatusLine().getStatusCode();
		// if (status != 200) {
		// throwHttpResponseException(request, response);
		// }

		String message = response.getStatusLine().getReasonPhrase();

		HttpEntity entity = response.getEntity();
		String mimeType = null;
		InputStream stream = null;
		if (entity != null) {
			// Response r = new Response();
			stream = entity.getContent();
			mimeType = entity.getContentType().getValue();
		}
		return new SimpleResponse<InputStream>(status, message, mimeType, stream);
	}

	public static void throwHttpResponseException(SimpleResponse<String> response) throws HttpResponseException {
		String error = response.status + " " + response.message;
		if (response.body != null)
			error = error + ": " + response.body;
		throw new HttpResponseException(response.status, error);
	}

	protected static void assertStatus(SimpleResponse<String> response, int... allowedStatuses)
			throws HttpResponseException {
		int status = response.status;
		for (int i = 0; i < allowedStatuses.length; i++) {
			if (allowedStatuses[i] == status)
				return;
		}
		throwHttpResponseException(response);
	}

	/**
	 * Writes the request parameters as HTTP Post body.
	 */
	public static void writeParams(Map<String, String> params, HttpPost post) {

		List<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
		for (Map.Entry<String, String> e : params.entrySet()) {
			pairs.add(new BasicNameValuePair(e.getKey(), e.getValue()));
		}

		UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(pairs, Charset.forName("UTF-8"));

		post.setEntity(urlEncodedFormEntity);
	}

	public static class SimpleResponse<T> {
		public SimpleResponse(int status, String message, String mimeType, T body) {
			this.status = status;
			this.message = message;
			this.mimeType = mimeType;
			this.body = body;
		}

		public final int status;
		public final String message, mimeType;
		public final T body;
	}
}
