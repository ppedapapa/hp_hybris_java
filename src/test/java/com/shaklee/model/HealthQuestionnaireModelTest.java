package com.shaklee.model;

import org.apache.http.util.Asserts;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.shaklee.Application;
import com.shaklee.resources.HealthQuestionnaireResource.MultipleHealthProfilesResponse;

import junit.framework.Assert;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HealthQuestionnaireModelTest {
	
	@Autowired
	HealthQuestionnaireModel healthQuestionnaireModel;
	
	//@Test
	public void testGetAllHealthPrints()
	{
		MultipleHealthProfilesResponse r = healthQuestionnaireModel.getAllHealthPrints("FA53210-1", null, null);
		System.out.println(r.toJSON());
		Assert.assertEquals(r.status, 0);
	}

}
