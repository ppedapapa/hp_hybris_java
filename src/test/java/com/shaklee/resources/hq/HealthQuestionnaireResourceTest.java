package com.shaklee.resources.hq;

import static com.shaklee.healthPrint.data.Bundle.CONSIDER;
import static com.shaklee.healthPrint.data.Bundle.TIER_1;
import static com.shaklee.healthPrint.data.Bundle.TIER_2;
import static com.shaklee.healthPrint.data.Bundle.TIER_3;
import static com.shaklee.rulesets.healthQuestionaire.Questions.HealthGoal.BONE;
import static com.shaklee.promo.QuestionsTestutils.assertThatListContains;
import static com.shaklee.promo.QuestionsTestutils.assertThatListDoesNotContain;
import static com.shaklee.promo.QuestionsTestutils.createQuestions;
import static com.shaklee.promo.QuestionsTestutils.getByValue;
import static com.shaklee.promo.JsonTestUtils.assertFound;
import static com.shaklee.promo.JsonTestUtils.assertJson;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shaklee.Application;
import com.shaklee.DAO.UserDataStorageDAO;
import com.shaklee.healthPrint.data.Bundle;
import com.shaklee.resources.HealthQuestionnaireResource;
import com.shaklee.resources.HealthQuestionnaireResource.DebugHQResponse;
import com.shaklee.resources.HealthQuestionnaireResource.MultipleHealthProfilesResponse;
import com.shaklee.resources.HealthQuestionnaireResource.StorageRequest;
import com.shaklee.resources.HealthQuestionnaireResource.UserRequestForGetAllHealthPrints;
import com.shaklee.promo.PromoTestUtils;
import com.shaklee.rulesets.healthQuestionaire.Questions;
import com.shaklee.rulesets.healthQuestionaire.Questions.DietaryRestriction;
import com.shaklee.rulesets.healthQuestionaire.Questions.Gender;
import com.shaklee.rulesets.healthQuestionaire.Questions.HealthGoal;
import com.shaklee.rulesets.healthQuestionaire.Questions.Pregnant;
import com.shaklee.shared.validation.InputValidationException;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@WithMockUser(username = "AC12345-1")
public class HealthQuestionnaireResourceTest {
	
	  @Autowired
	    private MockMvc mvc;


	@Autowired
	HealthQuestionnaireResource resource;
	
	@Autowired
	UserDataStorageDAO userDataStorageDAO;

	//@Autowired
	//PublicHealthQuestionnaireResource phr;

	//@Autowired
	//ChallengeTrackerDAO dao;

	//@Autowired
	//HQReport hqReport;
	
	
	// @Test
	
	public void testGoldKit() throws InputValidationException {
		// male
		Questions q = createQuestions(51, Gender.M);
		JSONObject r = callQuestions(q);
		assertBundleContains(r, TIER_1, "20290");
		assertBundleContains(r, TIER_2, "20284");
		assertBundleContains(r, TIER_3, "21293");

		// female
		q.gender = Gender.F;
		r = callQuestions(q);
		assertBundleContains(r, TIER_1, "20290");
		assertBundleContains(r, TIER_2, "20284");

		q.pregnant = Pregnant.NO;
		r = callQuestions(q);
		assertBundleContains(r, TIER_1, "20290");
		assertBundleContains(r, TIER_2, "20284");
		assertBundleContains(r, TIER_3, "21294");
	}

	// @Test
	public void testGoals() throws InputValidationException {
		// male
		Questions q = createQuestions(51, Gender.M);
		q.health_goals = Arrays.asList(BONE);
		JSONObject r = callQuestions(q);
		assertBundleContains(r, TIER_1, "21217");
		// removed dues to price limit, moved to consider
		assertBundleContains(r, TIER_2, "21217");
		// assertBundleContains(r, TIER_3, "21217");
		assertBundleContains(r, CONSIDER, "21214");

		// added from TIER_2
		assertBundleContains(r, CONSIDER, "21217");

		q.health_goals = Arrays.asList(BONE);
		r = callQuestions(q);
		// assertBundleContains(r, TIER_1, "50916");
		// assertBundleContains(r, TIER_2, "50916");
		// assertBundleContains(r, TIER_3, "50916");
		assertBundleContains(r, CONSIDER, "50916");

		// missing the first (BONE)
		// TODO: Once there are rules for all 3, remove this
		assertBundleMissing(r, TIER_1, "21217");
		assertBundleMissing(r, TIER_2, "21217");
		assertBundleMissing(r, TIER_3, "21217");
		assertBundleMissing(r, CONSIDER, "21214");
	}

	// @Test
	public void testKosher() throws InputValidationException {
		// male
		Questions q = createQuestions(51, Gender.M);
		q.health_goals = Arrays.asList(HealthGoal.AGING);
		JSONObject r = callQuestions(q);
		// assertBundleContains(r, TIER_1, "21501");
		// assertBundleContains(r, TIER_2, "21501");
		// assertBundleContains(r, TIER_3, "21501");
		assertBundleContains(r, CONSIDER, "21501");
		// non kosher product that does not have a substitution
		assertBundleContains(r, TIER_2, "20284");

		q.dietary_restrictions = Arrays.asList(DietaryRestriction.KOSHER);

		r = callQuestions(q);

		// substitutions
		assertBundleMissing(r, TIER_1, "21501");
		assertBundleMissing(r, TIER_2, "21501");
		assertBundleMissing(r, TIER_3, "21501");

		// assertBundleContains(r, TIER_1, "22911");
		assertBundleContains(r, TIER_2, "22911");
		assertBundleContains(r, TIER_3, "22911");

		// no substitution
		assertBundleMissing(r, TIER_2, "20284");
	}

	// @Test
	public void vegan() throws InputValidationException {
		// male
		Questions q = createQuestions(51, Gender.M);
		q.health_goals = Arrays.asList(HealthGoal.AGING);
		q.dietary_restrictions = Arrays.asList(DietaryRestriction.VEGETERIAN);
		JSONObject r = callQuestions(q);
		// TIER_1
		assertBundleContains(r, CONSIDER, "21501");
		assertBundleContains(r, CONSIDER, "20186");
	}

	// @Test
	public void glutenFree() throws InputValidationException, JSONException {
		// male
		Questions q = createQuestions(51, Gender.M);
		q.health_goals = Arrays.asList(HealthGoal.AGING);
		q.dietary_restrictions = Arrays.asList(DietaryRestriction.GLUTEN);
		JSONObject r = callQuestions(q);

		assertBundleContains(r, CONSIDER, "21501");

		// gluten message
		PromoTestUtils.assertMessage("HQ_RESTRICTIONS", "gluten_free", r);
	}

	// @Test
	public void testBMI() throws InputValidationException {
		// male
		Questions q = createQuestions(51, Gender.M);
		q.height_inches = 70;
		q.weight_lbs = 200;

		// under 26 bmi
		JSONObject r = callQuestions(q);
		assertBundleMissing(r, TIER_1, "21263");
		assertBundleMissing(r, TIER_2, "21263");
		assertBundleMissing(r, TIER_3, "89280");

		// 27 bmi

		q.weight_lbs = 220;
		r = callQuestions(q);
		assertBundleContains(r, TIER_1, "21263");
		assertBundleContains(r, TIER_2, "21263");
		assertBundleMissing(r, TIER_3, "89280");

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
	public void testUpsertForMulesoftLead() throws JSONException, InputValidationException {

		StorageRequest re2 = new StorageRequest();
		Questions q1 = new Questions();
		q1.age = 20;
		q1.is_guest = true;
		q1.country_code = "US";
		re2.email = "rita.carol.taylor@gmail.com";
		re2.first_name = "test2";
		re2.last_name = "test2";
		re2.questions = q1;
		re2.referrer_id = "ZV68946";
		re2.opt_in = true;
		
		re2.host_name = "test2.com";
		setGet(re2);
	}

	@Test
	public void testUpsert() throws JSONException, InputValidationException {

		// 1. AG80332, 2. AG80348, 3. ZV68946
		deleteTestData();
		// The report count should be 0
		// MC - new user
		StorageRequest re = new StorageRequest();
		Questions q = new Questions();
		q.age = 100;
		re.questions = q;
		re.referrer_id = "AG80348";
		re.questions.is_guest = false;
		re.opt_in = true;
		re.first_name = "test2";
		re.last_name = "test2";
		re.referrer_code = "TEST_FB";
		setGet(re);
		
		// The report count should be 1
		/*
		// MC - exiting user, should insert again
		StorageRequest re0 = new StorageRequest();
		Questions q0 = new Questions();
		q0.age = 100;
		re0.questions = q0;
		re0.questions.is_guest = false;
		re0.opt_in = true;
		setGet(re0, "AG80332");
		// The report count should be 1
	
		// PWS Guest/Join - email who exists in EXT_USER, with ginger-mojo
		// old user
		// AG80348
		// should insert the record
		StorageRequest re2 = new StorageRequest();
		Questions q1 = new Questions();
		q1.age = 20;
		q1.is_guest = true;
		q1.country_code = "US";
		re2.email = "ldflasdlf@fdsfwe.com";
		re2.first_name = "test2";
		re2.last_name = "test2";
		re2.questions = q1;
		re2.referrer_id = "ZV68946";
		re2.opt_in = true;
		
		re2.host_name = "test2.com";
		setGet(re2, "AG80332");
		// The report count should be 1
		
		// PWS Guest/join - new email without ginger-mojo
		// should insert the record
		// if already exists will update count
		StorageRequest re4 = new StorageRequest();
		Questions q4 = new Questions();
		q4.age = 50;
		q4.is_guest = true;
		re4.questions = q4;
		re4.referrer_id = "AG80332";

		re4.email = "testerPWSTest1@email.com";
		re4.first_name = "PWSTest1";
		re4.last_name = "PWSTest1";
		re4.referrer_code = "TEST_FB";
		setGet(re4, null);

		// PWS Guest/join - new email with ginger-mojo
		StorageRequest re4_1 = new StorageRequest();
		Questions q4_1 = new Questions();
		q4_1.age = 50;
		q4_1.is_guest = true;
		re4_1.questions = q4;
		re4_1.referrer_id = "AG80332";
		re4_1.email = "testerPWSTest2@email.com";
		re4_1.first_name = "PWSTest2";
		re4_1.last_name = "PWSTest2";

		setGet(re4_1, "AG80348");

		// Shaklee.com Guest/Join - refererId will be null, with ginger-mojo
		StorageRequest re3 = new StorageRequest();
		Questions q2 = new Questions();
		q2.age = 50;
		q2.is_guest = true;
		re3.email = "testerPWSTest3@email.com";
		re3.first_name = "PWSTest3";
		re3.last_name = "PWSTest3";
		re3.questions = q2;
		setGet(re3, "ZV68946");

		// Shaklee.com Guest/Join - refererId will be null, without ginger-mojo
		StorageRequest re5 = new StorageRequest();
		Questions q5 = new Questions();
		q5.age = 50;
		q5.is_guest = true;
		re5.email = "testerPWSTest4@email.com";
		re5.first_name = "test";
		re5.last_name = "test";
		re5.questions = q5;
		setGet(re5, null);

		// Shaklee.com Guest/Join -existing user, refererId will be null,
		// without ginger-mojo
		// AG80332
		StorageRequest re6 = new StorageRequest();
		Questions q6 = new Questions();
		q6.age = 50;
		q6.is_guest = true;
		re6.email = "iwerk@ewr.com";
		re6.first_name = "test";
		re6.last_name = "test";
		re6.questions = q6;
		re6.host_name = "www.shaklee2.com";
		setGet(re6, null);

		// Shaklee.com Guest/Join -existing user, refererId will be null, with
		// ginger-mojo
		// AG80348
		StorageRequest re7 = new StorageRequest();
		Questions q7 = new Questions();
		q7.age = 50;
		q7.is_guest = true;
		re7.email = "ldflasdlf@fdsfwe.com";
		re7.first_name = "test";
		re7.last_name = "test";
		re7.questions = q7;
		setGet(re7, "AG80332");
		*/
		deleteTestData();

	}

	private void deleteTestData() {

		userDataStorageDAO.delete(null, "testerpwstest1@email.com");

		userDataStorageDAO.delete(null, "testerpwstest2@email.com");

		userDataStorageDAO.delete(null, "testerpwstest3@email.com");

		userDataStorageDAO.delete(null, "testerpwstest4@email.com");

		userDataStorageDAO.delete(null, "testerpwstest5@email.com");

		userDataStorageDAO.delete(null, "testerpwstest6@email.com");

		// Test data 1

		userDataStorageDAO.delete("WN09078-1", null);

		// Test data 2 ( email of Test data 1)

		userDataStorageDAO.delete(null, "ldflasdlf@fdsfwe.com");

		userDataStorageDAO.delete(null, "gurpiarsi3dhu@yahoo.com");

		userDataStorageDAO.delete(null, "iwerk@ewr.com");

		userDataStorageDAO.delete(null, "test@test1.com");

	}

	private String setGet(StorageRequest req) {
		
		try
		{
			//StorageResponse response = resource.update(req, userId);
	
			ResultActions actions = mvc.perform(MockMvcRequestBuilders.post("https://local.shakleedev.com:8443/services/hp/questions/update")
					.contentType(MediaType.APPLICATION_JSON)
					.content(new ObjectMapper().writeValueAsString(req)))
				.andExpect(status().isOk());
			
	         String responseString = actions.andReturn().getResponse().getContentAsString();
	          
	          //JSONObject r = response.toJSON();
			// System.out.println(r.toString(2));
	
			// assertJson(r, 0, "status");
			 
			JSONObject json = new JSONObject(responseString);
			
			System.out.println(json.toString());
				
			return json.getString("healthProfileId");
		}
		catch (Exception e)
		{
			
		}
		return null;
	}

	//@Test
	public void testhealthPrintCompleted() throws JSONException {
		/*
		try {
			deleteTestData();
			// Test data - new user
			StorageRequest re = new StorageRequest();
			Questions q = new Questions();
			q.age = 100;
			re.questions = q;
			re.referrer_id = "AG80348";
			re.questions.is_guest = false;
			re.opt_in = true;
			re.first_name = "test2";
			re.last_name = "test2";
			String healthPrintId = setGet(re, "AG80332");

			//QuestionsResponse response = resource.getByHealthProfileId(healthPrintId);

			Assert.assertNotNull(response.data != null ? response.data.questions : null);

			// status should be good
			StatusResponse response1 = resource.healthPrintCompleted("FA53210", healthPrintId);

			Assert.assertEquals(response1.status, 0);

			// should throw InputValidationError
			StatusResponse response2 = resource.healthPrintCompleted("FA53210", null);

			Assert.assertNull(response2.status == 0 ? response2.status : null);

			// test for email
			StorageRequest re6 = new StorageRequest();
			Questions q6 = new Questions();
			q6.age = 50;
			q6.is_guest = true;
			re6.email = "test@test.com";
			re6.first_name = "test";
			re6.last_name = "test";
			re6.questions = q6;

			healthPrintId = setGet(re6, null);

			StatusResponse re4 = resource.healthPrintCompleted("BA23094", healthPrintId);

			Assert.assertEquals(re4.status, 0);
			deleteTestData();

		} catch (InputValidationException e) {
			System.out.println(e.getLocalizedMessage());
		}*/

	}

	//@Test
	public void testGetData() throws JSONException, InputValidationException {

		deleteTestData();
		/*
		// Test data - new user
		StorageRequest re = new StorageRequest();
		Questions q = new Questions();
		q.age = 100;
		re.questions = q;
		re.referrer_id = "AG80348";
		re.questions.is_guest = false;
		re.opt_in = true;
		re.first_name = "test2";
		re.last_name = "test2";
		String healthProfileId = setGet(re, "AG80332");

		q.age = 80;
		re.referrer_id = "AG80348";
		re.questions.is_guest = false;
		re.opt_in = true;
		re.first_name = "test4";
		re.last_name = "test2";
		re.questions = q;

		String healthProfileId1 = setGet(re, "AG80332");

		QuestionsResponse response = resource.getByHealthProfileId(healthProfileId);
		System.out.println(response.toJSON().toString(2));
		Assert.assertNotNull(response.data != null ? response.data.questions : null);

		response = resource.getByHealthProfileId(healthProfileId1);

		if (response != null)
			System.out.println(response.toJSON().toString(2));
		Assert.assertNotNull(response.data != null ? response.data.questions : null);
		deleteTestData();
		*/

	}

	@Test
	public void testGetAllHealthPrints() throws JSONException, InputValidationException {

		deleteTestData();
		// Scenario 1: testing with shakleeId

		final String email = "test@test1.com";
		createStorageRequestForGuest(email, "ZV68934");
		
		UserRequestForGetAllHealthPrints req = new UserRequestForGetAllHealthPrints();
	
		req.email = email;

		MultipleHealthProfilesResponse response = resource.getAllHealthPrints(req, null);
		
		System.out.println(response.toString());
		Assert.assertEquals(response.status, 0);
	

	
		deleteTestData();

	}
	
	@Test
	public void testGetAllHealthPrintsForUserId() throws JsonProcessingException, Exception {

		deleteTestData();
		// Scenario 1: testing with shakleeId

		final String email = "test@test1.com";
		createStorageRequestForGuest(email, "ZV68934");
		
		UserRequestForGetAllHealthPrints req = new UserRequestForGetAllHealthPrints();
	
		req.email = null;

		//MultipleHealthProfilesResponse response = resource.getAllHealthPrints(req);
		ResultActions actions = mvc.perform(MockMvcRequestBuilders.post("https://local.shakleedev.com:8443/services/hp/getAllHealthPrints")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(req)))
			.andExpect(status().isOk());
		
         String responseString = actions.andReturn().getResponse().getContentAsString();
         
         JSONObject json = new JSONObject(responseString);
			
         
		System.out.println(responseString);
		assertJson(json, 0, "status");
		
	
		deleteTestData();

	}

	public JSONObject callQuestions(Questions questions) throws InputValidationException {
		com.shaklee.resources.HealthQuestionnaireResource.HQResponse resp = resource.runQuestionnaire(questions);
		if (resp instanceof DebugHQResponse) {
			DebugHQResponse debug = (DebugHQResponse) resp;
			if (debug.debug instanceof List<?>) {
				List<?> log = (List<?>) debug.debug;
				for (Object match : log)
					System.out.println(match);
			}
		}

		JSONObject r = resp.toJSON();
		assertJson(r, 0, "status");
		return r;
	}

	// @Test
	public void testAssociate() throws InputValidationException, JSONException {
		deleteTestData();
		StorageRequest re2_1 = new StorageRequest();
		Questions q1_1 = new Questions();
		q1_1.age = 20;
		q1_1.is_guest = true;
		re2_1.email = "ldflsdf@wrrew.com";
		re2_1.first_name = "test";
		re2_1.last_name = "test";
		re2_1.questions = q1_1;
		re2_1.referrer_id = "AG80348";
		re2_1.opt_in = true;
		setGet(re2_1);

		StorageRequest re3 = new StorageRequest();
		Questions q2 = new Questions();
		q2.age = 35;
		q2.is_guest = true;
		re3.email = "ldflsdf@wrrew.com";
		re3.first_name = "test4";
		re3.last_name = "test5";
		re3.questions = q2;
		re3.referrer_id = "AG80348";
		re3.opt_in = true;
		setGet(re3);

		
		deleteTestData();

	}

	public static void assertBundleContains(JSONObject response, Bundle bundle, String... skus) {
		JSONArray a = (JSONArray) assertFound(response, "bundles");
		JSONObject b = getByValue(a, "bundle", bundle.name());
		assertThat(b, not(nullValue()));
		assertThatListContains(bundle.name(), (JSONArray) assertFound(b, "skus"), skus);
	}

	public static void assertBundleMissing(JSONObject response, Bundle bundle, String... skus) {
		JSONArray a = (JSONArray) assertFound(response, "bundles");
		JSONObject b = getByValue(a, "bundle", bundle.name());
		if (b == null)
			return;
		// assertThat(b, not(nullValue()));
		assertThatListDoesNotContain(bundle.name(), (JSONArray) assertFound(b, "skus"), skus);
	}

	// @Test
	public void testHealthPrintReport() throws InputValidationException, JSONException {
		final String email = "test@test.shaklee.com";
		createStorageRequest(email, "AG80348");
		//HealthPrintReportResponse response = phr.getHealthPrintReport("AG80348");

		//System.out.println(response.toJSON().toString(2));
	}

	// @Test
	public void testHealthPrintSummaryReport() throws InputValidationException, JSONException {
		/*
		final String email = "test@test.shaklee.com";
		final String shakleeID = "ZV68934";
		createStorageRequest(email, shakleeID, "AG80348");
		HealthPrintReportResponse details = phr.getHealthPrintReport("AG80348");
		HPReportSummaryResponse summary = phr.getHealthPrintSummaryCountReport("AG80348");
		assertEquals(details.data.size(), summary.data.overall);
		System.out.println(summary.toJSON().toString(2));
		deleteTestData();
		summary = phr.getHealthPrintSummaryCountReport("BG81879");
		System.out.println(summary.toJSON().toString(2));
		*/
	}

	private String createStorageRequest(String email, String referrerId)
			throws JSONException, InputValidationException {
		StorageRequest re = new StorageRequest();
		re.email = email;
		Questions q = new Questions();
		q.age = 100;
		re.questions = q;
		q.health_goals = Arrays.asList(HealthGoal.AGING, HealthGoal.FITNESS, HealthGoal.JOINT);
		re.referrer_id = referrerId;
		re.questions.is_guest = false;
		re.opt_in = true;
		re.first_name = "test2";
		re.last_name = "test2";
		return setGet(re);
	}
	
	private String createStorageRequestForGuest(String email, String referrerId)
			throws JSONException, InputValidationException {
		StorageRequest re = new StorageRequest();
		re.email = email;
		Questions q = new Questions();
		q.age = 100;
		re.questions = q;
		q.health_goals = Arrays.asList(HealthGoal.AGING, HealthGoal.FITNESS, HealthGoal.JOINT);
		re.referrer_id = referrerId;
		re.questions.is_guest = true;
		re.opt_in = true;
		re.first_name = "test2";
		re.last_name = "test2";
		return setGet(re);
	}

}
