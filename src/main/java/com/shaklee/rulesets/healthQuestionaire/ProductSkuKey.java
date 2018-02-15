package com.shaklee.rulesets.healthQuestionaire;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.shaklee.shared.data.Country2;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductSkuKey {
	public Country2 country;
	public String code;

	public ProductSkuKey() {
	}

	public ProductSkuKey(Country2 country, String code) {
		this.country = country;
		this.code = code;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ProductSkuKey) {
			ProductSkuKey other = (ProductSkuKey) obj;
			return code.equals(other.code) && country == other.country;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return code.hashCode();
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + '(' + country + ' ' + code + ')';
	}
}