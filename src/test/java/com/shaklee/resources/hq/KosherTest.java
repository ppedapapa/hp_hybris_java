package com.shaklee.resources.hq;

import static com.shaklee.healthPrint.data.Bundle.TIER_1;
import static com.shaklee.healthPrint.data.Bundle.TIER_2;
import static com.shaklee.healthPrint.data.Bundle.TIER_3;

import java.util.Arrays;

import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.shaklee.Application;
import com.shaklee.resources.HealthQuestionnaireResource;
import com.shaklee.rulesets.healthQuestionaire.Questions;
import com.shaklee.rulesets.healthQuestionaire.Questions.Gender;
import com.shaklee.rulesets.healthQuestionaire.Questions.HealthGoal;
import com.shaklee.rulesets.healthQuestionaire.Questions.Pregnant;
import com.shaklee.shared.validation.InputValidationException;

import static com.shaklee.promo.QuestionsTestutils.callQuestions;
import static com.shaklee.promo.QuestionsTestutils.createQuestions;
import static com.shaklee.promo.QuestionsTestutils.assertBundleContains;
import static com.shaklee.promo.QuestionsTestutils.assertBundleMissing;

@Component
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "local")
public class KosherTest {

	@Autowired
	HealthQuestionnaireResource resource;

	@Test
	@WithMockUser(username = "AB00000-1")
	public void testAging() throws InputValidationException {
		// male
		Questions q = createQuestions(40, Gender.M);
		q.language = "es";

		q.pregnant = Pregnant.NO;
		q.health_goals = Arrays.asList(HealthGoal.AGING, HealthGoal.FITNESS, HealthGoal.JOINT);

		JSONObject r = callQuestions(resource, q);
		// Rule will match but it will not in this bundle due to price limit
		assertBundleMissing(r, TIER_1, "89402");
		// it must be in this bundle
		assertBundleMissing(r, TIER_2, "89402");
		// it should not be in this bundle
		assertBundleMissing(r, TIER_3, "89402");

		// kosher
		q.dietary_restrictions = Arrays.asList(Questions.DietaryRestriction.KOSHER);
		r = callQuestions(resource, q);

		// we should really check for 22911, but it is always over the $ limit
		assertBundleContains(r, TIER_1, "89402");
		assertBundleContains(r, TIER_2, "89404");
		assertBundleContains(r, TIER_3, "89403");
	}

	//@Test
	public void testEnergy() throws InputValidationException {
		// male
		Questions q = createQuestions(40, Gender.M);
		q.language = "es";

		q.pregnant = Pregnant.NO;
		q.health_goals = Arrays.asList(Questions.HealthGoal.ENERGY);

		JSONObject r = callQuestions(resource, q);
		assertBundleContains(r, TIER_2, "20158");
		assertBundleContains(r, TIER_3, "20158");

		// kosher
		q.dietary_restrictions = Arrays.asList(Questions.DietaryRestriction.KOSHER);
		r = callQuestions(resource, q);

		// we should really check for 22911, but it is always over the $ limit
		assertBundleContains(r, TIER_2, "22031");
		assertBundleContains(r, TIER_3, "22031");
	}
}
