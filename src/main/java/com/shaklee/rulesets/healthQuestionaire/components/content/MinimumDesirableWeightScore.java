package com.shaklee.rulesets.healthQuestionaire.components.content;

import com.shaklee.healthPrint.data.Bundle;
import com.shaklee.healthPrint.data.HealthPrintContentRequest;
import com.shaklee.healthPrint.data.SKU;
import com.shaklee.promo.Action;
import com.shaklee.promo.basic.AbstractComponent;
import com.shaklee.rulesets.healthQuestionaire.Questions;
import com.shaklee.shared.validation.InputValidationException;

/**
 * Calculate the minimum desirable weight score based on request
 * 
 * @author ekoca
 *
 */
public class MinimumDesirableWeightScore extends AbstractComponent<HealthPrintContentRequest<Questions, Bundle, SKU>>
		implements Action<HealthPrintContentRequest<Questions, Bundle, SKU>> {

	@Override
	public void exec(HealthPrintContentRequest<Questions, Bundle, SKU> request) throws InputValidationException {
		request.score.min_desirable_weight = calculateLowWeight(request.request);
	}

	/**
	 * Calculates the minimum desirable weight score
	 * 
	 * @param request
	 * @return integer
	 */
	private int calculateLowWeight(Questions request) {
		return (int) (Math.pow(request.height_inches, 2) * 18.5 / 703);
	}
}
