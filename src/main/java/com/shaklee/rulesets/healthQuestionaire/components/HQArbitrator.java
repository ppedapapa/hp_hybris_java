package com.shaklee.rulesets.healthQuestionaire.components;

import static com.shaklee.promo.impl.MultipleArbitrationGroupsArbitrator.findPromoCodes;
import static java.util.Arrays.asList;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.shaklee.promo.Arbitrator;
import com.shaklee.promo.RuleSet;

/**
 * Arbitrator for health questionaire, since using standard arbitrator turned
 * too complicated.
 * 
 * @author Elli Albek
 *
 * @param <T>
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class HQArbitrator<T> implements Arbitrator<T> {

	// private static final Logger LOGGER =
	// LoggerFactory.getLogger(HQArbitrator.class);
	// private static boolean isDebugEnabled = LOGGER.isDebugEnabled();

	public List<ArbitrationGroup> groups;

	@Override
	public List<RuleSet<T>> arbitrate(List<RuleSet<T>> matches, T request) {
		if (groups == null || matches.isEmpty())
			// nothing to do
			return matches;

		// linked list is faster for removals in the middle
		// this is also a defensive copy
		matches = new LinkedList<RuleSet<T>>(matches);
		for (ArbitrationGroup group : groups) {
			matches = arbitrate(group, matches, request);
		}

		return matches;
	}

	static <T> List<RuleSet<T>> arbitrate(ArbitrationGroup group, List<RuleSet<T>> matches, T request) {

		switch (group.arbitration) {
		case ONE_OF:
			return arbitrateOneOf(group, matches);

		case LAST:
			return arbitrateLast(group, matches);
		}
		return null;
	}

	public static <T> List<RuleSet<T>> arbitrateOneOf(ArbitrationGroup group, List<RuleSet<T>> matches) {
		// remove exclusive matches.
		return arbitrateMatchCancelsGroup(group.promos, 1, matches);
	}

	public static <T> List<RuleSet<T>> arbitrateLast(ArbitrationGroup group, List<RuleSet<T>> matches) {

		// handle terminal matches
		Collection<String> lastMatches = findPromoCodes(group.promos, 1000, matches);
		if (lastMatches.size() > 0) {
			// remove everything after the last matches, but excluding the
			// final steps (which are also promos)
			for (Iterator<RuleSet<T>> iterator = matches.iterator(); iterator.hasNext();) {
				RuleSet<T> ruleSet = iterator.next();
				if (!lastMatches.contains(ruleSet.promo.code)) {
					// found a suspect promo
					if (ruleSet.promo.priority >= group.exclude_priority_min
							&& ruleSet.promo.priority < group.exclude_priority_max)
						// exclude only promos with priority higher than the
						// threshold
						iterator.remove();
				}
			}
		}

		return matches;
	}

	/**
	 * The data of a single arbitration group.
	 * 
	 * @author Elli Albek
	 */
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	public static final class ArbitrationGroup implements Cloneable {

		@NotNull
		public Arbitration arbitration;

		@NotNull
		public int exclude_priority_min = Integer.MAX_VALUE;

		@NotNull
		public int exclude_priority_max = Integer.MAX_VALUE;

		@NotNull
		@Size(min = 1)
		public List<String> promos;

		public static enum Arbitration {
			ONE_OF, LAST;
		}

		//TODO: Why is this code using asList on a list?
		@Override
		public String toString() {
			return "ArbitrationGroup(" + arbitration + ',' + asList(promos) + ')';
		}
	}

	/**
	 * If one (or more) of the specified promos match, cancels all others in the
	 * groups. If that promo exists, all rulsets of that promo will be
	 * preserved.
	 * 
	 * This is implemented as double pass. First pass creates the list of promos
	 * to keep, second pass creates the final list of rulesets.
	 */
	public static <T> List<RuleSet<T>> arbitrateMatchCancelsGroup(List<String> groupCodes, int maxMatches,
			List<RuleSet<T>> matches) {
		// first pass: list of promos to keep
		Set<String> matchedCodes = findPromoCodes(groupCodes, maxMatches, matches);
		if (matchedCodes == null || matchedCodes.isEmpty())
			// no matches, quit
			return matches;

		// second pass: preserve only the rules that matched.

		for (Iterator<RuleSet<T>> iterator = matches.iterator(); iterator.hasNext();) {
			RuleSet<T> ruleSet = iterator.next();
			if (groupCodes.contains(ruleSet.promo.code)) {
				// promo is in the exclusive group,
				if (!matchedCodes.contains(ruleSet.promo.code))
					// in the exclude group, but did not match this time
					iterator.remove();
			}
		}

		return matches;
	}

}