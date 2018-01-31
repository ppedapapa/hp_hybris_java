package com.shaklee.healthPrint.components;

import java.util.Collection;
import java.util.Map;

/**
 * Provides prices to rules that require them.
 * 
 * @author Elli Albek
 */
public interface ProductPriceDAO {

	public Map<String, Float> getPrices(String country, Collection<String> skus);

}