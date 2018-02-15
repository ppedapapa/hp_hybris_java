package com.shaklee.rulesets.healthQuestionaire.components;

import static com.shaklee.healthPrint.components.BundleUtils.getBundle;
import static com.shaklee.healthPrint.components.BundleUtils.getSkus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shaklee.DAO.ProductPriceDAO;
import com.shaklee.common.util.collections.AbstractCollectionTransformDecorator;
import com.shaklee.healthPrint.components.AddSku.SKUQuantity;
import com.shaklee.healthPrint.data.Bundle;
import com.shaklee.healthPrint.data.HPRequest;
import com.shaklee.healthPrint.data.SKU;
import com.shaklee.healthPrint.data.SKUList;
import com.shaklee.healthPrint.data.TieredSKUList;
import com.shaklee.promo.Action;
import com.shaklee.promo.LocalizedData;
import com.shaklee.promo.basic.AbstractComponent;

/**
 * Reduce the bundles to stay under a minimum price. Excess products from the
 * ultimate bundle go to consider category.
 * 
 * @author Elli Albek
 */
@Component
public class BundlePriceLimit<T extends LocalizedData> extends AbstractComponent<T>
		implements Action<HPRequest<T, Bundle, SKU>> {

	private static final Logger logger = LoggerFactory.getLogger(BundlePriceLimit.class);
	private static final boolean isDebugEnabled = logger.isDebugEnabled();

	@Autowired
	ProductPriceDAO dao;

	public float tier_1_max, tier_2_max, tier_3_max, price_step;

	@Override
	public void exec(HPRequest<T, Bundle, SKU> request) {
		if (request.bundles == null || request.bundles.isEmpty())
			// no recommendations
			return;

		final Collection<SKU> tier1 = getSkus(request.bundles, Bundle.TIER_1);
		final Collection<SKU> tier2 = getSkus(request.bundles, Bundle.TIER_2);
		final Collection<SKU> tier3 = getSkus(request.bundles, Bundle.TIER_3);

		// get all product prices from cache
		@SuppressWarnings("unchecked")
		final Map<String, Float> products = getProducts(request, tier1, tier2, tier3);

		// base consider bundle
		// prices are not necessary for consider, there is no upper limit.
		final Collection<SKU> consider = convertBundleToLinkedHashSet(request, Bundle.CONSIDER);

		splitByPrice(tier1, tier_1_max - price_step, tier_1_max + price_step, products);
		splitByPrice(tier2, tier_2_max - price_step, tier_2_max + price_step, products);

		consider.addAll(splitByPrice(tier3, tier_3_max - price_step, tier_3_max + price_step, products));

		// remove SKUs that are already in tiers from consider
		// even though consider was added tier 3 leftovers, it may have
		// duplicates with tier 3 from simple recommendations.
		// consider.removeAll(tier1);
		// consider.removeAll(tier2);
		// consider.removeAll(tier3);
	}

	private <BundleType> Map<String, Float> getProducts(HPRequest<T, BundleType, SKU> request,
			final Collection<SKU>... tiers) {
		Collection<String> skus = new HashSet<String>();
		for (Collection<SKU> tier : tiers)
			skus.addAll(new SkuDecorator(tier));

		// final Country2 country =
		// Country2.valueOf(q.request.country_code.toLowerCase());
		final String country = request.request.getCountryCode().toLowerCase();
		return dao.getPrices(country, skus);
		
	}

	private static <T> Collection<SKU> convertBundleToLinkedHashSet(HPRequest<T, Bundle, SKU> request,
			final Bundle bundle) {
		SKUList<Bundle, SKU> b = getBundle(request.bundles, bundle);
		if (b == null) {
			b = new TieredSKUList();
			b.setBundle(bundle);
			b.skus = new LinkedHashSet<SKU>();
			request.bundles.add(b);
		} else
			b.skus = new LinkedHashSet<SKU>(b.skus);

		return b.skus;
	}

	/**
	 * Subclasses can specify a different price tier
	 */
	public float getPrice(SKU sku, Map<String, Float> prices) {
		Float price = prices.get(sku.sku);
		if (price == null)
			return 0f;

		// has quantity
		if (sku instanceof SKUQuantity) {
			return ((SKUQuantity) sku).quantity * price;
		}

		return price;
	}

	public Collection<SKU> splitByPrice(Collection<SKU> skus, float max_lower, float max_upper,
			Map<String, Float> prices) {
		float total = 0;
		ArrayList<SKU> removed = new ArrayList<SKU>();

		Iterator<SKU> it = skus.iterator();

		while (it.hasNext()) {
			SKU sku = it.next();
			// logger.debug(sku.sku);
			if (total <= max_lower) {
				float price = getPrice(sku, prices);
				if (isDebugEnabled)
					logger.debug("SKU " + sku + " $" + price + " + " + total + " = " + (price + total));
				if (total + price <= max_upper) {
					total += price;
					continue;
				}
			}
			// past the max bundle limit
			it.remove();
			removed.add(sku);
			if (isDebugEnabled)
				logger.debug("Removed " + sku + " over limit (total=" + total + ")");
		}

		return removed;
	}

	public static final class SkuDecorator extends AbstractCollectionTransformDecorator<SKU, String> {

		public SkuDecorator(Collection<SKU> origin) {
			super(origin);
		}

		@Override
		protected String convert(SKU sku) {
			return sku.sku;
		}
	}
}