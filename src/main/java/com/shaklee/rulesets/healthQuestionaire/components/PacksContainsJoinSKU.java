package com.shaklee.rulesets.healthQuestionaire.components;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import com.shaklee.healthPrint.data.Bundle;
import com.shaklee.healthPrint.data.HPRequest;
import com.shaklee.healthPrint.data.SKU;
import com.shaklee.healthPrint.data.SKUList;
import com.shaklee.promo.Condition;
import com.shaklee.promo.basic.AbstractComponent;
import com.shaklee.rulesets.healthQuestionaire.Questions;
import com.shaklee.rulesets.healthQuestionaire.components.AddMembershipSku.JoinSKU;

/**
 * Checks that skus are not in any of the products.
 * 
 * @author Elli Albek
 */
@Component
public class PacksContainsJoinSKU extends AbstractComponent<Questions>
		implements Condition<HPRequest<Questions, Bundle, SKU>> {

	public List<Bundle> bundle = Bundle.ALL_TIERS;

	public boolean contains = true;

	@Override
	public boolean evaluate(HPRequest<Questions, Bundle, SKU> q) {
		if (q.bundles == null)
			return false;

		for (SKUList<Bundle, SKU> b : q.bundles) {
			if (bundle.contains(b.getBundle())) {
				if (evalBundle(b))
					return true;
			}
		}
		return false;
	}

	boolean evalBundle(SKUList<Bundle, SKU> b) {
		if (contains)
			return containsJoinSku(b.skus);
		else
			return !containsJoinSku(b.skus);
	}

	static boolean containsJoinSku(Collection<SKU> skus) {
		if (skus == null)
			return false;

		for (SKU sku : skus) {
			if (sku instanceof JoinSKU)
				return true;
		}

		return false;
	}
}