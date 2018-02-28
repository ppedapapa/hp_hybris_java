package com.shaklee.model;

import org.apache.http.util.Asserts;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import com.shaklee.Application;
import com.shaklee.resources.HealthQuestionnaireResource.MultipleHealthProfilesResponse;
import com.shaklee.resources.HealthQuestionnaireResource.UserRequestForGetAllHealthPrints;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HealthQuestionnaireModelTest {
	
	@Autowired
	HealthQuestionnaireModel healthQuestionnaireModel;
	
	@Test
	public void testGetAllHealthPrints()
	{
		UserRequestForGetAllHealthPrints req = new UserRequestForGetAllHealthPrints();
		req.user_id = "AB00000-1";
		MultipleHealthProfilesResponse r = healthQuestionnaireModel.getAllHealthPrints(req);
		System.out.println(r.toJSON());
		
		
		Assert.assertEquals(r.status, 0);
	}
	
	@Test
	public void testGetAllHealthPrintsByHealthPrintId()
	{
		UserRequestForGetAllHealthPrints req = new UserRequestForGetAllHealthPrints();
		req.health_profile_id = "1gwcsi1ffz15b5yyiogqvghxf";
		req.user_id = "FA53210-1";
		MultipleHealthProfilesResponse r = healthQuestionnaireModel.getAllHealthPrints(req);
		System.out.println(r.toJSON());
		Assert.assertEquals(r.status, 0);
	}

}
