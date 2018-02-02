package com.shaklee.promo;

import static com.shaklee.common.util.CollectionFunctions.flatten;
import static com.shaklee.common.util.CollectionFunctions.map;

import java.util.Comparator;
import java.util.List;

import com.shaklee.common.util.CollectionFunctions.Function;

/**
 * Collection utility methods for iterating over promo data.
 * 
 * @author Elli Albek
 */
public class PromoCollections {

	public static final <T> List<Promo<T>> getPromos(List<RuleSet<T>> rulesets) {
		return map(rulesets, new Function<RuleSet<T>, Promo<T>>() {
			@Override
			public Promo<T> apply(RuleSet<T> ruleset) {
				return ruleset.promo;
			}
		});
	}

	private static Object selectActions = new Function<RuleSet<Object>, List<Action<Object>>>() {
		@Override
		public List<Action<Object>> apply(RuleSet<Object> r) {
			return r.actions;
		}
	};

	@SuppressWarnings("unchecked")
	public static final <T> Function<RuleSet<T>, Action<T>> selectActions() {
		return (Function<RuleSet<T>, Action<T>>) selectActions;
	}

	private static Object selectMessages = new Function<RuleSet<Object>, List<Action<Object>>>() {
		@Override
		public List<Action<Object>> apply(RuleSet<Object> r) {
			if (r.actions == null)
				return r.messages;
			else
				return null;
		}
	};

	@SuppressWarnings("unchecked")
	public static final <T> Function<RuleSet<T>, Action<T>> selectMessages() {
		return (Function<RuleSet<T>, Action<T>>) selectMessages;
	}

	public static final Comparator<Promo<?>> promoPriorityComparator = new Comparator<Promo<?>>() {
		@Override
		public int compare(Promo<?> o1, Promo<?> o2) {
			return o2.priority - o1.priority;
		}
	};

	public static final Comparator<RuleSet<?>> rulesetPriorityComparator = new Comparator<RuleSet<?>>() {
		@Override
		public int compare(RuleSet<?> o1, RuleSet<?> o2) {
			return o2.promo.priority - o1.promo.priority;
		}
	};

	public static final <T> List<RuleSet<T>> getRuleSets(List<Promo<T>> promos) {
		final Function<Promo<T>, List<RuleSet<T>>> promoRulesMapper = new Function<Promo<T>, List<RuleSet<T>>>() {
			@Override
			public List<RuleSet<T>> apply(Promo<T> promo) {
				return promo.rulesets;
			}
		};

		return flatten(promos, promoRulesMapper);
	}
}