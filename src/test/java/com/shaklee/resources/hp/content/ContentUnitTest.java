package com.shaklee.resources.hp.content;

import static com.shaklee.promo.QuestionsTestutils.createQuestions;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shaklee.healthPrint.data.Bundle;
import com.shaklee.healthPrint.data.HealthPrintContentRequest;
import com.shaklee.healthPrint.data.SKU;
import com.shaklee.promo.basic.PromoMessage;
import com.shaklee.resources.HealthQuestionnaireResource;
import com.shaklee.resources.HealthQuestionnaireResource.HQResponse;
import com.shaklee.rulesets.healthQuestionaire.Questions;
import com.shaklee.rulesets.healthQuestionaire.Questions.Gender;
import com.shaklee.rulesets.healthQuestionaire.components.content.BMIScore;
import com.shaklee.rulesets.healthQuestionaire.components.content.MaximumDesirableWeightScore;
import com.shaklee.rulesets.healthQuestionaire.components.content.MinimumDesirableWeightScore;
import com.shaklee.shared.validation.InputValidationException;

/**
 * Testing Core Content flow without initializing entire SpringBoot Application
 * and no networks call either but it uses abstract mock config to mock services
 * and databases. If you need to mock more then you need to add more mock object
 * in MockConfig class.
 * 
 * If you need to do an integration test then don't use this test suite.
 * 
 * @author ekoca
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ContentUnitTest.MockConfig.class)
public class ContentUnitTest extends AbstractContentTest {

	@Autowired
	HealthQuestionnaireResource resource;

	private final Questions question = createQuestions(30, Gender.M);

	@Test
	public void testContentProvider() throws InputValidationException, JSONException {
		// init mock object for this test case
		System.out.println("###############################");
		System.out.println("Load the engine using mock objects without init entire spring boot application!");
		System.out.println("###############################");
		when(resource.runQuestionnaire(question)).thenReturn(mockHQResponse());

		// There must be at least four keys
		assertTrue(KEYS.length > 3);
		HQResponse response = resource.runQuestionnaire(question);
		assertEquals(0, response.status);
		System.out.println("Response: " + response);

		// Test if we have valid content
		final JSONObject json = response.toJSON();
		assertContent(json);

		// validate response
		assertContent(json);
		assertEquals(KEYS[0], ((PromoMessage) response.content.get(0)).key);
		assertSectionAndKeys(json, SECTION_NAME, 100, KEYS);
	}

	@Test
	public void testBmiCalculations() throws InputValidationException, JSONException {
		// init the request
		HealthPrintContentRequest<Questions, Bundle, SKU> request = mockHQRequest(question);

		// bmi
		BMIScore bmiScore = new BMIScore();
		bmiScore.exec(request);
		assertEquals(31.885218f, request.score.bmi, 0f);
		System.out.format("BMI score: %f \n", request.score.bmi);

		// min_desirable_weight
		MinimumDesirableWeightScore minScore = new MinimumDesirableWeightScore();
		minScore.exec(request);
		assertEquals(104, request.score.min_desirable_weight);
		System.out.format("min_desirable_weight score: %d \n", request.score.min_desirable_weight);

		// max_desirable_weight
		MaximumDesirableWeightScore maxScore = new MaximumDesirableWeightScore();
		maxScore.exec(request);
		assertEquals(140, request.score.max_desirable_weight);
		System.out.format("max_desirable_weight score: %d \n", request.score.max_desirable_weight);
	}
}
