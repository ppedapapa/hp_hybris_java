package com.shaklee.promo.impl;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.shaklee.promo.Action;
import com.shaklee.promo.Arbitrator;
import com.shaklee.promo.DiscountAction;
import com.shaklee.promo.RuleSet;
import com.shaklee.promo.util.ArrayIterator;

/**
 * An arbitrator implementation that supports multiple promo groups. Each group
 * is arbitrated independently, and can have its own properties, such as number
 * of allowed matches and priority order.
 * 
 * @author Elli Albek
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class MultipleArbitrationGroupsArbitrator<T> implements Arbitrator<T> {

	public List<ArbitrationGroup> groups;

	@Override
	public List<RuleSet<T>> arbitrate(List<RuleSet<T>> matches, T request) {
		if (groups == null || matches.isEmpty())
			// nothing to do
			return matches;

		// linked list is faster for removals in the middle
		// this is also a defensive copy
		// matches = new LinkedList<RuleSet<T>>(matches);

		List<RuleSet<T>> arbitrated = null;

		for (ArbitrationGroup group : groups) {
			List<RuleSet<T>> groupMatch = arbitrate(group, matches, request);
			if (groupMatch != null) {
				// arbitration results: deferred initialization
				if (arbitrated == null)
					arbitrated = groupMatch;
				else
					arbitrated.addAll(groupMatch);
			}

			// fast exist optimization
			if (matches.isEmpty())
				// groups may remove all matches, in that case no point in
				// arbitrating
				break;
		}

		// All group promos were removed from matches. Matches now contains only
		// promos that are not part of any match group.
		if (arbitrated != null)
			matches.addAll(arbitrated);

		return matches;
	}

	static <T> List<RuleSet<T>> arbitrate(ArbitrationGroup group, List<RuleSet<T>> matches, T request) {

		switch (group.selection_order) {
		case PRIORITY:
			return arbitrateByPriority(group, matches);

		case DISCOUNT:
			return arbitrateByDiscount(group, matches, request);

		case ORDER:
			return arbitrateByOrder(group, matches);

		// case MATCH_CANCELS_ALL:
		// return arbitrateMatchCancelsAll(group, matches);
		//
		// case MATCH_CANCELS_GROUP:
		//	return arbitrateMatchCancelsGroup(group, matches);
		}
		return null;
	}

	/**
	 * Arbitrate a single match group that selects the top priority matches.
	 * Since the matches will already happen by priority, it can stop
	 * immediately once it finds the maximum allowed matched.
	 */
	static <T> List<RuleSet<T>> arbitrateByPriority(ArbitrationGroup group, List<RuleSet<T>> matches) {

		ArrayList<RuleSet<T>> arbitrated = null;

		for (Iterator<RuleSet<T>> iterator = matches.iterator(); iterator.hasNext();) {
			RuleSet<T> ruleSet = iterator.next();
			if (group.hasPromo(ruleSet.promo.code)) {
				// MATCH
				if (arbitrated == null)
					arbitrated = new ArrayList<RuleSet<T>>(group.max_matches / 2 + 1);
				arbitrated.add(ruleSet);
				iterator.remove();

				// quick exit
				if (arbitrated.size() == group.max_matches) {
					// we have enough matches, stop here
					// before stopping we have to delete from the list more
					// matches that may be there (IE max = 2, but there are 3
					// matches)
					removeGroup(iterator, group);
					return arbitrated;
				}
			}
		}

		// went over the entire list, return what we found so far
		return arbitrated;
	}

	/**
	 * Arbitrate a single match group that selects the first matches, according
	 * to the order of promos in the list.
	 */
	static <T> List<RuleSet<T>> arbitrateByOrder(ArbitrationGroup group, List<RuleSet<T>> matches) {

		ArrayList<RuleSet<T>> arbitrated = null;

		ArrayIterator<?> codes = new ArrayIterator(group.promos);

		while (codes.hasNext()) {
			String code = (String) codes.next();
			for (Iterator<RuleSet<T>> iterator = matches.iterator(); iterator.hasNext();) {
				RuleSet<T> ruleSet = iterator.next();
				if (code.equals(ruleSet.promo.code)) {
					// MATCH
					if (arbitrated == null)
						arbitrated = new ArrayList<RuleSet<T>>(group.max_matches / 2 + 1);
					arbitrated.add(ruleSet);
					iterator.remove();

					// quick exit
					if (arbitrated.size() == group.max_matches) {
						// we have enough matches, stop here
						// before stopping we have to delete from the list more
						// matches that may be there (IE max = 2, but there are
						// 3
						// matches)
						while (codes.hasNext())
							removeCode(matches, (String) codes.next());

						return arbitrated;
					}
				}
			}
		}

		// went over the entire list, return what we found so far
		return arbitrated;
	}

	/**
	 * If one (or more) of the specified promos match, cancels all other promos.
	 * If that promo exists, all its rulsets will be preserved.
	 * 
	 * This is implemented as double pass. First pass creates the list of promos
	 * to keep, second pass creates the final list of rulesets.
	 * 
	 * Size in that case is how many promo codes we want to preserve. For
	 * example,, we can pass 3 promo codes, but preserve only two (first two
	 * matches).
	 */
	public static <T> List<RuleSet<T>> arbitrateMatchCancelsAll(ArbitrationGroup group, List<RuleSet<T>> matches) {

		// first pass: list of promos to keep
		Set<String> codes = findPromoCodes(asList(group.promos), group.max_matches, matches);
		if (codes == null || codes.isEmpty())
			// no matches, quit
			return null;

		// second pass: preserve only the rules that matched.
		ArrayList<RuleSet<T>> arbitrated = null;

		for (Iterator<RuleSet<T>> iterator = matches.iterator(); iterator.hasNext();) {
			RuleSet<T> ruleSet = iterator.next();
			if (codes.contains(ruleSet.promo.code)) {
				// MATCH
				if (arbitrated == null)
					arbitrated = new ArrayList<RuleSet<T>>(codes.size() + 1);
				arbitrated.add(ruleSet);
			} else
				iterator.remove();

		}

		return arbitrated;
	}

	/**
	 * Find a list of codes in the matches. The codes have to be in the supplied
	 * list.
	 */
	public static <T> Set<String> findPromoCodes(List<String> codes, int maxMatches, List<RuleSet<T>> matches) {
		Set<String> actualCodes = new LinkedHashSet<String>(codes.size());
		{
			// List<String> codes = Arrays.asList(group.promos);
			for (RuleSet<?> ruleSet : matches) {
				String code = ruleSet.promo.code;
				if (codes.contains(code))
					actualCodes.add(ruleSet.promo.code);
				if (actualCodes.size() == maxMatches)
					return actualCodes;
			}
		}
		return actualCodes;
	}

	/**
	 * Remove all remaining matches from arbitration group after it is
	 * determined that arbitration is done.
	 */
	static <T> void removeCode(List<RuleSet<T>> matches, String code) {

		Iterator<RuleSet<T>> it = matches.iterator();
		while (it.hasNext()) {
			RuleSet<T> rule = it.next();
			if (code.equals(rule.promo.code))
				it.remove();
		}
	}

	/**
	 * Remove all remaining matches from arbitration group after it is
	 * determined that arbitration is done.
	 */
	static <T> void removeGroup(Iterator<RuleSet<T>> matches, ArbitrationGroup group) {
		while (matches.hasNext()) {
			RuleSet<T> ruleSet = matches.next();
			if (group.hasPromo(ruleSet.promo.code))
				matches.remove();
		}
	}

	static <T> List<RuleSet<T>> arbitrateByDiscount(ArbitrationGroup group, List<RuleSet<T>> matches, T request) {

		ArrayList<DiscountMatch<T>> arbitrated = null;

		for (Iterator<RuleSet<T>> iterator = matches.iterator(); iterator.hasNext();) {
			RuleSet<T> match = iterator.next();
			if (group.hasPromo(match.promo.code)) {
				// MATCH
				if (arbitrated == null)
					arbitrated = new ArrayList<DiscountMatch<T>>(group.max_matches / 2 + 1);

				double discount = calcRuleDiscount(request, match);

				arbitrated.add(new DiscountMatch<T>(match, discount));
				iterator.remove();
			}
		}

		// fast exit
		if (arbitrated == null)
			return null;

		// sort only if we have too many matches. If not too many, we need all
		// the results, no need to sort.
		if (arbitrated.size() > group.max_matches) {
			Collections.sort(arbitrated, DiscountComparator);
			// get only top matches
			arbitrated.subList(group.max_matches, arbitrated.size()).clear();
		}
		return convert(arbitrated);
	}

	public static <T> double calcRuleDiscount(T request, RuleSet<T> match) {
		final List<Action<T>> actions = match.actions;
		if (actions == null)
			return 0d;

		double discount = 0d;
		for (Action<T> action : actions) {
			if (action instanceof DiscountAction)
				discount += ((DiscountAction<T>) action).calculateDiscount(request);
		}

		return discount;
	}

	/**
	 * Conversion method reuses the original ArrayList, taking advantage in the
	 * fact that types are erased at runtime.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> List<RuleSet<T>> convert(ArrayList<DiscountMatch<T>> matches) {
		// we can reuse the same array and avoid unnecessary code and memory
		ArrayList all = matches;
		for (int i = 0; i < all.size(); i++) {
			DiscountMatch<T> match = (DiscountMatch<T>) all.get(i);
			all.set(i, match.ruleset);
		}

		return (List<RuleSet<T>>) all;
	}

	/**
	 * The data of a single arbitration group.
	 * 
	 * @author Elli Albek
	 */
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	public static final class ArbitrationGroup implements Cloneable {

		public ArbitrationGroup() {
		}

		// for tests
		ArbitrationGroup(SelectionOrder selection_order, int max_matches, String... promos) {
			this.selection_order = selection_order;
			this.max_matches = max_matches;
			this.promos = promos;
		}

		@NotNull
		public SelectionOrder selection_order;
		@NotNull
		public int max_matches;
		@NotNull
		@Size(min = 2)
		public String[] promos;

		public static enum SelectionOrder {
			PRIORITY, DISCOUNT, ORDER, MATCH_CANCELS_ALL, MATCH_CANCELS_GROUP;
		}

		/**
		 * Fast iteration / comparison
		 */
		boolean hasPromo(String promo) {
			// localize for speed
			String[] promos = this.promos;
			for (int i = 0; i < promos.length; i++) {
				if (promo.equals(promos[i]))
					return true;
			}
			return false;
		}

		@Override
		public String toString() {
			return "ArbitrationGroup(" + selection_order + ',' + max_matches + ',' + asList(promos) + ')';
		}
	}

	public static class DiscountMatch<T> {
		RuleSet<T> ruleset;
		double discount;

		public DiscountMatch(RuleSet<T> ruleset, double discount) {
			this.ruleset = ruleset;
			this.discount = discount;
		}
	}

	/**
	 * Compare by discount (higher discount first). Secondary comparison is by
	 * priority.
	 */
	private static final Comparator<DiscountMatch<?>> DiscountComparator = new Comparator<DiscountMatch<?>>() {

		@Override
		public int compare(DiscountMatch<?> o1, DiscountMatch<?> o2) {
			if (o1.discount == o2.discount)
				// same discount, use priority as default sort
				return o2.ruleset.promo.priority - o1.ruleset.promo.priority;

			if (o2.discount > o1.discount)
				return 1;

			return -1;
		}
	};
}
