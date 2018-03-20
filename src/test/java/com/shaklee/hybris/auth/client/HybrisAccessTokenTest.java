package com.shaklee.hybris.auth.client;

import static org.junit.Assert.assertTrue;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.junit.Test;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

/**
 * This is a test class for Hybris Auth
 * 
 * @author ekoca
 *
 */
public class HybrisAccessTokenTest {

//	@Test
	public void getAccessTokenViaSpringSecurityOAuthClient() {
		try {
			TrustStrategy acceptingTrustStrategy = (chain, authType) -> true;

			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(TrustSelfSignedStrategy.INSTANCE).build();

			SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
			

			CloseableHttpClient httpClient = HttpClients.custom().setSSLContext(sslContext)
					.setSSLHostnameVerifier(new HostnameVerifier() {

						@Override
						public boolean verify(String hostname, SSLSession session) {
							System.out.println(hostname);
							// TODO Auto-generated method stub
							return true;
						}
					}).setSSLSocketFactory(csf).build();
			
			
			SSLContextBuilder builder = new SSLContextBuilder();
			builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build(), new HostnameVerifier() {
				@Override
				public boolean verify(String hostname, SSLSession session) {
					System.out.println(hostname);
					// TODO Auto-generated method stub
					return true;
				}
			});
			
			final Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
			        .register("http", new PlainConnectionSocketFactory())
			        .register("https", sslsf)
			        .build();
			
			final PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(registry);
			connManager.setMaxTotal(10);
			

			CloseableHttpClient httpclient2 = HttpClients.custom().setSSLSocketFactory(sslsf).setConnectionManager(connManager).build();

			HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

			requestFactory.setHttpClient(httpclient2);
			
			//httpClient.execute(new HttpGet("https://www.shakleedev.com:9002/authorizationserver/oauth/token"));
			//
			ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
			resourceDetails.setClientId("ShakleeOAuthClient");
			resourceDetails.setClientSecret("admin");
			resourceDetails.setGrantType("client_credentials");
			resourceDetails.setAccessTokenUri("https://www.shakleedev.com:9002/authorizationserver/oauth/token");

			OAuth2RestTemplate oAuthRestTemplate = new OAuth2RestTemplate(resourceDetails);
			oAuthRestTemplate.setRequestFactory(requestFactory);
			
			OAuth2AccessToken token = oAuthRestTemplate.getAccessToken();
			System.out.println(oAuthRestTemplate.getResource());
			System.out.println(oAuthRestTemplate.getOAuth2ClientContext());
			System.out.println(token);

			assertTrue(token != null);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
