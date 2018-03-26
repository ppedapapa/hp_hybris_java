package com.shaklee.promo;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.shaklee.shared.validation.InputValidationException;

/**
 * Runs the entire promo calculation.
 * 
 * request is a cloned instance of the promo data. Meaning it is safe to modify.
 * 
 * @author Elli Albek
 */
@Component
public class PromoEngine<T> {

	private static final Logger logger = LoggerFactory.getLogger(PromoEngine.class);
	private static final boolean DEBUG_ENABLED = logger.isDebugEnabled();

	public void runPromos(PromoExecutionInstance<T> engineLogic, T request) throws InputValidationException {

		final List<Promo<T>> promos = engineLogic.promos;
		// final Arbitrator<T> arbitrator = engineLogic.arbitrator;

		// if (DEBUG_ENABLED)
		// logger.debug("Evaluating promos:\n" + promos);

		List<RuleSet<T>> rulesets = PromoCollections.getRuleSets(promos);
		List<RuleSet<T>> matches = evaluateConditionsAndArbitrate(rulesets, request, engineLogic.arbitrator);
		for (RuleSet<T> rs : matches)
		{
			try
			{
				execMessages(request, rs.actions);
			}
			catch(Exception e)
			{
				logger.debug("Exception details ::::"+e+"\n"+rs);
			}
			
		}
		for (RuleSet<T> rs : matches)
			execMessages(request, rs.messages);
	}

	private static <T> void execMessages(T request, List<Action<T>> actions) throws InputValidationException {
		if (actions != null)
			for (Action<T> action : actions)
				action.exec(request);
	}

	/**
	 * Previous matches are not evaluated again, but used as part of
	 * arbitration.
	 * 
	 * This method always returns a new list and never modifies the input lists.
	 * 
	 * @throws InputValidationException
	 */
	private static <T> List<RuleSet<T>> evaluateConditionsAndArbitrate(List<RuleSet<T>> rulesets, T request,
			Arbitrator<T> arbitrator) throws InputValidationException {
		ArrayList<RuleSet<T>> matches = new ArrayList<RuleSet<T>>(2);

		if (DEBUG_ENABLED && request instanceof RequestLog) {
			// run with debugging
			RequestLog log = (RequestLog) request;
			for (RuleSet<T> rs : rulesets) {
				if (rs.condition.evaluate(request)) {
					matches.add(rs);
					log.debug("RULE MATCH:  " + rs.promo.code + ' ' + rs.id + ' '+ rs.condition);
				}else
				{
					log.debug("RULE FAILED: " + rs.promo.code + ' ' + rs.id + ' '+ rs.condition);
				}
			}
		} else
			// no debugging
			for (RuleSet<T> rs : rulesets) {
				if (rs.condition.evaluate(request))
					matches.add(rs);
			}

		if (arbitrator != null && matches.size() > 1) {
			// multiple matches, need arbitration
			List<RuleSet<T>> ar = arbitrator.arbitrate(matches, request);
			if (ar != null)
				return ar;
		}
		return matches;
	}
}