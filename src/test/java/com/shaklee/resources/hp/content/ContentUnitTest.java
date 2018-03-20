package com.shaklee.resources.hp.content;

import static com.shaklee.promo.QuestionsTestutils.createQuestions;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shaklee.healthPrint.data.Bundle;
import com.shaklee.healthPrint.data.HPRequest;
import com.shaklee.healthPrint.data.SKU;
import com.shaklee.resources.HealthQuestionnaireResource;
import com.shaklee.resources.HealthQuestionnaireResource.HQResponse;
import com.shaklee.rulesets.healthQuestionaire.HQService;
import com.shaklee.rulesets.healthQuestionaire.Questions;
import com.shaklee.rulesets.healthQuestionaire.Questions.Gender;
import com.shaklee.shared.validation.InputValidationException;

/**
 * Testing Core Content flow without initialize entire SpringBoot Application
 * using abstract mock config.
 * 
 * @author ekoca
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ContentUnitTest.MockConfig.class)
@ActiveProfiles(profiles = "local")
public class ContentUnitTest extends AbstractContentTest {
	@Autowired
	HealthQuestionnaireResource resource;

	@Autowired
	HQService service;

	@Test
	public void test() throws InputValidationException {
		System.out.println(
				"Emre is testing real JUnit using the mocks without initilizing the entire SpringBoot Application :)");
		Questions question = createQuestions(10, Gender.M);
		HPRequest<Questions, Bundle, SKU> request = new HPRequest<Questions, Bundle, SKU>(question, null, null);
		when(resource.runQuestionnaire(question)).thenReturn(mockHQResponse());
		when(service.runPromoEngine(request)).thenReturn(mockHQRequest(request));
		HQResponse response = resource.runQuestionnaire(question);
		HPRequest<Questions, Bundle, SKU> results = service.runPromoEngine(request);
		System.out.println(response);
		System.out.println(results);
	}
}
