package com.shaklee.promo.impl;

import java.util.List;

import com.shaklee.promo.Arbitrator;
import com.shaklee.promo.RuleSet;

/**
 * A simple arbitrator that picks the first match
 * 
 * @author Elli Albek
 */
class FirstMatchArbitrator<T> implements Arbitrator<T> {

	@Override
	public List<RuleSet<T>> arbitrate(List<RuleSet<T>> matches, T request) {
		if (matches == null || matches.isEmpty())
			// none
			return null;

		// has matches, pick first
		return matches.subList(0, 1);
	}
}