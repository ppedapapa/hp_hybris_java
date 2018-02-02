package com.shaklee.rulesets.healthQuestionaire;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.shaklee.common.util.StringUtils;
import com.shaklee.shared.data.Country2;

public class Product extends ProductSkuKey {

	public String name;

	// For fully accurate calculations this needs to be decimal, for other
	// usages float is enough

	public float mn_price;

	@JsonIgnore
	public float sn_price, dn_price; // , srp_price;

	public void setCountry(Country2 country) {
		this.country = country;
	}

	public void setSku(String sku) {
		this.sku = sku.trim();
	}

	public void setSn_price(float sn_price) {
		this.sn_price = sn_price;
	}

	public void setDn_price(float dn_price) {
		this.dn_price = dn_price;
	}

	public void setMn_price(float mn_price) {
		this.mn_price = mn_price;
	}

	// public void setSrp_price(float srp_price) {
	// this.srp_price = srp_price;
	// }

	public void setName(String name) {
		name = StringUtils.clean(name);
		this.name = name;
	}
}