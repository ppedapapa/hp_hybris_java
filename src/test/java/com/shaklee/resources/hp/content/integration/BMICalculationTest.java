package com.shaklee.resources.hp.content.integration;

import static com.shaklee.promo.QuestionsTestutils.callQuestions;
import static com.shaklee.promo.QuestionsTestutils.createQuestions;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shaklee.Application;
import com.shaklee.resources.HealthQuestionnaireResource;
import com.shaklee.resources.hp.content.AbstractContentTest;
import com.shaklee.rulesets.healthQuestionaire.Questions;
import com.shaklee.rulesets.healthQuestionaire.Questions.Gender;
import com.shaklee.rulesets.healthQuestionaire.Questions.HealthGoal;
import com.shaklee.shared.validation.InputValidationException;

/**
 * Test class for score calculations such as BMI, lifestyle and diet scores.
 * 
 * @author ekoca
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "emre")
public class BMICalculationTest extends AbstractContentTest {

	@Autowired
	HealthQuestionnaireResource resource;

	@Test
	public void testBMIScore() throws InputValidationException, JSONException {
		Questions questions = createQuestions(20, Gender.M);
		questions.health_goals = Arrays.asList(HealthGoal.OVERALL);

		JSONObject response = callQuestions(resource, questions);
		final JSONArray contents = assertContent(response);
		for (int i = 0; i < contents.length(); i++) {
			JSONObject obj = contents.getJSONObject(i);
			assertEquals("hp-under-age", obj.getString("key"));
		}
	}

	@Test
	public void testMinimumDesirableWeightScore() throws InputValidationException, JSONException {
		Questions questions = createQuestions(20, Gender.M);
		questions.health_goals = Arrays.asList(HealthGoal.OVERALL);

		JSONObject response = callQuestions(resource, questions);
		final JSONArray contents = assertContent(response);
		for (int i = 0; i < contents.length(); i++) {
			JSONObject obj = contents.getJSONObject(i);
			assertEquals("hp-under-age", obj.getString("key"));
		}
	}

	@Test
	public void testMaximumDesirableWeightScore() throws InputValidationException, JSONException {
		Questions questions = createQuestions(20, Gender.M);
		questions.health_goals = Arrays.asList(HealthGoal.OVERALL);

		JSONObject response = callQuestions(resource, questions);
		final JSONArray contents = assertContent(response);
		for (int i = 0; i < contents.length(); i++) {
			JSONObject obj = contents.getJSONObject(i);
			assertEquals("hp-under-age", obj.getString("key"));
		}
	}
}
