package com.shaklee.healthPrint.data;

import java.util.Collection;
import java.util.List;

/**
 * An implementation of {@link SKUList} that adds a tier level to the list of
 * SKUs, creating a bundle.
 * 
 * @author Elli Albek
 */
public class TieredSKUList extends SKUList<Bundle, SKU> {

	/** the price tier **/
	public Bundle bundle;

	@Override
	public Bundle getBundle() {
		return bundle;
	}

	@Override
	public void setBundle(Bundle bundle) {
		this.bundle = bundle;
	}

	@SuppressWarnings("unchecked")
	public static Collection<TieredSKUList> fromAbstractType(Collection<SKUList<Bundle, SKU>> abs) {
		Collection<?> l = abs;
		return (List<TieredSKUList>) l;
	}

	@Override
	public Collection<SKU> getSkus() {
		return skus;
	}

	@Override
	public void setSkus(Collection<SKU> skus) {
		this.skus = skus;
	}
}