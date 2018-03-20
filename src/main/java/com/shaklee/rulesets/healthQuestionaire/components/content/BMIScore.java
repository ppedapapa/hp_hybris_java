package com.shaklee.rulesets.healthQuestionaire.components.content;

import static com.shaklee.rulesets.healthQuestionaire.components.BMI.bmi;

import org.springframework.stereotype.Component;

import com.shaklee.healthPrint.data.Bundle;
import com.shaklee.healthPrint.data.HealthPrintContentRequest;
import com.shaklee.healthPrint.data.SKU;
import com.shaklee.promo.Action;
import com.shaklee.promo.basic.AbstractComponent;
import com.shaklee.rulesets.healthQuestionaire.Questions;
import com.shaklee.shared.validation.InputValidationException;

/**
 * Calculate the BMI score based on request
 * 
 * @author ekoca
 *
 */
@Component
public class BMIScore extends AbstractComponent<HealthPrintContentRequest<Questions, Bundle, SKU>>
		implements Action<HealthPrintContentRequest<Questions, Bundle, SKU>> {
	/**
	 * Generates the BMI score based on request (non-Javadoc)
	 * 
	 * @see com.shaklee.promo.Action#exec(java.lang.Object)
	 */
	@Override
	public void exec(HealthPrintContentRequest<Questions, Bundle, SKU> request) throws InputValidationException {
		// Calculate BMI score
		request.score.bmi = bmi(request.request);
	}
}