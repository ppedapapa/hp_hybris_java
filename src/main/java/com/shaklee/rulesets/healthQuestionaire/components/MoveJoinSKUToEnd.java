package com.shaklee.rulesets.healthQuestionaire.components;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.shaklee.healthPrint.data.Bundle;
import com.shaklee.healthPrint.data.HPRequest;
import com.shaklee.healthPrint.data.SKU;
import com.shaklee.healthPrint.data.SKUList;
import com.shaklee.promo.Action;
import com.shaklee.promo.basic.AbstractComponent;
import com.shaklee.rulesets.healthQuestionaire.Questions;
import com.shaklee.rulesets.healthQuestionaire.components.AddMembershipSku.JoinSKU;

/**
 * Checks that skus are not in any of the products.
 * 
 * @author Elli Albek
 */
@Component
public class MoveJoinSKUToEnd extends AbstractComponent<Questions>
		implements Action<HPRequest<Questions, Bundle, SKU>> {

	public List<Bundle> bundle = Bundle.ALL_TIERS;

	public BigDecimal price = BigDecimal.ZERO;

	@Override
	public void exec(HPRequest<Questions, Bundle, SKU> t) {
		if (t.bundles == null)
			return;

		for (SKUList<Bundle, SKU> b : t.bundles) {
			if (bundle.contains(b.getBundle())) {
				b.skus = organizeSKUList(b.skus);
			}
		}
	}

	Collection<SKU> organizeSKUList(Collection<SKU> skus) {
		Iterator<SKU> sit = skus.iterator();
		while (sit.hasNext()) {
			SKU sku = sit.next();
			if (sku instanceof JoinSKU) {
				sit.remove();
				ArrayList<SKU> l = new ArrayList<SKU>(skus.size() + 1);
				l.addAll(skus);
				l.add(sku);
				return l;
			}
		}

		return skus;
	}
}