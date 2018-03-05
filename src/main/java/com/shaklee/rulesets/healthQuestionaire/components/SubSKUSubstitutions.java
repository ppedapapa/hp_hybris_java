package com.shaklee.rulesets.healthQuestionaire.components;

import static com.shaklee.rulesets.healthQuestionaire.components.SKUSubstitutions.REMOVE;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.shaklee.healthPrint.data.HPRequest;
import com.shaklee.healthPrint.data.SKU;
import com.shaklee.healthPrint.data.SKUList;
import com.shaklee.promo.Action;
import com.shaklee.promo.basic.AbstractComponent;

@Component
public class SubSKUSubstitutions extends AbstractComponent<Object> implements Action<HPRequest<?, ?, SKU>> {

	/** Required for serialization **/
	public List<String> sku;
	private Map<String, String> replacements;

	public void setSku(List<String> skus) {
		replacements = SKUSubstitutions.createReplacementMap(skus);
	}

	@Override
	public void exec(HPRequest<?, ?, SKU> q) {
		if (q.bundles != null) {
			for (SKUList<?, SKU> bundle : q.bundles) {
				Iterator<SKU> skus = bundle.skus.iterator();
				while (skus.hasNext()) {
					SKU sku = skus.next();
					// no replacement, process children
					if (sku.sub_sku != null)
						replaceSKUs(sku.sub_sku);
				}
			}
		}
	}

	void replaceSKUs(List<String> skuList) {
		ListIterator<String> skus = skuList.listIterator();
		while (skus.hasNext()) {
			String replacement = replacements.get(skus.next());
			if (replacement == REMOVE) {
				skus.remove();
			} else if (replacement != null) {
				// sub exists, replace
				skus.set(replacement);
			}
		}
	}
}