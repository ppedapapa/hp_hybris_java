package com.shaklee.rulesets.healthQuestionaire.components;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shaklee.healthPrint.data.Bundle;
import com.shaklee.healthPrint.data.HPRequest;
import com.shaklee.healthPrint.data.SKU;
import com.shaklee.healthPrint.data.SKUList;
import com.shaklee.promo.Condition;
import com.shaklee.promo.basic.AbstractComponent;
import com.shaklee.rulesets.healthQuestionaire.ProductPriceDAO;
import com.shaklee.rulesets.healthQuestionaire.Questions;
import com.shaklee.rulesets.healthQuestionaire.components.AddMembershipSku.JoinSKU;

/**
 * Checks that skus are not in any of the products.
 * 
 * @author Elli Albek
 */
@Component
public class PacksContainsJoinKit extends AbstractComponent<Questions>
		implements Condition<HPRequest<Questions, Bundle, SKU>> {

	public List<Bundle> bundle = Bundle.ALL_TIERS;

	public boolean contains = true;

	@Autowired
	ProductPriceDAO dao;

	@Override
	public boolean evaluate(HPRequest<Questions, Bundle, SKU> q) {
		if (q.bundles == null)
			return false;

		Collection<String> joinKits = dao.getJoinKits();

		for (SKUList<Bundle, SKU> b : q.bundles) {
			if (bundle.contains(b.getBundle())) {
				if (evalBundle(joinKits, b))
					return true;
			}
		}
		return false;
	}

	boolean evalBundle(Collection<String> joinKits, SKUList<Bundle, SKU> b) {
		if (contains)
			return contains(b.skus, joinKits);
		else
			return !contains(b.skus, joinKits);
	}

	static boolean contains(Collection<SKU> skus, Collection<String> joinKits) {
		if (skus == null || skus.isEmpty())
			return false;

		for (SKU sku : skus) {
			if (sku instanceof JoinSKU == false)
				if (joinKits.contains(sku.sku))
					return true;
		}

		return false;
	}
}