package com.shaklee.rulesets.healthQuestionaire.components;

import org.springframework.stereotype.Component;

import com.shaklee.healthPrint.data.Bundle;
import com.shaklee.healthPrint.data.HPRequest;
import com.shaklee.healthPrint.data.SKU;
import com.shaklee.healthPrint.data.SKUList;
import com.shaklee.healthPrint.data.TieredSKUList;
import com.shaklee.promo.Action;

/**
 * Action that aggregates all matches into bundles, removing duplicates. Must be
 * always run after the recommendation rules, before the price limit
 * rules/actions.
 * 
 * @author Elli Albek
 */
@Component
public class CreateBundles<Q> extends AbstractCreateBundles<Q, Bundle, SKU>
		implements Action<HPRequest<Q, Bundle, SKU>> {

	@Override
	protected SKUList<Bundle, SKU> createNewSKUList(SKUs<Bundle> addSku) {
		TieredSKUList bundle = new TieredSKUList();
		bundle.setBundle(addSku.bundle);
		return bundle;
	}
}