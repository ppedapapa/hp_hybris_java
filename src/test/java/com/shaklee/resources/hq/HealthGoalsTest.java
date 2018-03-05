package com.shaklee.resources.hq;

import static com.shaklee.healthPrint.data.Bundle.CONSIDER;
import static com.shaklee.healthPrint.data.Bundle.TIER_1;
import static com.shaklee.healthPrint.data.Bundle.TIER_2;
import static com.shaklee.healthPrint.data.Bundle.TIER_3;
import static com.shaklee.rulesets.healthQuestionaire.Questions.HealthGoal.BONE;
import static com.shaklee.rulesets.healthQuestionaire.Questions.HealthGoal.FITNESS;
import static com.shaklee.rulesets.healthQuestionaire.Questions.HealthGoal.HEART;
import static com.shaklee.rulesets.healthQuestionaire.Questions.HealthGoal.OVERALL;
import static com.shaklee.rulesets.healthQuestionaire.Questions.HealthGoal.SLEEP;
import static com.shaklee.rulesets.healthQuestionaire.Questions.HealthGoal.WEIGHT;
import static com.shaklee.rulesets.healthQuestionaire.Questions.HealthGoal.PERFORMANCE;
import static com.shaklee.promo.QuestionsTestutils.assertBundleContains;
import static com.shaklee.promo.QuestionsTestutils.assertBundleMissing;
import static com.shaklee.promo.QuestionsTestutils.callQuestions;
import static com.shaklee.promo.QuestionsTestutils.createQuestions;

import java.io.IOException;
import java.util.Arrays;

import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.shaklee.Application;
import com.shaklee.common.util.JsonLoader;
import com.shaklee.resources.HealthQuestionnaireResource;
import com.shaklee.rulesets.healthQuestionaire.Questions;
import com.shaklee.rulesets.healthQuestionaire.Questions.DietaryRestriction;
import com.shaklee.rulesets.healthQuestionaire.Questions.Gender;
import com.shaklee.promo.JsonTestUtils;
import com.shaklee.shared.validation.InputValidationException;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HealthGoalsTest {

	@Autowired
	HealthQuestionnaireResource resource;

	JsonLoader loader = new JsonLoader();

	//@Test
	public void testWeightLoss() throws InputValidationException {
		// male
		Questions q = createQuestions(30, Gender.M);
		q.health_goals = Arrays.asList(WEIGHT);
		testWeightLoss(q);
		testCanadaWeightLoss(q);

		q.health_goals = Arrays.asList(OVERALL, WEIGHT);
		q.weight_lbs = 300;
		q.height_inches = 60;
		testWeightLoss(q);

		q.gender = Gender.F;
		JSONObject r = callQuestions(resource, q);

		assertBundleContains(r, TIER_1, "21263");
		assertBundleContains(r, TIER_2, "89426");
		// assertBundleContains(r, TIER_3, "22054");
		assertBundleContains(r, TIER_1, "21296"); // female

		assertBundleMissing(r, TIER_1, "21295"); // male
		// assertBundleMissing(r, TIER_3, "22000");

		q.age = 50;
		r = callQuestions(resource, q);

		assertBundleContains(r, TIER_1, "21298");
	}

	//@Test
	public void testWeightLossNuts() throws InputValidationException {
		// male
		Questions q = createQuestions(30, Gender.M);
		q.health_goals = Arrays.asList(WEIGHT);

		q.health_goals = Arrays.asList(OVERALL, WEIGHT);
		q.weight_lbs = 300;
		q.height_inches = 60;
		JSONObject r = callQuestions(resource, q);

		assertBundleMissing(r, TIER_1, "89280");
		assertBundleMissing(r, TIER_2, "89280");
		assertBundleContains(r, TIER_3, "89280");

		assertBundleMissing(r, TIER_1, "89426");
		assertBundleContains(r, TIER_2, "89426");
		assertBundleMissing(r, TIER_3, "89426");

		assertBundleContains(r, TIER_1, "21263");
		assertBundleMissing(r, TIER_3, "22031");
		// assertBundleContains(r,TIER_1, "21274"); only for soy free

		// assertBundleContains(r, TIER_3, "22000", "22051");

		q.dietary_restrictions = Arrays.asList(Questions.DietaryRestriction.NUTS);

		r = callQuestions(resource, q);

		assertBundleMissing(r, TIER_1, "89280");
		assertBundleMissing(r, TIER_2, "89280");
		assertBundleMissing(r, TIER_3, "89280");

		assertBundleMissing(r, TIER_1, "89384");
		assertBundleContains(r, TIER_2, "89384");
		assertBundleContains(r, TIER_3, "89384");

		assertBundleContains(r, TIER_3, "22031");

		assertBundleContains(r, TIER_1, "21263");
		// assertBundleContains(r,TIER_1, "21274"); soy free only
	}

	JSONObject testWeightLoss(Questions q) throws InputValidationException {
		JSONObject r = callQuestions(resource, q);
		assertBundleContains(r, TIER_1, "21263");
		assertBundleContains(r, TIER_2, "89426");
		// assertBundleContains(r, TIER_3, "22054");
		assertBundleContains(r, TIER_1, "21295"); // male
		assertBundleMissing(r, TIER_1, "21296"); // female
		return r;
	}

	JSONObject testCanadaWeightLoss(Questions q) throws InputValidationException {
		q.country_code = "CA";
		q.gender = Gender.F;
		JSONObject r = callQuestions(resource, q);
		assertBundleContains(r, TIER_1, "56261");
		assertBundleContains(r, TIER_2, "79426");
		assertBundleMissing(r, TIER_1, "57445"); // male
		assertBundleContains(r, TIER_1, "57440"); // female
		q.country_code = "US";
		q.gender = Gender.M;
		return r;
	}

	//@Test
	public void testActive() throws InputValidationException {
		// male
		Questions q = createQuestions(30, Gender.M);
		q.exercise_frequency = 3;
		q.exercise_intensity = 3;
		q.health_goals = Arrays.asList(OVERALL);
		testActive(q);

		q.health_goals = Arrays.asList(FITNESS, BONE);
		testActive(q);

		q.age = 45;
		{
			JSONObject r = callQuestions(resource, q);
			assertBundleContains(r, TIER_1, "20282", "21309", "20158", "21261");
			assertBundleContains(r, TIER_2, "89384", "20281", "21309", "20158");
			assertBundleContains(r, TIER_3, "89383", "20281", "21309", "20158");

			assertBundleContains(r, CONSIDER, "22054", "22000");
		}
	}

	//@Test
	public void testActiveAge40Bug943()
			throws InputValidationException, InstantiationException, IllegalAccessException, IOException {

		// US
		callMissingSkusMF("943_active_age_40_overlap_US.json", "20284");

		{
			// Canada
			Questions q = JsonTestUtils.loadJsonFile("943_active_age_40_overlap_CA.json", Questions.class,
					HealthGoalsTest.class, loader);
			JSONObject r = callQuestions(resource, q);
			assertBundleContains(r, TIER_2, "79350", "54309", "54158");
			assertBundleContains(r, TIER_3, "79350", "57880", "54309", "54158");
			assertBundleMissing(r, TIER_2, "57880");
			assertBundleMissing(r, TIER_3, "79351");
		}
	}

	//@Test
	public void testActiveAge50Bug944()
			throws IOException, InputValidationException, InstantiationException, IllegalAccessException {
		{
			// US
			Questions q = JsonTestUtils.loadJsonFile("944_active_age_50_overlap_US.json", Questions.class,
					HealthGoalsTest.class, loader);
			JSONObject r = callQuestions(resource, q);
			// 41+
			assertBundleContains(r, TIER_3, "89383");
			assertBundleContains(r, TIER_2, "89384");

			// 41-50
			assertBundleContains(r, TIER_1, "20282");

			// 51+
			assertBundleMissing(r, TIER_1, "20284");

			// 41+
			assertBundleContains(r, TIER_1, "21309", "20158");
			assertBundleContains(r, TIER_2, "20281", "21309", "20158");
			assertBundleContains(r, TIER_3, "20281", "21309", "20158");

			// 41-50
			assertBundleContains(r, TIER_1, "21261");

			// 13+
			assertBundleContains(r, CONSIDER, "22054", "22000");
		}
	}

	void callMissingSkusMF(final String jsonFileName, String... over40Skus)
			throws IOException, InstantiationException, IllegalAccessException, InputValidationException {
		Questions q = JsonTestUtils.loadJsonFile(jsonFileName, Questions.class, HealthGoalsTest.class, loader);

		JSONObject r = callQuestions(resource, q);
		assertMissingSKUsAllBundles(r, over40Skus);
		q.gender = Gender.F;
		r = callQuestions(resource, q);
		assertMissingSKUsAllBundles(r, over40Skus);
	}

	void assertMissingSKUsAllBundles(JSONObject r, String... over40Skus) {

		assertBundleMissing(r, TIER_1, over40Skus);
		assertBundleMissing(r, TIER_2, over40Skus);
		assertBundleMissing(r, TIER_3, over40Skus);
		assertBundleMissing(r, CONSIDER, over40Skus);
	}

	JSONObject testActive(Questions q) throws InputValidationException {
		JSONObject r = callQuestions(resource, q);

		assertBundleContains(r, CONSIDER, "22054");
		assertBundleContains(r, CONSIDER, "22000");

		assertBundleContains(r, TIER_1, "21309");
		assertBundleContains(r, TIER_2, "89384");
		assertBundleContains(r, TIER_3, "20281");
		// assertBundleContains(r, TIER_1, "20282"); // male

		assertBundleMissing(r, TIER_1, "20283"); // female
		assertBundleMissing(r, TIER_3, "22000");
		return r;
	}

	//@Test
	public void testOverall() throws InputValidationException {
		// male
		Questions q = createQuestions(30, Gender.M);
		q.exercise_frequency = 2;
		q.exercise_intensity = 2;
		q.health_goals = Arrays.asList(OVERALL);
		testOverall(q);

		// q.health_goals = Arrays.asList(FITNESS, BONE);
		// testOverall(q);

		//q.age = 51;
		{
			//JSONObject r = callQuestions(resource, q);
			//assertBundleContains(r, TIER_1, "20284");

			//assertBundleContains(r, TIER_2, "20284");

			//assertBundleContains(r, TIER_3, "21293");
		}
	}

	JSONObject testOverall(Questions q) throws InputValidationException {
		JSONObject r = callQuestions(resource, q);

		assertBundleContains(r, TIER_1, "20282");
		assertBundleContains(r, TIER_2, "20282");
		assertBundleContains(r, TIER_3, "21293");

		return r;
	}

	//@Test
	public void testHeart() throws InputValidationException {
		// male
		Questions q = createQuestions(30, Gender.M);
		// Weight loss
		// High BMI
		q.weight_lbs = 500;
		q.height_inches = 50;
		q.healthy_fats = 3;
		q.health_goals = Arrays.asList(HEART, WEIGHT);

		// q.health_goals = Arrays.asList(HEART);
		{
			JSONObject r = callQuestions(resource, q);
			assertBundleContains(r, CONSIDER, "22077", "22076", "22079", "22067");
		}

		q.health_goals = Arrays.asList(BONE, SLEEP, HEART);
		{
			JSONObject r = callQuestions(resource, q);
			assertBundleMissing(r, CONSIDER, "22076");
			assertBundleContains(r, TIER_1, "22077");
		}

	}

	/**
	 * Tests the combination of weight loss and nuts.
	 * 
	 * CON-879
	 */
	//@Test
	public void testWeighLossNuts() throws InputValidationException {
		Questions q = createQuestions(30, Gender.F);
		q.health_goals = Arrays.asList(WEIGHT);

		{
			// no nuts allergy
			JSONObject r = callQuestions(resource, q);
			q.dietary_restrictions = Arrays.asList(DietaryRestriction.NUTS);
			assertBundleMissing(r, TIER_3, "22031");
		}
		{
			// nuts: add the sku
			JSONObject r = callQuestions(resource, q);
			q.dietary_restrictions = Arrays.asList(DietaryRestriction.NUTS);
			assertBundleContains(r, TIER_3, "22031");
		}
	}
	
	
	//@Test
	public void testPerformance() throws InputValidationException {
		// male
		Questions q = createQuestions(30, Gender.M);
		q.health_goals = Arrays.asList(PERFORMANCE);
		testWeightLoss(q);
		
		
	}
	
	

}
