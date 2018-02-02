package com.shaklee.promo;

import static com.shaklee.common.util.CollectionFunctions.flatten;

import java.util.List;

import com.shaklee.common.util.CollectionFunctions;
import com.shaklee.common.util.CollectionFunctions.Function;
import com.shaklee.common.util.CollectionFunctions.Predicate;

public class Promo<T> {
	public List<RuleSet<T>> rulesets;
	public String code; // promo code
	public int id, priority;

	// maybe another type that is easier to deal with null
	public Integer eligibilityGroupID;

	// TODO: some functional stuff, most to utils later or use Guava
	private static final <T> Function<Promo<T>, List<RuleSet<T>>> promoRulesets() {
		return new Function<Promo<T>, List<RuleSet<T>>>() {
			@Override
			public List<RuleSet<T>> apply(Promo<T> input) {
				return input.rulesets;
			}
		};
	}

	public static <T> List<RuleSet<T>> filterPromos(List<Promo<T>> l,
			final Function<RuleSet<T>, Action<T>> mapper) {

		Function<Promo<T>, List<RuleSet<T>>> promoRulesets = promoRulesets();

		return flatten(l, promoRulesets, new Predicate<RuleSet<T>>() {
			@Override
			public boolean accept(RuleSet<T> p) {
				return mapper.apply(p) != null;
			}
		});
	}

	@Override
	public boolean equals(Object other) {
		return ((Promo<?>) other).id == id;
	}

	@Override
	public String toString() {
		return "Promo(" + id + ' ' + code + ")\n"
				+ CollectionFunctions.toString(rulesets, "  ");
	}
}
