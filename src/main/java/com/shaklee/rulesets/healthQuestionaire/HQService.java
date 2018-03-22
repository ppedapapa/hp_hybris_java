package com.shaklee.rulesets.healthQuestionaire;

import static com.shaklee.common.util.CollectionUtils.combine;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shaklee.healthPrint.data.HPRequest;
import com.shaklee.healthPrint.data.HealthPrintContentRequest;
import com.shaklee.promo.LocalizedDataImpl;
import com.shaklee.promo.PromoDatabase;
import com.shaklee.promo.PromoEngine;
import com.shaklee.promo.PromoExecutionInstance;
import com.shaklee.promo.PromoRequest;
import com.shaklee.promo.PromoRequest.Action;
import com.shaklee.promo.PromoRequest.PromoAction;
import com.shaklee.promo.impl.DefaultPromoDatabase;
import com.shaklee.promo.impl.PromoEngineLoader;
import com.shaklee.shared.data.Language;
import com.shaklee.shared.validation.InputValidationException;

/**
 * The main execution code, of HealthPrint.
 * 
 * @author Elli Albek
 */
@Component
public class HQService {

	// Logger logger = LoggerFactory.getLogger(PromoService.class);

	PromoDatabase<Questions> db;

	@Autowired
	PromoEngine<PromoRequest<Questions>> engine;

	PromoEngineLoader<Questions> loader;

	@Autowired
	public void setDb(DefaultPromoDatabase<Questions> db) {
		this.db = db;
		loader = new PromoEngineLoader<Questions>(db);
	}

	/**
	 * Get engine data, reduce by eligibility, run the promo engine.
	 * 
	 * @throws InputValidationException
	 */
	public <BundleType, ItemListType> HealthPrintContentRequest<Questions, BundleType, ItemListType> runPromoEngine(
			PromoRequest<Questions> r) throws InputValidationException {

		// Must override the request since there is one ruleset for US and CA.
		// The request to load the promo rules is always US, even if running for
		// CA. This is why it is separate than the requests that are used for
		// running the rules.
		// final PromoRequest<? extends AbstractLocalizedData> rulesDataRequest
		// = rulesDataRequest(r.request);

		// set some defaults
		if (r.request.language == null)
			r.request.language = Language.en.name();

		// First execution: questionnaire
		HPRequest<Questions, BundleType, ItemListType> request = new HPRequest<Questions, BundleType, ItemListType>(
				r.request, r.request, null);
		{
			// rulesDataRequest.request.rulesetGroup = "MC_HQ";
			PromoExecutionInstance<PromoRequest<Questions>> engineLogic = loader
					.loadPromoExecutionInstance(rulesDataRequest("MC_HQ"));
			// engine.runPromos(engineLogic, request);
		}

		// Second execution: promos on top of the questionnaire results
		HPRequest<Questions, BundleType, ItemListType> promosRequest = new HPRequest<Questions, BundleType, ItemListType>(
				r.request, r.request, null);
		{
			// rulesDataRequest.request.rulesetGroup = "HQ_PROMOS";
			PromoExecutionInstance<PromoRequest<Questions>> engineLogic = loader
					.loadPromoExecutionInstance(rulesDataRequest("HQ_PROMOS"));
			promosRequest.bundles = request.bundles;
			promosRequest.response = new ArrayList<PromoRequest.PromoAction>(2);
			// engine.runPromos(engineLogic, promosRequest);
		}

		/*
		 * Adding new flows called as Score and Content for current Health print
		 * services as an third and fourth execution.
		 */
		// Third execution: score results on top of the questionnaire results
		final HealthPrintContentRequest<Questions, BundleType, ItemListType> scoreRequest = new HealthPrintContentRequest<Questions, BundleType, ItemListType>(
				r.request, null, null);
		{
			PromoExecutionInstance<PromoRequest<Questions>> engineLogic = loader
					.loadPromoExecutionInstance(rulesDataRequest("HP_SCORE"));
			scoreRequest.bundles = request.bundles;
			scoreRequest.response = new ArrayList<PromoRequest.PromoAction>(2);
			engine.runPromos(engineLogic, scoreRequest);
		}

		/*
		 * Fourth execution: content keys on top of the questionnaire results
		 */
		final HealthPrintContentRequest<Questions, BundleType, ItemListType> contentRequest = new HealthPrintContentRequest<Questions, BundleType, ItemListType>(
				r.request, null, null);
		{
			PromoExecutionInstance<PromoRequest<Questions>> engineLogic = loader
					.loadPromoExecutionInstance(rulesDataRequest("HP_CONTENT"));
			contentRequest.bundles = request.bundles;
			// adding score results
			contentRequest.score = scoreRequest.score;
			contentRequest.response = new ArrayList<PromoRequest.PromoAction>(2);
			engine.runPromos(engineLogic, contentRequest);
		}

		// merge results
		final HealthPrintContentRequest<Questions, BundleType, ItemListType> results = new HealthPrintContentRequest<Questions, BundleType, ItemListType>(
				r.request, null, null);
		results.bundles = request.bundles;
		results.recommended = request.recommended;
		results.response = combine(combine(request.response, promosRequest.response), contentRequest.response);
		results.log = combine(combine(request.log, promosRequest.log), combine(scoreRequest.log, contentRequest.log));
		// adding filtered content data
		results.content = contentRequest.response.stream() // open the stream to reduce content data
				.map(promoAction -> promoAction.messages) // return the list of message actions
				.flatMap(List::stream) // transform array of messages to stream,
				.collect(Collectors.toList()); // collect stream back to List<Action>
		return results;
	}

	@Deprecated
	/**
	 * We will remove this method since it is deprecated with Java Streams API
	 * 
	 * @param response
	 * @return
	 */
	private List<Action> contentReducer(List<PromoAction> response) {
		List<Action> content = new ArrayList<Action>();
		for (final PromoAction promoAction : response) {
			content.addAll(promoAction.messages);
		}
		return content;
	}

	// public static <BundleType> HPRequest<Questions, BundleType, ?>
	// rulesDataRequest(Questions q) {
	// Questions locale = new Questions();
	// locale.country_code = "US";
	// locale.rulesetGroup = "MC_HQ";
	// return new HPRequest<Questions, BundleType, Object>(locale, null);
	// }

	public static <BundleType> HPRequest<Questions, BundleType, Object> rulesDataRequest(String rulesetGroup) {
		LocalizedDataImpl locale = new LocalizedDataImpl(null, "US", rulesetGroup);
		return new HPRequest<Questions, BundleType, Object>(null, locale, null);
	}
}