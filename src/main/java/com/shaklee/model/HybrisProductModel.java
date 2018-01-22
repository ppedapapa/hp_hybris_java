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
	
	public void getProducts()
	{
	    final String uri = "https://www.shakleedev.com:9002/shakleeintegration/v2/shakleeUS/products/89384?fields=DEFAULT";

	    URL url;
		try {
			url = new URL(uri);
		
	    HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
	    
	    con.setRequestMethod("GET");
	    con.setRequestProperty("Content-Type", "application/json; charset=utf-8");
	    
		con.setRequestProperty("Access-Control-Allow-Credentials", "true");
		con.setRequestProperty("Access-Control-Allow-Origin", "*");
 
		System.out.println("\nSending request to URL : " + url);
		System.out.println("Response Code : " + con.getResponseCode());
		System.out.println("Response Message : " + con.getResponseMessage());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	   // ResponseEntity<String> result = con..getForEntity(uri, String.class);

	    // System.out.println(result);
	}

}
