package com.shaklee.resources;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

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
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.shaklee.entity.Product;

@Component
public class HybrisProductService {

	@Autowired
	private Environment env;
	
	static String hybrisUrl;
	
	final static String uri1 = "/shakleeintegration/v2/shaklee";
	
	final static String uri_products = "/products/";
	
	final static String uri1_multipleProducts = "/products?codes=";
	
	final static String uri2 = "?fields=BASIC";
	
	final static String uri2_fields = "&fields=BASIC";
	

	private static Logger logger = LoggerFactory.getLogger(HybrisProductService.class);
	


	public List<Product> getProducts(String countryCode, String...sku) throws ClientProtocolException, IOException {
		
		hybrisUrl = env.getProperty("hybrisUrl");
		
		if(sku == null)
			return null;
		
		try {
			
			// TODO: Refactore the below code once certificate is added to www.shakleedev.com
			SSLContextBuilder builder = new SSLContextBuilder();
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

			String restUrl = null;
			if (sku.length == 1)
			{
				restUrl = hybrisUrl + uri1 + countryCode + uri_products + sku[0]+ uri2;
			}
			else
			{				
				restUrl = hybrisUrl + uri1+ countryCode + uri1_multipleProducts + String.join(",", sku)+ uri2_fields;
				
			}
			
			if(restUrl != null)
			{
				HttpGet httpGet = new HttpGet(restUrl);
				
				HttpResponse response1 = httpclient.execute(httpGet);
				HttpEntity responseEntity = response1.getEntity();
				if(responseEntity!=null) {
				  
					String  response = EntityUtils.toString(responseEntity);
				    ObjectMapper mapper = new ObjectMapper();
					List<Product> p = Arrays.asList(mapper.readValue(response, Product.class));
					return p;
	
				}
			}
			
		} catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
			logger.error("error connecting to Hybris: ", e);
		}
		
		return null;

	}

}
