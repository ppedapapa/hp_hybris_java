package com.shaklee.rulesets.healthQuestionaire.components;

import static com.shaklee.healthPrint.components.BundleUtils.getSkus;

import java.util.Collection;

import org.springframework.stereotype.Component;

import com.shaklee.healthPrint.data.Bundle;
import com.shaklee.healthPrint.data.HPRequest;
import com.shaklee.promo.Action;
import com.shaklee.promo.basic.AbstractComponent;

/**
 * Reduce the bundles to stay under a minimum price. Excess products from the
 * ultimate bundle go to consider category.
 * 
 * @author Elli Albek
 * @author Emre Koca
 */
@Component
public class ConsiderDoesNotContainTiers extends AbstractComponent<Object> implements Action<HPRequest<?, Bundle, ?>> {

	public float tier_1_max, tier_2_max, tier_3_max, price_step;

	@Override
	public void exec(HPRequest<?, Bundle, ?> q) {
		if (q.bundles == null || q.bundles.isEmpty())
			// no recommendations
			return;

		removeAll(q);
	}

	public static void removeAll(HPRequest<?, Bundle, ?> q) {
		final Collection<?> tier1 = getSkus(q.bundles, Bundle.TIER_1);
		final Collection<?> tier2 = getSkus(q.bundles, Bundle.TIER_2);
		final Collection<?> tier3 = getSkus(q.bundles, Bundle.TIER_3);
		final Collection<?> consider = getSkus(q.bundles, Bundle.CONSIDER);

		// remove SKUs that are already in tiers from consider
		// even though consider was added tier 3 leftovers, it may have
		// duplicates with tier 3 from simple recommendations.

		consider.removeAll(tier1);
		consider.removeAll(tier2);
		consider.removeAll(tier3);
	}
}