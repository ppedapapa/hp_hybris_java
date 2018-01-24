package com.shaklee.rulesets.healthQuestionaire.components;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import com.shaklee.healthPrint.data.Bundle;
import com.shaklee.healthPrint.data.HPRequest;
import com.shaklee.healthPrint.data.SKUList;
import com.shaklee.promo.Action;
import com.shaklee.promo.basic.AbstractComponent;
import com.shaklee.rulesets.healthQuestionaire.Questions;
import com.shaklee.rulesets.healthQuestionaire.components.AddMembershipSku.JoinSKU;

/**
 * Sets the price of membership SKUs to the specified prices.
 * 
 * @author Elli Albek
 */
@Component
public class ChangeMembershipSKUPrice extends AbstractComponent<Questions>
		implements Action<HPRequest<Questions, Bundle, ?>> {

	public List<Bundle> bundle = Bundle.ALL_TIERS;

	public BigDecimal price = BigDecimal.ZERO;

	@Override
	public void exec(HPRequest<Questions, Bundle, ?> t) {
		if (t.bundles == null)
			return;

		for (SKUList<Bundle, ?> b : t.bundles) {
			if (bundle.contains(b.getBundle())) {
				for (Object sku : b.skus) {
					if (sku instanceof JoinSKU) {
						((JoinSKU) sku).sn_price = price;
					}
				}
			}
		}
	}
}