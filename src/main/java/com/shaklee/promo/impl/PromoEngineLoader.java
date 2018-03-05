package com.shaklee.promo.impl;

import static com.shaklee.promo.impl.InputValidationRuntimeException.assertRequired;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

import com.shaklee.common.util.CollectionFunctions.Function;
import com.shaklee.common.util.cache.CachingLoader;
import com.shaklee.common.util.cache.Loader;
import com.shaklee.promo.LocalizedData;
import com.shaklee.promo.Promo;
import com.shaklee.promo.PromoCollections;
import com.shaklee.promo.PromoDatabase;
import com.shaklee.promo.PromoExecutionInstance;
import com.shaklee.promo.PromoRequest;

public class PromoEngineLoader<T> {

	// @Autowired
	PromoDatabase<T> db;

	final CachingLoader<LocalizedData, PromoExecutionInstance<PromoRequest<T>>> cache;

	public PromoEngineLoader(PromoDatabase<T> db) {
		this.db = db;

		Function<LocalizedData, Object> cacheKeyGenerator = new Function<LocalizedData, Object>() {
			@Override
			public Object apply(LocalizedData cart) {
				return assertRequired(cart.getRulesetGroup(), "rulesetGroup") + '/'
						+ assertRequired(cart.getCountryCode(), "region");
			}
		};

		Loader<LocalizedData, PromoExecutionInstance<PromoRequest<T>>> loader = new Loader<LocalizedData, PromoExecutionInstance<PromoRequest<T>>>() {

			@Override
			public PromoExecutionInstance<PromoRequest<T>> get(LocalizedData key) {
				return loadPromoExecutionInstance(key, null);
			}
		};

		cache = new CachingLoader<LocalizedData, PromoExecutionInstance<PromoRequest<T>>>(loader, 60, cacheKeyGenerator, true);
	}

	public PromoExecutionInstance<PromoRequest<T>> loadPromoExecutionInstance(PromoRequest<T> cart) {
		if (cart.now == null)
			// no developer time overrides, use cache
			return cache.get(cart.promoGroupIdentifier).clone();
		else
			// developer overrides time, we cannot cache this rule set since it
			// is created for a different time (in the future or past)
			return loadPromoExecutionInstance(cart.promoGroupIdentifier, new Timestamp(cart.now));
	}

	protected PromoExecutionInstance<PromoRequest<T>> loadPromoExecutionInstance(LocalizedData request, Timestamp ts) {
		List<Promo<PromoRequest<T>>> promos = db.loadPromoRules(request, ts);
		// sort by priority
		Collections.sort(promos, PromoCollections.promoPriorityComparator);

		final PromoExecutionInstance<PromoRequest<T>> instance = new PromoExecutionInstance<PromoRequest<T>>();
		instance.promos = Collections.unmodifiableList(promos);
		instance.arbitrator = db.loadArbitrator(request);

		return instance;
	}
}
