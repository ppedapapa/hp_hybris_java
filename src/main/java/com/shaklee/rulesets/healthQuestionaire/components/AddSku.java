package com.shaklee.rulesets.healthQuestionaire.components;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.shaklee.healthPrint.data.Bundle;
import com.shaklee.promo.Action;
import com.shaklee.promo.PromoRequest;
import com.shaklee.promo.basic.AbstractComponent;
import com.shaklee.promo.basic.PromoActionUtils;

@Component
public class AddSku<Q> extends AbstractComponent<PromoRequest<Q>> implements Action<PromoRequest<Q>> {

	/**
	 * Default to all bundles
	 */
	@NotNull
	public List<Bundle> bundle = Bundle.ALL_TIERS;

	@NotNull
	public ArrayList<String> sku;

	public String category;

	@SuppressWarnings("unchecked")
	@Override
	public void exec(PromoRequest<Q> r) {
		for (Bundle b : bundle) {
			SKUs<Bundle> s = new SKUs<Bundle>();
			s.sku = (List<String>) sku.clone();
			s.bundle = b;
			s.category = category;
			PromoActionUtils.addAction(r, ruleset, s);
		}
	}
}
