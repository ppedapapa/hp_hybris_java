package com.shaklee.rulesets.healthQuestionaire.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;

import com.shaklee.healthPrint.data.HPRequest;
import com.shaklee.healthPrint.data.SKUList;
import com.shaklee.promo.PromoRequest.PromoAction;

/**
 * Action that aggregates all matches into bundles, removing duplicates. Must be
 * always run after the recommendation rules, before the price limit
 * rules/actions.
 * 
 * Used to be a parameterized object, but because of SKUList refactoring needs
 * its own strongly typed subclass as well.
 * 
 * @author Elli Albek
 */
public abstract class AbstractCreateBundles<Q, T, S> {

	/** required for json serializer **/
	public boolean blah;

	public void exec(HPRequest<Q, T, S> request) {
		request.bundles = groupSKUsByBundle(request.response);
	}

	public Collection<SKUList<T, S>> groupSKUsByBundle(List<PromoAction> actions) {
		HashMap<T, SKUList<T, S>> bundles = new HashMap<T, SKUList<T, S>>(5);
		if (actions != null)
			for (PromoAction pa : actions) {
				if (pa.actions != null)
					for (com.shaklee.promo.PromoRequest.Action action : pa.actions) {
						if (action instanceof SKUs) {
							@SuppressWarnings("unchecked")
							SKUs<T> addSku = (SKUs<T>) action;
							SKUList<T, S> bundle = bundles.get(addSku.bundle);
							if (bundle == null) {
								bundle = createNewSKUList(addSku);
								bundle.skus = new LinkedHashSet<S>();
								bundles.put(addSku.bundle, bundle);
							}

							addSku.addTo(bundle.skus);
						}
					}
			}

		// get rid of the map's memory and use a faster collection instead.
		return new ArrayList<SKUList<T, S>>(bundles.values());
	}

	protected abstract SKUList<T, S> createNewSKUList(SKUs<T> addSku);
}