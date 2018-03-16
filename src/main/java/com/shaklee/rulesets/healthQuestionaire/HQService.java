package com.shaklee.rulesets.healthQuestionaire;

import static com.shaklee.common.util.CollectionUtils.combine;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shaklee.healthPrint.data.HPRequest;
import com.shaklee.promo.LocalizedDataImpl;
import com.shaklee.promo.PromoDatabase;
import com.shaklee.promo.PromoEngine;
import com.shaklee.promo.PromoExecutionInstance;
import com.shaklee.promo.PromoRequest;
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
	public <BundleType, ItemListType> HPRequest<Questions, BundleType, ItemListType> runPromoEngine(
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
			request.bundles = request.bundles;
			request.response = new ArrayList<PromoRequest.PromoAction>(2);
			engine.runPromos(engineLogic, request);
		}

		// Second execution: promos on top of the questionnaire results
		/*HPRequest<Questions, BundleType, ItemListType> promosRequest = new HPRequest<Questions, BundleType, ItemListType>(
				r.request, r.request, null);
		{
			// rulesDataRequest.request.rulesetGroup = "HQ_PROMOS";
			PromoExecutionInstance<PromoRequest<Questions>> engineLogic = loader
					.loadPromoExecutionInstance(rulesDataRequest("HQ_PROMOS"));
			promosRequest.bundles = request.bundles;
			promosRequest.response = new ArrayList<PromoRequest.PromoAction>(2);
			engine.runPromos(engineLogic, promosRequest);
		}*/

		// merge results

		request.response = combine(request.response, request.response);
		request.log = combine(request.log, request.log);
		return request;
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