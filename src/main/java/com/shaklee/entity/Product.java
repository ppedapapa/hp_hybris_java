package com.shaklee.entity;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.shaklee.rulesets.healthQuestionaire.ProductSkuKey;



@JsonIgnoreProperties(ignoreUnknown = true)
public class Product  extends ProductSkuKey {
	
	String description;
	
	List<ProductPricePerTier> prices;

	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ProductPricePerTier> getPrices() {
		return prices;
	}

	public void setPrices(List<ProductPricePerTier> prices) {
		this.prices = prices;
	}

	
	

	
}
