package com.shaklee.promo;

import static com.shaklee.common.util.CollectionFunctions.filter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shaklee.common.util.CollectionFunctions.Function;
import com.shaklee.common.util.CollectionFunctions.Predicate;
import com.shaklee.promo.impl.DefaultPromoDatabase;
import com.shaklee.promo.impl.PromoEngineLoader;
import com.shaklee.shared.validation.InputValidationException;

/**
 * The main execution code, which takes a request and modify it based on promos.
 * 
 * @author Elli Albek
 */
@Component
public class PromoService<T extends AbstractLocalizedData> {

	// Logger logger = LoggerFactory.getLogger(PromoService.class);

	DefaultPromoDatabase<PromoRequest<T>> db;

	@Autowired
	PromoEngine<PromoRequest<T>> engine;
	
	PromoEngineLoader<T> loader;

	@Autowired
	public void setDb(DefaultPromoDatabase<PromoRequest<T>> db) {
		this.db = db;
		loader = new PromoEngineLoader(db);
	}
	/**
	 * Reduce the promos list to the promos that the user is eligible for.
	 */
	List<Promo<PromoRequest<T>>> getEligibility(
			List<Promo<PromoRequest<T>>> promos, PromoRequest<T> request) {

		List<Integer> eligibilityGroupIDs = filter(promos,
				new Function<Promo<PromoRequest<T>>, Integer>() {
					@Override
					public Integer apply(Promo<PromoRequest<T>> promo) {
						return promo.eligibilityGroupID;
					}
				});

		if (eligibilityGroupIDs.isEmpty())
			// no eligibility conditions, match everything
			return promos;

		final List<Integer> groupMatches = db.loadEligibilityMatches(
				eligibilityGroupIDs, request.request);

		return filter(promos, new Predicate<Promo<PromoRequest<T>>>() {
			@Override
			public boolean accept(Promo<PromoRequest<T>> promo) {
				// promo has no eligibility
				return promo.eligibilityGroupID == null
				// promo has eligibility, user must be in it
						|| groupMatches.contains(promo.eligibilityGroupID);
			}
		});
	}

	/**
	 * Get engine data, reduce by eligibility, run the promo engine.
	 * 
	 * @throws InputValidationException
	 */
	public PromoRequest<T> runPromoEngine(PromoRequest<T> request)
			throws InputValidationException {
		// long time = System.nanoTime();
		request.request.rulesetGroup = "MC_PROMO";
		PromoExecutionInstance<PromoRequest<T>> engineLogic = loader.loadPromoExecutionInstance(request); 

		// logger.debug("loadPromoExecutionInstance " + (System.nanoTime() -
		// time)
		// / 1000f);
		// reduce promos by eligibility lists
		engineLogic.promos = getEligibility(engineLogic.promos, request);
		// logger.debug("loadPromoExecutionInstance getEligibility "
		// + (System.nanoTime() - time) / 1000f);

		// populate request with time value (if not overriden by callers)
		if (request.now == null)
			request = new PromoRequest<T>(request.request, request.promoGroupIdentifier,
					System.currentTimeMillis());

		// time = System.nanoTime();
		engine.runPromos(engineLogic, request);
		// logger.debug("runPromos " + (System.nanoTime() - time) / 1000f);
		return request;
	}
	
	public static <T> PromoRequest<T> rulesDataRequest(String rulesetGroup) {
		LocalizedDataImpl locale = new LocalizedDataImpl(null, "US", rulesetGroup);
		return new PromoRequest<T>(null, locale, null);
	}
}