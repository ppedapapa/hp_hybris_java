package com.shaklee.resources.ce.integration;

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
import com.shaklee.resources.ce.AbstractContentTest;
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
public class ContentInvalidTest extends AbstractContentTest {

	@Autowired
	HealthQuestionnaireResource resource;

	@Test
	public void testScoreWithAgeLessThan13() throws InputValidationException, JSONException {
		Questions questions = createQuestions(10, Gender.M);
		questions.health_goals = Arrays.asList(HealthGoal.OVERALL);

		JSONObject response = callQuestions(resource, questions);
		final JSONArray contents = assertContent(response);
		for (int i = 0; i < contents.length(); i++) {
			JSONObject obj = contents.getJSONObject(i);
			assertEquals("hp-under-age", obj.getString("key"));
		}
	}

	@Test
	public void testScoreWithAgeBiggerThan100() throws InputValidationException, JSONException {
		Questions questions = createQuestions(100, Gender.F);
		questions.health_goals = Arrays.asList(HealthGoal.OVERALL);

		JSONObject response = callQuestions(resource, questions);
		final JSONArray contents = assertContent(response);
		for (int i = 0; i < contents.length(); i++) {
			JSONObject obj = contents.getJSONObject(i);
			assertEquals("hp-over-age", obj.getString("key"));
		}
		// BMI section
		// assertBMI(response, "bmi-underweight", 16.32653d, 56, 76);
		// life section
		// assertLifeStyle(response, 74, "lifestyle-70-89", "q-exercise-frequency-0",
		// "q-exercise-intensity-0",
		// "q-energy-0");
		// diet section
		// assertDiet(response, 49, "diet-13-49", "q-breakfast-times-0",
		// "q-meat-fish-0", "q-days-dairy-0");
	}
}
