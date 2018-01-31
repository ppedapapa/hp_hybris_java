package com.shaklee.rulesets.healthQuestionaire.components;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.shaklee.promo.Action;
import com.shaklee.promo.PromoRequest;
import com.shaklee.promo.PromoRequest.PromoAction;
import com.shaklee.promo.basic.AbstractComponent;

@Component
public class SKUSubstitutions extends AbstractComponent<Object> implements Action<PromoRequest<?>> {

	private static final Logger LOGGER = LoggerFactory.getLogger(SKUSubstitutions.class);

	static final String REMOVE = "REMOVE";
	public static final Pattern SPLIT = Pattern.compile("\\s+");

	public List<String> sku;
	private Map<String, String> replacements;

	public void setSku(List<String> skus) {
		replacements = createReplacementMap(skus);
	}

	static Map<String, String> createReplacementMap(List<String> sku) {
		HashMap<String, String> replacements = new HashMap<String, String>(sku.size());
		for (String s : sku) {
			String[] sa = SPLIT.split(s);
			if (sa.length > 1)
				replacements.put(sa[0].trim(), sa[1].trim());
			else
				replacements.put(sa[0].trim(), REMOVE);
		}
		return replacements;
	}

	@Override
	public void exec(PromoRequest<?> q) {
		if (q.response != null) {
			for (PromoAction pa : q.response) {
				for (com.shaklee.promo.PromoRequest.Action action : pa.actions) {
					if (action instanceof SKUs) {
						SKUs<?> addSku = (SKUs<?>) action;
						if (addSku.sku != null) {
							ListIterator<String> skus = addSku.sku.listIterator();
							while (skus.hasNext()) {
								String sku = skus.next();
								String replacement = replacements.get(sku);
								if (replacement == REMOVE) {
									skus.remove();

									if (LOGGER.isDebugEnabled())
										LOGGER.debug("REMOVED " + sku);
								} else if (replacement != null) {
									// sub exists, replace
									skus.set(replacement);

									if (LOGGER.isDebugEnabled())
										LOGGER.debug("REPLACE " + sku + " with " + replacement);
								}
							}
						}
					}
				}
			}
		}
	}
}