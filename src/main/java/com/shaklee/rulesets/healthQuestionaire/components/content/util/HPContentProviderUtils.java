package com.shaklee.rulesets.healthQuestionaire.components.content.util;

import com.shaklee.rulesets.healthQuestionaire.Questions;

/**
 * Helper class for the multi-vitamin introduction implementation
 * 
 * @author ekoca
 *
 */
public class HPContentProviderUtils {
	/**
	 * Check if user has any restriction on diet restrictions
	 * 
	 * @param key
	 * @param request
	 * @return boolean
	 */
	public static boolean isRestricted(String question, Questions questions) {
		return questions.dietary_restrictions.stream().anyMatch(r -> r.name().equals(question.toUpperCase()));
	}
}
