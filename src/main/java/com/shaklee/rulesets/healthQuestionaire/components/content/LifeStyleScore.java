package com.shaklee.rulesets.healthQuestionaire.components.content;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

import com.shaklee.healthPrint.data.Bundle;
import com.shaklee.healthPrint.data.HealthPrintContentRequest;
import com.shaklee.healthPrint.data.SKU;
import com.shaklee.promo.Action;
import com.shaklee.promo.basic.AbstractScoreComponent;
import com.shaklee.rulesets.healthQuestionaire.Questions;
import com.shaklee.rulesets.healthQuestionaire.components.content.util.HPContentProviderUtils;
import com.shaklee.rulesets.healthQuestionaire.components.content.util.HPContentQuestionUtils;
import com.shaklee.shared.validation.InputValidationException;

@Component
public class LifeStyleScore extends AbstractScoreComponent<HealthPrintContentRequest<Questions, Bundle, SKU>>
		implements Action<HealthPrintContentRequest<Questions, Bundle, SKU>> {

	public Map<String, List<Integer>> scores;

	/**
	 * Generates the LifeStyle score based on request (non-Javadoc)
	 * 
	 * @see com.shaklee.promo.Action#exec(java.lang.Object)
	 */
	@Override
	public void exec(HealthPrintContentRequest<Questions, Bundle, SKU> request) throws InputValidationException {
		// Calculate LifeStyle score
		request.score.life_score = (int) (calculate(request.request) * bmiFactor(request.score.bmi));

	}

	@Override
	protected float calculate(Questions questions) {
		// scores
		int score = 0;
		for (Entry<String, List<Integer>> scores : scores.entrySet()) {
			final String question = scores.getKey();
			if (HPContentProviderUtils.isRestricted(question, questions) == false) {
				final int answer = HPContentQuestionUtils.getUserAnswer(question, questions);
				score += scores.getValue().get(answer);
			}
		}
		return ((float) score / (scores.values().size()));
	}
}
