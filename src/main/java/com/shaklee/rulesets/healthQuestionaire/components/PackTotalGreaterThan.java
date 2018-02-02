package com.shaklee.rulesets.healthQuestionaire.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shaklee.healthPrint.data.Bundle;
import com.shaklee.healthPrint.data.HPRequest;
import com.shaklee.healthPrint.data.SKU;
import com.shaklee.healthPrint.data.SKUList;
import com.shaklee.promo.Condition;
import com.shaklee.promo.basic.AbstractComponent;
import com.shaklee.rulesets.healthQuestionaire.Product;
import com.shaklee.rulesets.healthQuestionaire.ProductSkuKey;
import com.shaklee.rulesets.healthQuestionaire.Questions;
import com.shaklee.rulesets.healthQuestionaire.components.AddMembershipSku.JoinSKU;
import com.shaklee.shared.data.Country2;

/**
 * Checks that skus are not in any of the products.
 * 
 * @author Elli Albek
 */
@Component
public class PackTotalGreaterThan extends AbstractComponent<Questions>
		implements Condition<HPRequest<Questions, Bundle, SKU>> {

	public List<Bundle> bundle = Bundle.ALL_TIERS;

	public float total;
	public boolean excludeJoinSKU = true;

	//@Autowired
	//ProductPriceDAO dao;

	@Override
	public boolean evaluate(HPRequest<Questions, Bundle, SKU> q) {
		if (q.bundles == null)
			return false;

		for (SKUList<Bundle, SKU> b : q.bundles) {
			if (bundle.contains(b.getBundle())) {
				Country2 country = Country2.valueOf(q.request.country_code.toLowerCase());
				if (eval(b, country))
					return true;
			}
		}
		return false;
	}

	boolean eval(SKUList<?, SKU> bundle, Country2 country) {
		if (bundle.skus == null || bundle.skus.isEmpty())
			return false;

		List<ProductSkuKey> skus = new ArrayList<ProductSkuKey>(bundle.skus.size());

		for (SKU sku : bundle.skus) {
			if (excludeJoinSKU ^ (sku instanceof JoinSKU))
				skus.add(new ProductSkuKey(country, sku.sku));
		}
		//final Map<ProductSkuKey, Product> products = dao.getProducts(skus);
		final Map<ProductSkuKey, Product> products = null;

		float sum = 0f;
		for (Product product : products.values()) {
			sum += product.mn_price;
			if (sum >= total)
				return true;
		}

		return false;
	}
}