package com.shaklee.healthPrint.components;

import java.util.Collection;
import java.util.List;

import com.shaklee.healthPrint.data.Bundle;
import com.shaklee.healthPrint.data.SKU;
import com.shaklee.promo.PromoRequest;
import com.shaklee.promo.basic.PromoActionUtils;
import com.shaklee.rulesets.healthQuestionaire.components.SKUs;

public class AddSku<Q> extends com.shaklee.rulesets.healthQuestionaire.components.AddSku<Q> {

	public int quantity = 1;

	@SuppressWarnings("unchecked")
	@Override
	public void exec(PromoRequest<Q> r) {
		for (Bundle b : bundle) {
			SKUsQuantity<Bundle> s = new SKUsQuantity<Bundle>();
			s.sku = (List<String>) sku.clone();
			s.bundle = b;
			s.category = category;
			s.quantity = quantity;
			PromoActionUtils.addAction(r, ruleset, s);
		}
	}

	static class SKUsQuantity<B> extends SKUs<B> {
		public int quantity;

		@Override
		public void addTo(Collection skus) {
			for (String s : sku) {
				SKUQuantity item = new SKUQuantity();
				item.sku = s;
				item.category = this.category;
				item.quantity = this.quantity;
				skus.add(item);
			}
		}
	}

	public static class SKUQuantity extends SKU {
		public int quantity;
	}
}