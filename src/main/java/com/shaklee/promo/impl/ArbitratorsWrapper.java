package com.shaklee.promo.impl;

import java.util.List;

import com.shaklee.promo.Arbitrator;
import com.shaklee.promo.RuleSet;

/**
 * It is a wrapper class that run all the arbitrator rules (loaded from DB)
 * 
 * @author ekoca
 *
 * @param <T>
 */
public class ArbitratorsWrapper<T> implements Arbitrator<T> {

	final List<Arbitrator<T>> arbitrators;

	public ArbitratorsWrapper(List<Arbitrator<T>> arbitrators) {
		this.arbitrators = arbitrators;
	}

	@Override
	public List<RuleSet<T>> arbitrate(List<RuleSet<T>> matches, T request) {
		for (Arbitrator<T> arbitrator : arbitrators) {
			matches = arbitrator.arbitrate(matches, request);
		}
		return matches;
	}
}