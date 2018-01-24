package com.shaklee.rulesets.healthQuestionaire.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.shaklee.healthPrint.data.Bundle;
import com.shaklee.healthPrint.data.HPRequest;
import com.shaklee.healthPrint.data.SKU;
import com.shaklee.healthPrint.data.SKUList;
import com.shaklee.promo.Action;
import com.shaklee.promo.PromoRequest;
import com.shaklee.promo.basic.AbstractComponent;
import com.shaklee.rulesets.healthQuestionaire.Questions;

/**
 * Add couple SKUs to given given bundle if bundle size is greater and equal to
 * given size. Watch out; this is running after engine is complete so it does
 * not add item as an action! It adds SKUs directly to given bundle!
 * 
 * For Elli, I explained why I could not separate the logic for checking the
 * bundle size here instead of in a condition class. Tip; bundles are not there
 * until engines is complete but actions!!!! ;)))
 * 
 * @author ekoca
 *
 */
@Component
public class FillBundleIfBundleSizeGreaterEqualThan extends AbstractComponent<PromoRequest<Questions>>
		implements Action<HPRequest<Questions, Bundle, ?>> {
	@NotNull
	public Bundle bundle_name;
	@NotNull
	public ArrayList<String> sku;
	@NotNull
	public String category;
	public int bundle_size;

	@Override
	public void exec(HPRequest<Questions, Bundle, ?> r) {
		if (r.bundles == null)
			return;

		SKUList<?, ?> bundle = getBundle(r.bundles, bundle_name);

		if (bundle != null && bundle.skus.size() >= bundle_size) {
			addSKUsToBundle(r, bundle.skus);
		}

		ConsiderDoesNotContainTiers.removeAll(r);
	}

	@SuppressWarnings("unchecked")
	private void addSKUsToBundle(PromoRequest<Questions> r, Collection<?> skus) {
		SKUs s = new SKUs();
		s.sku = (List<String>) sku.clone();
		s.bundle = bundle_name;
		s.category = category;
		s.addTo(skus);
	}

	public static <B, L> SKUList<B, L> getBundle(Collection<? extends SKUList<B, L>> bundles, Bundle bundle_name) {
		for (SKUList<B, L> bundle : bundles) {
			if (bundle_name == bundle.getBundle()) {
				return bundle;
			}
		}
		return null;
	}
}
