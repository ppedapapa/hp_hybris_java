package com.shaklee.model;

import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HybrisProductModel {

	static {
		javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(new javax.net.ssl.HostnameVerifier() {
			public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
				if (hostname.endsWith("shakleedev.com")) {
					return true;
				}
				return false;
			}
		});
	}

	public void getProducts() {
		final String uri = "https://www.shakleedev.com:9002/shakleeintegration/v2/shakleeUS/products/89384?fields=DEFAULT";
		
		URL url;
		try {
			RestTemplate restTemplate = new RestTemplate();
	        String quote = restTemplate.getForObject(uri, String.class);
	        System.out.println(quote.toString());

		} catch (Exception e) {
		
		}
	}

}
