package com.shaklee.resources;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.shaklee.entity.Product;
import com.shaklee.shared.oauth.OauthClientService;

@Component
public class HybrisProductService {

	@Autowired
	private Environment env;
	
	@Autowired
	private OauthClientService oauthClientService;

	static String hybrisUrl;

	final static String uri1 = "/shakleeintegration/v2/shaklee";

	final static String uri_products = "/products/";

	final static String uri1_multipleProducts = "/products?codes=";

	final static String uri2 = "?fields=BASIC";

	final static String uri2_fields = "&fields=BASIC";

	final static String uri3 = "&lang=";

	private static Logger logger = LoggerFactory.getLogger(HybrisProductService.class);

	public List<Product> getProducts(String countryCode, Collection<String> skus)
			throws ClientProtocolException, JSONException, IOException {

		hybrisUrl = env.getProperty("hybrisUrl");

		if (skus == null)
			return null;

		// TODO: Refactore the below code once certificate is added to
		// www.shakleedev.com
		CloseableHttpClient httpclient = getHttpClient();
		
		String oAuthCredentials = getAccessTokenAndTokenType();

		String restUrl = null;
		if (skus.size() == 1) {
			restUrl = hybrisUrl + uri1 + countryCode + uri_products + skus.stream().findFirst().get() + uri2 + "&" + oAuthCredentials;
		} else {
			restUrl = hybrisUrl + uri1 + countryCode + uri1_multipleProducts + String.join(",", skus) + uri2_fields + "&" + oAuthCredentials;

		}

		if (httpclient != null) {
			try {
				HttpGet httpGet = new HttpGet(restUrl);

				HttpResponse response1 = httpclient.execute(httpGet);
				HttpEntity responseEntity = response1.getEntity();

				if (response1.getStatusLine().getStatusCode() != 200
						&& response1.getStatusLine().getStatusCode() != 201)
					return null;

				if (responseEntity != null) {

					String response = EntityUtils.toString(responseEntity);
					JSONObject jsonResponse = new JSONObject(response);
					ObjectMapper mapper = new ObjectMapper();

					if (skus.size() == 1) {

						List<Product> p = Arrays.asList(mapper.readValue(response, Product.class));
						return p;
					} else {
						List<Product> p = mapper.readValue(jsonResponse.getString("products"),
								new TypeReference<List<Product>>() {
								});
						return p;
					}

				}
			} catch (IOException e) {
				logger.debug("Could not get join skus from hybris: " + restUrl, e);

				return null;
			}
		}
		return null;

	}

	public List<Product> getMembershipSkus(String country, String l)  {
		// TODO: Refactore the below code once certificate is added to
		// www.shakleedev.com

		hybrisUrl = env.getProperty("hybrisUrl");

		CloseableHttpClient httpclient = getHttpClient();

		String uri_membership = "getMembershipProducts";
		
		String oAuthCredentials = getAccessTokenAndTokenType();

		String restUrl = null;
		if (l != null && country != null)
			restUrl = hybrisUrl + uri1 + country + uri_products + uri_membership + uri2 + uri3 + l + '_' + country + "&" + oAuthCredentials;

		else
			restUrl = hybrisUrl + uri1 + country + uri_products + uri_membership + uri2 + "&" + oAuthCredentials;

		if (httpclient != null) {
			try {
				HttpGet httpGet = new HttpGet(restUrl);

				HttpResponse response1 = httpclient.execute(httpGet);

				if (response1.getStatusLine().getStatusCode() != 200
						&& response1.getStatusLine().getStatusCode() != 201)
					return null;

				HttpEntity responseEntity = response1.getEntity();
				if (responseEntity != null) {

					String response = EntityUtils.toString(responseEntity);
					JSONObject jsonResponse = new JSONObject(response);
					ObjectMapper mapper = new ObjectMapper();

					List<Product> p = mapper.readValue(jsonResponse.getString("products"),
							new TypeReference<List<Product>>() {
							});
					return p;
				}
			} catch (IOException | JSONException e) {
				logger.debug("Could not get membership skus from hybris: " + restUrl, e);

				return null;
			}

		}

		return null;
	}

	public List<String> getJoinSkus(String country) {
		// TODO: Refactore the below code once certificate is added to
		// www.shakleedev.com

		hybrisUrl = env.getProperty("hybrisUrl");

		CloseableHttpClient httpclient = getHttpClient();

		String uri_join = "getJoinSkus?fields=SKU";

		String oAuthCredentials = getAccessTokenAndTokenType();
		
		String restUrl = hybrisUrl + uri1 + country + uri_products + uri_join + "&" + oAuthCredentials;

		if (httpclient != null) {
			try {
				HttpGet httpGet = new HttpGet(restUrl);

				HttpResponse response1;

				response1 = httpclient.execute(httpGet);

				HttpEntity responseEntity = response1.getEntity();

				if (response1.getStatusLine().getStatusCode() != 200
						&& response1.getStatusLine().getStatusCode() != 201)
					return null;

				if (responseEntity != null) {

					String response = EntityUtils.toString(responseEntity);
					JSONObject jsonResponse = new JSONObject(response);
					ObjectMapper mapper = new ObjectMapper();

					List<JoinSKUResponse> p = mapper.readValue(jsonResponse.getString("products"),
							new TypeReference<List<JoinSKUResponse>>() {
							});

					List<String> productSkus = new ArrayList<String>();

					for (JoinSKUResponse joinSKU : p) {
						productSkus.add(joinSKU.sku);
					}
					return productSkus;
				}
			} catch (IOException | JSONException e) {
				logger.debug("Could not get join skus from hybris: " + restUrl, e);

				return null;
			}

		}

		return null;
	}

	public static class JoinSKUResponse {
		public String sku;
	}

	public Map<String, List<String>> getPacks(String country) {
		// TODO: Refactore the below code once certificate is added to
		// www.shakleedev.com

		hybrisUrl = env.getProperty("hybrisUrl");

		CloseableHttpClient httpclient = getHttpClient();

		String uri_packs = "getCustomizableBundles";

		String oAuthCredentials = getAccessTokenAndTokenType();
		
		String restUrl = hybrisUrl + uri1 + country + uri_products + uri_packs + uri2 + "&" + oAuthCredentials;

		if (httpclient != null) {
			try {

				HttpGet httpGet = new HttpGet(restUrl);

				HttpResponse response1;

				response1 = httpclient.execute(httpGet);

				if (response1.getStatusLine().getStatusCode() != 200
						&& response1.getStatusLine().getStatusCode() != 201)
					return null;

				HttpEntity responseEntity = response1.getEntity();
				if (responseEntity != null) {

					String response = EntityUtils.toString(responseEntity);
					JSONObject jsonResponse = new JSONObject(response);
					ObjectMapper mapper = new ObjectMapper();

					List<PackResponse> p = mapper.readValue(jsonResponse.getString("customizableBundles"),
							new TypeReference<List<PackResponse>>() {
							});

					Map<String, List<String>> packs = new HashMap<>();

					for (PackResponse pack : p) {
						packs.put(pack.sku, pack.defaultProductOptions);
					}

					// List<String> productSkus = new ArrayList<String>();

					// for (JoinSKUResponse joinSKU : p) {
					// productSkus.add(joinSKU.sku);
					// }
					return packs;
				}
			} catch (IOException | JSONException e) {
				logger.debug("Could not get pack from hybris : " + restUrl, e);
				return null;
			}

		}

		return null;
	}

	public static class PackResponse {
		public String sku;
		public List<String> defaultProductOptions;
	}

	private CloseableHttpClient getHttpClient() {
		SSLContextBuilder builder = new SSLContextBuilder();
		try {
			builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());

			SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(builder.build(),
					NoopHostnameVerifier.INSTANCE);

			Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
					.register("http", new PlainConnectionSocketFactory()).register("https", sslConnectionSocketFactory)
					.build();
			PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
			cm.setMaxTotal(100);
			CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory)
					.setConnectionManager(cm).build();
			return httpclient;
		} catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException e) {

			logger.debug("Error: ", e);

			return null;
		}
	}
	
	public String getAccessTokenAndTokenType()
	{
		try{
			JSONObject jsonObject = oauthClientService.getoAuthAccessToken();
			
			String access_token="";
			String token_type="";
			if(jsonObject != null)
			{
				access_token = jsonObject.getString("access_token");
				token_type = jsonObject.getString("token_type");
			}
			return "access_token="+access_token+"&token_type="+token_type;
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "";
	}

}
