package com.shaklee.promo;

import java.util.List;

/**
 * Runtime data of entire execution instance. Normally includes all the possible
 * promos per country that are running during this period.
 * 
 * @author Elli Albek
 */
public class PromoExecutionInstance<T> implements Cloneable {
	public List<Promo<T>> promos;
	public Arbitrator<T> arbitrator;

	@SuppressWarnings("unchecked")
	@Override
	public PromoExecutionInstance<T> clone() {
		try {
			return (PromoExecutionInstance<T>) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new Error(e);
		}
	}
}