package com.shaklee.promo;

import java.sql.Timestamp;
import java.util.List;

/**
 * Database access for default implementation of promo engine.
 * 
 * @author Elli Albek
 */
public interface PromoDatabase<T> {

	/**
	 * Returns the groups that the user belongs to, from the provided list
	 */
	//List<Integer> loadEligibilityMatches(List<Integer> userGroupIDs, T request);
	
	/**
	 * Returns the list of promo rules and actions from a persistent storage.
	 * Do not cache, this is handled elsewhere.
	 */
	List<Promo<PromoRequest<T>>> loadPromoRules(LocalizedData request, Timestamp ts);
	
	Arbitrator<PromoRequest<T>> loadArbitrator(LocalizedData request);
}
