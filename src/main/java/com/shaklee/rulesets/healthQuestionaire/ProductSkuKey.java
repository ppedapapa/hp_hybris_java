package com.shaklee.rulesets.healthQuestionaire;

import com.shaklee.shared.data.Country2;

public class ProductSkuKey {
	public Country2 country;
	public String sku;

	public ProductSkuKey() {
	}

	public ProductSkuKey(Country2 country, String sku) {
		this.country = country;
		this.sku = sku;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ProductSkuKey) {
			ProductSkuKey other = (ProductSkuKey) obj;
			return sku.equals(other.sku) && country == other.country;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return sku.hashCode();
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + '(' + country + ' ' + sku + ')';
	}
}