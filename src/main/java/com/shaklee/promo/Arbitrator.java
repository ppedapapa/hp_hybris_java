package com.shaklee.promo;

import java.util.List;

/**
 * Arbitrate multiple matches.
 * 
 * @author Elli Albek
 */
public interface Arbitrator<T> {

	/**
	 * Return a list of promos if the arbitrator made a final decision. If there
	 * is no decision yet, return Null.
	 * 
	 * Any returned value that is not Null means a FINAL decision, and promos
	 * that were not evaluated yet will not be evaluated.
	 */
	List<RuleSet<T>> arbitrate(List<RuleSet<T>> matches, T request);
}