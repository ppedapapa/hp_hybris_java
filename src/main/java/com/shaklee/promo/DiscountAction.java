package com.shaklee.promo;

/**
 * Extension for actions that create discounts. Used by arbitrators that
 * arbitrate based on discounts.
 * 
 * @author Elli Albek
 */
public interface DiscountAction<T> extends Action<T> {

	/**
	 * Return the monetary value of this discount, how much money a user will
	 * save. Unit is the currency of the shopping cart (like CAD, USD, Yen, etc)
	 */
	public double calculateDiscount(T t);
}
