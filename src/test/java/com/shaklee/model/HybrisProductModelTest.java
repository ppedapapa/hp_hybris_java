package com.shaklee.model;

import static com.shaklee.promo.JsonTestUtils.assertJson;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
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
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import com.shaklee.Application;
import com.shaklee.entity.Product;
import com.shaklee.resources.HybrisProductService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@PropertySource("classpath:application-local.properties")
public class HybrisProductModelTest {

	@Autowired
	HybrisProductService hybrisProductModel;

	@Autowired
	private Environment env;
	
	@Test
	public void testGetProducts() throws Exception {

		
		List<Product> p =  hybrisProductModel.getProducts("US", Arrays.asList("89384"));

		assertNotNull(p);
		
		p = hybrisProductModel.getProducts("US", Arrays.asList("89384", "22067"));
		
		assertNotNull(p);
		
	}
	@Test
	public void getoAuthAccessToken()
			throws ClientProtocolException, IOException, JSONException {

		String hybrisOAuthUrl = env.getProperty("hybrisOAuthUrl");
		 String uri = "/shakleeintegration/oauth/token";
		JSONObject jsonResponse = null;

		try {

			// TODO: Refactore the below code once certificate is added to
			// www.shakleedev.com
			CloseableHttpClient httpclient = getHttpClient();

			
			String restUrl = null;
			
			restUrl = hybrisOAuthUrl + uri;
			
			if (restUrl != null) {
				HttpPost httpPost = new HttpPost(restUrl);
				List<BasicNameValuePair> nameValuePairs = new ArrayList<BasicNameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("client_id", env.getProperty("client_id")));
                nameValuePairs.add(new BasicNameValuePair("client_secret", env.getProperty("client_secret")));
                nameValuePairs.add(new BasicNameValuePair("grant_type", env.getProperty("grant_type")));
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                //httpPost.setHeader("Content-type", "x-www-form-urlencoded");
                
                
				HttpResponse response1 = httpclient.execute(httpPost);
				if(response1.getStatusLine().getStatusCode()==307)
				{
					String LocationHeader = response1.getFirstHeader("location").getValue();
					CloseableHttpClient httpclient1 = getHttpClient();
					HttpPost httppost2 = new HttpPost(LocationHeader);
					List<BasicNameValuePair> nameValuePairs1 = new ArrayList<BasicNameValuePair>();
	                nameValuePairs1.add(new BasicNameValuePair("username", env.getProperty("username")));
	                nameValuePairs1.add(new BasicNameValuePair("password", env.getProperty("password")));
	                
	                httppost2.setEntity(new UrlEncodedFormEntity(nameValuePairs));
					response1 = httpclient1.execute(httppost2);
				}
				HttpEntity responseEntity = response1.getEntity();
				if (responseEntity != null) {

					String response = EntityUtils.toString(responseEntity);
					jsonResponse = new JSONObject(response);
					assertNotNull(jsonResponse);
					String access_token="";
					String token_type="";
					if(jsonResponse != null)
					{
						access_token = jsonResponse.getString("access_token");
						token_type = jsonResponse.getString("token_type");
					}
					assertNotNull(access_token);
					assertNotNull(token_type);
				}
				
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
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

			e.printStackTrace();

			return null;
		}
	}

}
