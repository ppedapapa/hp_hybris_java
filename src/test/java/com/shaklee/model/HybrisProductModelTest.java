package com.shaklee.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.shaklee.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HybrisProductModelTest {
	
	@Autowired
	HybrisProductModel hybrisProductModel;
	
	 @Test
	  public void testGetProducts() throws Exception {
		 
		 hybrisProductModel.getProducts();
	 }

}
