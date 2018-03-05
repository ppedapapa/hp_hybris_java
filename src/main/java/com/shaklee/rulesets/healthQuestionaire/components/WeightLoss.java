package com.shaklee.rulesets.healthQuestionaire.components;

import static com.shaklee.rulesets.healthQuestionaire.Questions.HealthGoal.WEIGHT;
import static com.shaklee.rulesets.healthQuestionaire.components.HealthGoals.checkGoal;

import org.springframework.stereotype.Component;

import com.shaklee.promo.Condition;
import com.shaklee.promo.PromoRequest;
import com.shaklee.promo.basic.AbstractComponent;
import com.shaklee.rulesets.healthQuestionaire.Questions;
import com.shaklee.shared.validation.InputValidationException;

/**
 * A combined condition to simplify weight loss conditions.
 * 
 * @author Elli Albek
 */
@Component
public class WeightLoss extends AbstractComponent<Questions> implements Condition<PromoRequest<Questions>> {

	public int bmi = 30;

	private BMI bmiCondition;
	private CommonExclusions comm;
	
	public WeightLoss() {
		bmiCondition = new BMI();
		bmiCondition.min = bmi;
		comm = new CommonExclusions();
	}

	public void setBmi(int bmi) {
		bmiCondition.min = bmi;
	}

	@Override
	public boolean evaluate(PromoRequest<Questions> q) throws InputValidationException {

		// common exclusions
		if (comm.evaluate(q) == false)
			return false;

		// first goal
		if (checkGoal(q, 0, WEIGHT))
			return true;

		// second goal + BMI
		if (checkGoal(q, 1, WEIGHT) && bmiCondition.evaluate(q))
			return true;

		return false;
	}
}