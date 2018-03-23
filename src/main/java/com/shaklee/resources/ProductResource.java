package com.shaklee.resources;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shaklee.shared.oauth.OauthClientService;

@Controller
public class ProductResource {
	
	@Autowired
	private OauthClientService oauthClientService;
	
	@Autowired
	private Environment env;
	
	private static Logger logger = LoggerFactory.getLogger(ProductResource.class);
	
	static String hybrisUrl;

	final static String uri1 = "/shakleeintegration/v2/shaklee";

	final static String uri1_multipleProducts = "/products?codes=";
	
	
	@RequestMapping("/getproducts")
	public JSONObject getProducts(@RequestParam(value="productCodes") String[] productCodes,HttpSession session,HttpServletRequest request, HttpServletResponse response) 
			throws ClientProtocolException, IOException, JSONException {
		
		hybrisUrl = env.getProperty("hybrisUrl");
		JSONObject jsonResponse = null;
		
		String country = (String) session.getAttribute("country");
		
		String restUrl = null;
		
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
					return jsonResponse;
				}
			}
			catch (Exception e) {
				logger.debug("Could not get getProducts from hybris: " + restUrl, e);

				return null;
			}
		}
		return null;
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

			logger.debug("Error: ", e);

			return null;
		}
	}
}
