package com.shaklee.rulesets.healthQuestionaire.components;

import java.util.Collection;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.shaklee.healthPrint.data.SKU;
import com.shaklee.promo.PromoRequest.Action;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class SKUs<BundleType> implements Action {

	public List<String> sku;

	public String category;
	public BundleType bundle;

	public void addTo(Collection skus) {
		for (String s : sku) {
			SKU item = new SKU();
			item.sku = s;
			item.category = this.category;
			skus.add(item);
		}
	}
}