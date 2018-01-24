package com.shaklee.shared.http.rest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.map.type.CollectionType;

//import com.shaklee.itrack.common.util.UTCDateUtils;
import com.shaklee.shared.oauth.CommonHttpClient;
import com.shaklee.shared.oauth.CommonHttpClient.SimpleResponse;

/**
 * Generic rest client for json based web services. Notice the services
 * automatically support lists without specifying it explicitly as a return type
 * (since this is not possible to create a class object for generic type).
 * 
 * For example, if a service returns a list of User objects, just send
 * User.class. This supports return types of both User and List<User>.
 * 
 * @author Elli Albek
 */
public class RestClient {

	protected final CommonHttpClient client;
	public static final ObjectMapper REST_JSON_MAPPER = createDefaultMapper();

	public RestClient(CommonHttpClient client) {
		this.client = client;
	}

	@SuppressWarnings("unchecked")
	public <T> T putJson(String url, Object request, Class<?> responseClass, String... headers) throws IOException {
		final HttpPut put = new HttpPut(url);
		setHeaders(put, headers);
		setJsonBody(request, put);
		return (T) executeRestRequest(responseClass, put);
	}

	@SuppressWarnings("unchecked")
	public <T> T getJson(String url, Object request, Class<?> responseClass, String... headers) throws IOException {
		final HttpGet put = new HttpGet(url);
		setHeaders(put, headers);
		return (T) executeRestRequest(responseClass, put);
	}

	@SuppressWarnings("unchecked")
	public <T> T deleteJson(String url, Object request, Class<?> responseClass, String... headers) throws IOException {
		final HttpDelete put = new HttpDelete(url);
		setHeaders(put, headers);
		return (T) executeRestRequest(responseClass, put);
	}

	@SuppressWarnings("unchecked")
	public <T> T postJson(String url, Object request, Class<?> responseClass, String... headers) throws IOException {
		final HttpPost put = new HttpPost(url);
		setHeaders(put, headers);
		setJsonBody(request, put);
		return (T) executeRestRequest(responseClass, put);
	}

	Object executeRestRequest(Class<?> responseClass, final HttpRequestBase put)
			throws IOException, HttpResponseException {
		SimpleResponse<String> response = client.textRespose(put);
		assertResponse(response);
		return deserializeResponse(responseClass, response.body);
	}

	Object deserializeResponse(Class<?> responseClass, String response) throws IOException {
		response = response.trim();
		if (response.length() > 0 && response.charAt(0) == '[')
			// array
			return deserializeListResponse(responseClass, response);
		else
			// object
			return REST_JSON_MAPPER.readValue(response, responseClass);
	}

	<T> List<T> deserializeListResponse(Class<T> responseClass, String response) throws IOException {
		CollectionType javaType = REST_JSON_MAPPER.getTypeFactory().constructCollectionType(List.class, responseClass);
		return REST_JSON_MAPPER.readValue(response, javaType);
	}

	void setJsonBody(Object request, final HttpEntityEnclosingRequestBase put)
			throws JsonGenerationException, JsonMappingException, IOException {
		put.setHeader("Content-Type", ContentType.APPLICATION_JSON.getMimeType());
		String body = REST_JSON_MAPPER.writeValueAsString(request);
		// JSONSerializer.toJacksonJaxbJson(request, false, false);
		StringEntity requestBody = new StringEntity(body);
		put.setEntity(requestBody);
	}

	void setHeaders(final HttpRequestBase request, String... headers) {
		if (headers != null && headers.length > 0) {
			for (int i = 0; i < headers.length; i += 2) {
				request.setHeader(headers[i], headers[i + 1]);
			}
		}
	}

	public static void assertResponse(SimpleResponse<String> response) throws HttpResponseException {
		if (response.status != 200)
			throw new RestException(response);
	}

	public static ObjectMapper createDefaultMapper() {
		ObjectMapper mapper = new ObjectMapper();
		// configure mapper
		mapper.configure(Feature.WRITE_DATES_AS_TIMESTAMPS, false);
		SimpleDateFormat parser = iso8601DateFormat();
		mapper.setDateFormat(parser);
		mapper.getSerializationConfig().setSerializationInclusion(Inclusion.NON_NULL);
		return mapper;
	}

	public static SimpleDateFormat iso8601DateFormat() {
		SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		parser.setTimeZone(TimeZone.getTimeZone("UTC"));
		return parser;
	}
}
