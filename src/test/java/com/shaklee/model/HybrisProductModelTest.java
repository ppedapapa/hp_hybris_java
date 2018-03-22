package com.shaklee.model;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
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
	
	//@Test
	public void testGetProducts() throws Exception {

		
		List<Product> p =  hybrisProductModel.getProducts("US", Arrays.asList("89384"));

		assertNotNull(p);
		
		p = hybrisProductModel.getProducts("US", Arrays.asList("89384", "22067"));
		
		assertNotNull(p);
		
	}
	//@Test
	public void getoAuthAccessToken()
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

}
