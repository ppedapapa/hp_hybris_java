package com.shaklee.model;

import static com.shaklee.promo.JsonTestUtils.assertJson;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
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

	@Test
	public void testGetProducts() throws Exception {

		
		List<Product> p =  hybrisProductModel.getProducts("US", "89384");

		assertNotNull(p);
		
		p = hybrisProductModel.getProducts("US", "89384", "22067");
		
		assertNotNull(p);
		
	}

}
