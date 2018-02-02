package com.shaklee.rulesets.healthQuestionaire.components;

import java.util.Collection;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.shaklee.common.util.CollectionUtils;
import com.shaklee.healthPrint.data.HPRequest;
import com.shaklee.healthPrint.data.SKU;
import com.shaklee.healthPrint.data.SKUList;
import com.shaklee.promo.Condition;
import com.shaklee.promo.basic.AbstractComponent;

/**
 * Checks that skus are not in any of the products.
 * 
 * @author Elli Albek
 */
@Component
public class PacksContains extends AbstractComponent<Object> implements Condition<HPRequest<?, ?, SKU>> {

	@NotNull
	@Size(min = 1)
	public Collection<String> sku;

	public void setSku(List<String> sku) {
		this.sku = CollectionUtils.fastSearchCollection(sku);
	}

	public boolean contains = true;

	@Override
	public boolean evaluate(HPRequest<?, ?, SKU> q) {
		if (contains)
			return contains(q.bundles, sku);
		else
			return !contains(q.bundles, sku);
	}

	public static <T> boolean contains(Collection<SKUList<T, SKU>> bundles, Collection<String> skus) {
		if (bundles == null)
			return false;

		for (SKUList<?, SKU> bundle : bundles) {
			if (bundle.skus != null)
				for (SKU sku : bundle.skus) {
					if (skus.contains(sku.sku))
						return true;
				}
		}

		return false;
	}
}