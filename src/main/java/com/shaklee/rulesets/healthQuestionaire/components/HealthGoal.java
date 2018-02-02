package com.shaklee.rulesets.healthQuestionaire.components;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.shaklee.common.util.CollectionUtils;
import com.shaklee.promo.Condition;
import com.shaklee.promo.PromoRequest;
import com.shaklee.promo.basic.AbstractComponent;
import com.shaklee.rulesets.healthQuestionaire.Questions;
import com.shaklee.shared.validation.InputValidationException;

@Component
public class HealthGoal extends AbstractComponent<Questions> implements Condition<PromoRequest<Questions>> {

	@NotNull
	public Questions.HealthGoal goal;

	public int[] position;

	@Override
	public boolean evaluate(PromoRequest<Questions> q) throws InputValidationException {
		return checkGoal(q, position, goal);
	}

	static boolean checkGoal(PromoRequest<Questions> q, int[] positions, Questions.HealthGoal goal) {
		final List<Questions.HealthGoal> healthGoals = q.request.health_goals;

		if (healthGoals == null)
			// no goals in input.
			return false;

		if (positions == null || positions.length == 0)
			// no position in the array, goal can be anywhere in it.
			return healthGoals.contains(goal);
		else
			// has positions array, goal must be in a certain place in the input
			// array as dictated by positions.
			for (int i = 0; i < positions.length; i++) {
				int position = positions[i];
				Questions.HealthGoal actualGoal = CollectionUtils.get(healthGoals, position);
				if (goal == actualGoal)
					return true;
			}

		return false;
	}
}

