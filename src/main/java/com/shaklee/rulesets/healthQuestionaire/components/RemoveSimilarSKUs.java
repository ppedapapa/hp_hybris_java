package com.shaklee.rulesets.healthQuestionaire.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.shaklee.healthPrint.data.HPRequest;
import com.shaklee.healthPrint.data.SKU;
import com.shaklee.healthPrint.data.SKUList;
import com.shaklee.promo.Action;
import com.shaklee.promo.basic.AbstractComponent;

/**
 * Remove similar SKUS in a bundle. The configuration is sku lists. If the first
 * in the list is in the bundle, the rest are removed from the bundle.
 */

@Component
public class RemoveSimilarSKUs extends AbstractComponent<Object> implements Action<HPRequest<?, ?, SKU>> {

	// private static final Logger LOGGER =
	// LoggerFactory.getLogger(RemoveSimilarSKUs.class);

	static final String REMOVE = "REMOVE";
	private static final Pattern SPLIT = Pattern.compile("\\s+");

	public List<String> sku;
	private Map<String, List<String>> replacements;

	public void setSku(List<String> skus) {
		replacements = createRemoveMap(skus);
	}

	static Map<String, List<String>> createRemoveMap(List<String> sku) {
		HashMap<String, List<String>> replacements = new HashMap<String, List<String>>(sku.size());
		for (String s : sku) {
			String[] sa = SPLIT.split(s);
			if (sa.length < 2)
				throw new IllegalArgumentException("Line must contain at least two skus, found '" + s + "'");

			ArrayList<String> deletes = new ArrayList<String>(sa.length - 1);
			for (int i = 1; i < sa.length; i++)
				deletes.add(sa[i]);

			replacements.put(sa[0].trim(), deletes);
		}
		return replacements;
	}

	@Override
	public void exec(HPRequest<?, ?, SKU> q) {
		if (q.bundles == null)
			// no recommendations
			return;

		for (SKUList<?, SKU> l : q.bundles)
			cleanseSKUs(l.skus);
	}

	void cleanseSKUs(Collection<SKU> skus) {
		Collection<String> remove = findRemovals(skus);
		if (remove == null)
			return;

		SKU sku = new SKU(); // reused
		for (String removeSku : remove) {
			sku.sku = removeSku;
			skus.remove(sku);
		}
	}

	Collection<String> findRemovals(Collection<SKU> skus) {
		Collection<String> remove = null;

		for (SKU sku : skus) {
			List<String> dups = replacements.get(sku.sku);
			if (dups != null) {
				if (remove == null)
					remove = new ArrayList<String>(dups);
				else
					remove.addAll(dups);
			}
		}
		return remove;
	}
}