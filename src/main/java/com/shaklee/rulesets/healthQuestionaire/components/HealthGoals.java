
package com.shaklee.rulesets.healthQuestionaire.components;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.shaklee.common.util.CollectionUtils;
import com.shaklee.promo.Condition;
import com.shaklee.promo.PromoRequest;
import com.shaklee.promo.basic.AbstractComponent;
import com.shaklee.rulesets.healthQuestionaire.Questions;
import com.shaklee.shared.validation.InputValidationException;

@Component
public class HealthGoals extends AbstractComponent<Questions> implements Condition<PromoRequest<Questions>> {

	@NotNull
	public List<Questions.HealthGoal> goal;

	@Min(0)
	public int position = -1;

	@Override
	public boolean evaluate(PromoRequest<Questions> q) throws InputValidationException {
		return checkGoal(q, position, goal);
	}

	static boolean checkGoal(PromoRequest<Questions> q, int position, List<Questions.HealthGoal> goals) {
		com.shaklee.rulesets.healthQuestionaire.Questions.HealthGoal actualGoal = CollectionUtils
				.get(q.request.health_goals, position);

		return goals.contains(actualGoal);
	}

	static boolean checkGoal(PromoRequest<Questions> q, int position, Questions.HealthGoal goal) {
		Questions.HealthGoal actualGoal = CollectionUtils.get(q.request.health_goals, position);

		return goal == actualGoal;
	}
}