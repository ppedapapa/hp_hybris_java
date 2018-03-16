package com.shaklee.model;

import static com.shaklee.promo.JsonTestUtils.assertJson;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.shaklee.Application;
import com.shaklee.entity.Product;
import com.shaklee.resources.HybrisProductService;
import com.shaklee.rulesets.healthQuestionaire.ProductSkuKey;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "local")
public class HybrisProductModelTest {

	@Autowired
	HybrisProductService hybrisProductModel;

	//@Test
	public void testGetProducts() throws Exception {

		
		List<Product> p =  hybrisProductModel.getProducts("US", Arrays.asList("89384"));

		assertNotNull(p);
		
		
		
		p = hybrisProductModel.getProducts("US", Arrays.asList("89384", "22067"));
		
		assertNotNull(p);

		
		
	}
	
	@Test
	public void testGetMemebershipSKUs() throws Exception {

		
		List<Product> p =  hybrisProductModel.getMembershipSkus("US", "en");

		assertNotNull(p);

	}
	
	//@Test
	public void testJoinSKUs() throws Exception {

		
		List<String> p =  hybrisProductModel.getJoinSkus("US");
		assertNotNull(p);
		
		p = hybrisProductModel.getJoinSkus("CA");
		
		assertNotNull(p);
		

	}
	

	//@Test
	public void testPacks() throws Exception {

		
		Map<String, List<String>> p =  hybrisProductModel.getPacks("US");
		assertNotNull(p);
		
		//p = hybrisProductModel.getJoinSkus("CA");
		
		//assertNotNull(p);
		

	}

}
