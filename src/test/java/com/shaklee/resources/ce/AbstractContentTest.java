package com.shaklee.resources.ce;

import static org.mockito.Mockito.mock;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;

import com.shaklee.DAO.UserDataStorageDAO;
import com.shaklee.DAOImpl.UserDAOImpl;
import com.shaklee.common.util.JsonLoader;
import com.shaklee.healthPrint.data.Bundle;
import com.shaklee.healthPrint.data.HPRequest;
import com.shaklee.healthPrint.data.HealthPrintContentRequest;
import com.shaklee.healthPrint.data.SKU;
import com.shaklee.model.HealthQuestionnaireModel;
import com.shaklee.promo.PromoEngine;
import com.shaklee.promo.PromoRequest;
import com.shaklee.promo.impl.DefaultPromoDatabase;
import com.shaklee.promo.impl.PromoLoader;
import com.shaklee.promo.util.PromoJsonLoader;
import com.shaklee.resources.HealthQuestionnaireResource;
import com.shaklee.resources.HealthQuestionnaireResource.HQResponse;
import com.shaklee.rulesets.healthQuestionaire.HQService;
import com.shaklee.rulesets.healthQuestionaire.Questions;

/**
 * Abstract Test Config Class that mocks the actual implementation using the
 * Mockito mock objects.
 *
 * @author ekoca
 */
public class AbstractContentTest {

	protected HQResponse mockHQResponse() {
		HQResponse response = new HQResponse(0);
		return response;
	}

	protected HealthPrintContentRequest<Questions, Object, Object> mockHQRequest(
			HPRequest<Questions, Bundle, SKU> request) {
		// HQResponse response = new HQResponse(0);
		final HealthPrintContentRequest<Questions, Object, Object> results = new HealthPrintContentRequest<>(
				request.request, null, null);
		return results;
	}

	protected static class MockConfig {
		@Bean
		@SuppressWarnings("unchecked")
		public PromoEngine<PromoRequest<Questions>> getPromoEngine() {
			return mock(PromoEngine.class);
		}

		@Bean
		public HQService getHQService() {
			return mock(HQService.class);
		}

		@Bean
		public DataSource getDataSource() {
			return mock(DataSource.class);
		}

		@Bean
		public HealthQuestionnaireResource getHealthQuestionnaireResource() {
			return mock(HealthQuestionnaireResource.class);
		}

		@Bean
		public UserDAOImpl getUserDAOImpl() {
			return mock(UserDAOImpl.class);
		}

		@Bean
		public HealthQuestionnaireModel getHealthQuestionnaireModel() {
			return mock(HealthQuestionnaireModel.class);
		}

		@Bean
		public UserDataStorageDAO getUserDataStorageDAO() {
			return mock(UserDataStorageDAO.class);
		}

		@SuppressWarnings("unchecked")
		@Bean
		public DefaultPromoDatabase<Questions> getDefaultPromoDatabase() {
			return mock(DefaultPromoDatabase.class);
		}

		@SuppressWarnings("unchecked")
		@Bean
		public PromoLoader<PromoRequest<Questions>> getPromoLoader() {
			return mock(PromoLoader.class);
		}

		@Bean
		public JsonLoader getJsonLoader() {
			return mock(JsonLoader.class);
		}

		@Bean
		public PromoJsonLoader getPromoJsonLoader() {
			return mock(PromoJsonLoader.class);
		}
	}
}