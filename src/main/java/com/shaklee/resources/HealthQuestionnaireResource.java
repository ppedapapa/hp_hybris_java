package com.shaklee.resources;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shaklee.DAO.UserDataStorageDAO.UserDataResponse;
import com.shaklee.common.util.JSONSerializer;
import com.shaklee.common.util.validation.BeanValidator;
import com.shaklee.healthPrint.data.Bundle;
import com.shaklee.healthPrint.data.HPRequest;
import com.shaklee.healthPrint.data.SKU;
import com.shaklee.healthPrint.data.SKUList;
import com.shaklee.healthPrint.data.TieredSKUList;
import com.shaklee.rulesets.healthQuestionaire.HQService;
import com.shaklee.rulesets.healthQuestionaire.Questions;
import com.shaklee.security.stereotypes.CurrentUser;
import com.shaklee.model.HealthQuestionnaireModel;
import com.shaklee.promo.PromoRequest;
import com.shaklee.promo.PromoRequest.PromoAction;
import com.shaklee.shared.data.Country2;
import com.shaklee.shared.util.StatusResponse;
import com.shaklee.shared.validation.InputValidationException;



@RestController
@RequestMapping("/services/hp")
public class HealthQuestionnaireResource {
	
	Logger logger = LoggerFactory.getLogger(HealthQuestionnaireResource.class);

	BeanValidator bval = BeanValidator.INSTANCE;

	@Autowired
	HQService service;

	
	@Autowired
	HealthQuestionnaireModel healthQuestionnaireModel;
	

	@RequestMapping(path = "/recommendations", method = POST)
	public HQResponse runQuestionnaire(@RequestBody Questions questions) throws InputValidationException {
		if (logger.isDebugEnabled())
			logger.debug("Request: " + JSONSerializer.toJacksonJaxbJson(questions, true));

		final long start = System.currentTimeMillis();
		try {
			// input validation
			bval.assertValid(questions);

			HPRequest<Questions, Bundle, SKU> req = new HPRequest<Questions, Bundle, SKU>(questions, null, null);
			req = service.runPromoEngine(req);
			String message = "No rules matched";
			if (req.response != null)
				message = "Matched " + req.response.size() + " rules";

			DebugHQResponse response = new DebugHQResponse(message);
			response.data = req.response;
			response.bundles = TieredSKUList.fromAbstractType(req.bundles);
			response.recommended = req.recommended;
			response.ms = System.currentTimeMillis() - start;
			response.debug = req.log;

			if (logger.isDebugEnabled())
				logger.debug("Response:\n" + JSONSerializer.toJacksonJaxbJson(response, true));

			if (logger.isDebugEnabled() && response.bundles != null) {

				ArrayList<String> skus = new ArrayList<String>();
				for (SKUList<?, SKU> sl : response.bundles) {
					for (SKU sku : sl.skus) {
						skus.add(sku.sku);
					}
				}
				Country2 country = Country2.valueOf(questions.country_code.toLowerCase());
				// response.debug_sku = productDAO.getDebug(country, skus);
			}
			// logActivity("RECOMMENDATIONS", response.message, questions, null); 
					//response.bundles);
			return response;
		} catch (InputValidationException e) {
			// handled by the framework.
			throw e;
		} catch (Throwable e) {
			String msg = "Rule engine crashed for user " + questions.user_id;
			try {
				String json = JSONSerializer.toJacksonJaxbJson(questions, true);
				msg = msg + " questions:\n" + json;
			} catch (Exception e1) {
				logger.error("Error generating json from questions (for logging purpose)", e1);
				// add the error string to the m
				msg = msg + " questions: " + e1.toString();
			}
			// logActivity("RECOMMENDATIONS","FAILED " + e.toString(), questions, e.toString());
			return (HQResponse) new HQResponse(StatusResponse.SERVER_ERROR)
					.message(PromoResource.toString("Rule Engine crashed", e).toString());
		}

	}
	
	@RequestMapping(path = "/testUserId", method = GET)
	public String testUserId(Principal principal)
	{
		String currentUserName = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    currentUserName = authentication.getName();
		}
		
		if (currentUserName == null)
			return "user_not_logged" ;
		
		return currentUserName ;
		
	}

	@RequestMapping(path = "/getAllHealthPrints", method = POST)
	public MultipleHealthProfilesResponse getAllHealthPrints(
			@RequestBody UserRequestForGetAllHealthPrints request, @CurrentUser User user)
	{
		if (user != null && user.getUsername() != null)
		{
			return healthQuestionnaireModel.getAllHealthPrints(user.getUsername(), request.email, request.downline_id);
			
		}
		return healthQuestionnaireModel.getAllHealthPrints(null, request.email, request.downline_id);
	}

	public static class UserRequestForGetAllHealthPrints {
		// @ShakleeID
		// public String user_id;
		public String email;
		public String downline_id;
	}
	
	public static final class MultipleHealthProfilesResponse extends StatusResponse {

		public List<UserDataResponse> data;

		public MultipleHealthProfilesResponse(List<UserDataResponse> data) {
			super(SUCCESS);
			this.data = data;
		}

		public MultipleHealthProfilesResponse(int status) {
			super(status);
		}
	}


	public static final class QuestionsResponse  {

		public String data;

		QuestionsResponse(String data) {
			this.data = data;
		}
	}
		
		public static class UserRequest {
			public String health_profile_id;
		}

		public static class BaseStorageRequest extends CommonStorageRequest {
			public String account_id;
			public String contact_id;
		}
		
		public static class CommonStorageRequest {
			public String email;

			@Size(max = 50)
			public String first_name;

			@Size(max = 50)
			public String last_name;

			@NotNull
			public Questions questions;
		}
		@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
		public static class HQResponse extends com.shaklee.shared.util.StatusResponse {

			public List<PromoAction> data;
			public Collection<TieredSKUList> bundles;
			public Bundle recommended;

			public HQResponse(String message) {
				super(SUCCESS, message);
			}

			public HQResponse(int status) {
				super(status);
			}
		}
		
		@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
		public static class DebugHQResponse extends HQResponse {

			public Object debug, debug_sku;
			public long ms;

			public DebugHQResponse(String message) {
				super(message);
			}
		}
}

