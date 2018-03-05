package com.shaklee.resources.hq;

import static com.shaklee.healthPrint.data.Bundle.CONSIDER;
import static com.shaklee.healthPrint.data.Bundle.TIER_1;
import static com.shaklee.healthPrint.data.Bundle.TIER_2;
import static com.shaklee.healthPrint.data.Bundle.TIER_3;
import static com.shaklee.promo.QuestionsTestutils.assertBundleContains;
import static com.shaklee.promo.QuestionsTestutils.assertBundleMissing;
import static com.shaklee.promo.QuestionsTestutils.callQuestions;
import static com.shaklee.promo.QuestionsTestutils.createQuestions;

import java.util.Arrays;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.shaklee.Application;
import com.shaklee.resources.HealthQuestionnaireResource;
import com.shaklee.rulesets.healthQuestionaire.Questions;
import com.shaklee.rulesets.healthQuestionaire.Questions.DietaryRestriction;
import com.shaklee.rulesets.healthQuestionaire.Questions.Gender;
import com.shaklee.rulesets.healthQuestionaire.Questions.Pregnant;
import com.shaklee.shared.validation.InputValidationException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "local")
public class BaseKitTest {

	@Autowired
	HealthQuestionnaireResource resource;

	@Test
	public void testKids() throws InputValidationException, JSONException {
		// male
		Questions q = createQuestions(10, Gender.M);
		q.language = "es";

		q.pregnant = Pregnant.NO;
		// q.health_goals = Arrays.asList(Questions.HealthGoal.OVERALL);
		JSONObject r = callQuestions(resource, q);
		assertBundleContains(r, TIER_1, "20002");
		assertBundleContains(r, TIER_2, "20076");
		assertBundleContains(r, TIER_3, "21261");
		assertBundleContains(r, CONSIDER, "21264");

		// kosher
		q.dietary_restrictions = Arrays.asList(Questions.DietaryRestriction.KOSHER);
		q.gender = Gender.F;
		r = callQuestions(resource, q);

		assertBundleContains(r, TIER_1, "20001");
		assertBundleContains(r, TIER_2, "21261");
		assertBundleContains(r, TIER_3, "20096");
		assertBundleContains(r, CONSIDER, "21267");
	}

	//@Test
	public void testPregnant() throws InputValidationException, JSONException {
		// male
		Questions q = createQuestions(41, Gender.F);
		q.height_inches = 70;
		q.weight_lbs = 200;

		// under 26 bmi
		JSONObject r;
		callNotPregnant(q);
		q.pregnant = Pregnant.PMS;
		callNotPregnant(q);

		// 27 bmi
		q.pregnant = Pregnant.PREGNANT;
		r = callQuestions(resource, q);
		assertBundleContains(r, TIER_1, "21214");
		assertBundleContains(r, TIER_2, "20491");
		assertBundleMissing(r, TIER_3, "21261");

		//
		// // female
		// q.gender = Gender.F;
		// r = callQuestions(q);
		// assertBundleContains(r, TIER_1, "20290");
		// assertBundleContains(r, TIER_2, "20284");
		//
		// q.is_pregnant = false;
		// r = callQuestions(q);
		// assertBundleContains(r, TIER_1, "20290");
		// assertBundleContains(r, TIER_2, "20284");
		// assertBundleContains(r, TIER_3, "21294");
	}

	//@Test
	public void testKosher() throws InputValidationException, JSONException {
		// male
		Questions q = createQuestions(20, Gender.M);
		q.dietary_restrictions = Arrays.asList(DietaryRestriction.KOSHER);
		q.pregnant = null; // Pregnant.NO;
		assertKosherDefault(q);

		q.pregnant = Pregnant.NO;
		assertKosherDefault(q);

		q.pregnant = Pregnant.PMS;
		assertKosherDefault(q);
	}

	void assertKosherDefault(Questions q) throws InputValidationException, JSONException {
		JSONObject r = callQuestions(resource, q);
		assertBundleContains(r, TIER_1, "89402");
		assertBundleContains(r, TIER_2, "89404");
		assertBundleContains(r, TIER_3, "89403");
	}

	void callNotPregnant(Questions q) throws InputValidationException, JSONException {
		JSONObject r = callQuestions(resource, q);
		assertBundleMissing(r, TIER_1, "21217");
		assertBundleMissing(r, TIER_2, "21217");
		assertBundleMissing(r, TIER_3, "21217");

		assertBundleMissing(r, TIER_1, "20288");
		// assertBundleMissing(r, TIER_2, "20283");
		assertBundleMissing(r, TIER_3, "22077");
	}
}
