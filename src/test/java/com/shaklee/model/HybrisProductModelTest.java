package com.shaklee.model;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

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
import com.shaklee.shared.oauth.OauthClientService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@PropertySource("classpath:application-local.properties")
public class HybrisProductModelTest {

	@Autowired
	HybrisProductService hybrisProductModel;

	@Autowired
	private Environment env;
	
	@Autowired
	private OauthClientService oauthClientService;
	
	@Test
	public void testGetProducts() throws Exception {

		
		List<Product> p =  hybrisProductModel.getProducts("US", Arrays.asList("89384"));

		assertNotNull(p);
		
		p = hybrisProductModel.getProducts("US", Arrays.asList("89384", "22067"));
		
		assertNotNull(p);
		
	}
	@Test
	public void testGetoAuthAccessToken()
			throws ClientProtocolException, IOException, JSONException {

		try {
				JSONObject jsonObject = oauthClientService.getoAuthAccessToken();
				assertNotNull(jsonObject);
				String access_token="";
				String token_type="";
				if(jsonObject != null)
				{
					access_token = jsonObject.getString("access_token");
					token_type = jsonObject.getString("token_type");
				}
				assertNotNull(access_token);
				assertNotNull(token_type);
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetProductsForProductService()throws ClientProtocolException, IOException, JSONException {
		
		String hybrisUrl = env.getProperty("hybrisUrl");
		String uri1 = "/shakleeintegration/v2/shaklee";
		String uri1_multipleProducts = "/products?codes=";
		
		JSONObject jsonResponse = null;
		
		String country = "US";
		
		String restUrl = null;
		
		String[] productCodes={"20282","20435","20652","20732"};
		
		String codes = "";
		if(productCodes != null && productCodes.length !=0)
		  codes=String.join(",", productCodes);
		
		restUrl = hybrisUrl + uri1 + country + uri1_multipleProducts + codes+ "&" + getAccessTokenAndTokenType();
		
		CloseableHttpClient httpclient = getHttpClient();
		
		if (httpclient != null) {
			
			try
			{
				HttpGet httpGet = new HttpGet(restUrl);
				HttpResponse response1 = httpclient.execute(httpGet);
				HttpEntity responseEntity = response1.getEntity();
				
				if (responseEntity != null) {
					String pResponse = EntityUtils.toString(responseEntity);
					jsonResponse = new JSONObject(pResponse);
					assertNotNull(jsonResponse);
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
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
