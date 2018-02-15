package com.shaklee.entity;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductPricePerTier {

	String priceTier;
	BigDecimal value;
	
	public String getPriceTier() {
		return priceTier;
	}
	public void setPriceTier(String priceTier) {
		this.priceTier = priceTier;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
	
}
