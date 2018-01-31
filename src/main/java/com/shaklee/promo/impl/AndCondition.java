package com.shaklee.promo.impl;

import java.util.Arrays;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.shaklee.promo.Condition;
import com.shaklee.promo.RequestLog;
import com.shaklee.promo.RuleSet;
import com.shaklee.shared.validation.InputValidationException;

/**
 * Simple implementation of AND condition (on a group of other conditions)
 * 
 * @author Elli Albek
 */
@JsonSerialize
public class AndCondition<T> implements Condition<T> {

	public static final Logger logger = LoggerFactory.getLogger(AndCondition.class);
	public static final boolean isDebugEnabled = logger.isDebugEnabled();

	@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "class")
	public final Condition<T>[] conditions;

	private final RuleSet<T> ruleset;

	@SuppressWarnings("unchecked")
	public AndCondition(Collection<Condition<T>> conditions, RuleSet<T> ruleset) {
		if (conditions.isEmpty())
			throw new LoaderException(
					"AndCondition must have at least one nested condition");

		this.conditions = conditions.toArray(new Condition[conditions.size()]);
		this.ruleset = ruleset;
	}

	@Override
	public boolean evaluate(T t) throws InputValidationException {
		final Condition<T>[] conditions = this.conditions;
		if (isDebugEnabled && t instanceof RequestLog) {
			RequestLog log = (RequestLog) t;
			// debug execution, logs failures
			for (int i = 0; i < conditions.length; i++) {
				if (conditions[i].evaluate(t) == false) {
					log.debug("Condition failed: " + ruleset.promo.code
							+ ' ' + ruleset.id 
							+ ' ' + conditions[i]);
					return false;
				}
			}
			return true;
		} else {
			// no debug, faster execution with less code and check1s
			for (int i = 0; i < conditions.length; i++) {
				if (conditions[i].evaluate(t) == false)
					return false;
			}
			return true;
		}
	}

	@Override
	public String toString() {
		return "AndCondition" + Arrays.asList(conditions);
	}
}
