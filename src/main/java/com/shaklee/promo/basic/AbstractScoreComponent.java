package com.shaklee.promo.basic;

import com.shaklee.promo.PromoComponent;
import com.shaklee.rulesets.healthQuestionaire.Questions;

/**
 * Abstract score component that contains common score calculations.
 * 
 * @author ekoca
 */
public abstract class AbstractScoreComponent<T> extends AbstractComponent<T> implements PromoComponent<T>, Cloneable {
	abstract protected float calculate(Questions questions);

	/**
	 * returns a category based on bmi score
	 * 
	 * @param bmi
	 *            score
	 * @return a category based on bmi score
	 */
	protected String bmiCategory(float bmi) {
		if (bmi <= 18.5) {
			return "underweight";
		} else if (bmi < 25) {
			return "normal";
		} else if (bmi < 30) {
			return "overweight";
		}
		return "obese";
	}

	/**
	 * returns bmi factor calculator
	 * 
	 * @param bmi
	 *            score
	 * @return a bmi factor based on bmi score
	 */
	protected float bmiFactor(float bmi) {
		if (bmi < 25f)
			return 1f;
		else if (bmi < 30f)
			return 0.90f;
		else if (bmi < 35f)
			return 0.75f;
		else if (bmi < 40f)
			return 0.6f;
		return 0.4f;
	}
}