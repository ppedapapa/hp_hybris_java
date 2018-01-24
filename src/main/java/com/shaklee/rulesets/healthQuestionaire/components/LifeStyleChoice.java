package com.shaklee.rulesets.healthQuestionaire.components;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.shaklee.healthPrint.components.AbstractSelectRule;
import com.shaklee.rulesets.healthQuestionaire.Questions;

@Component
public class LifeStyleChoice extends AbstractSelectRule<Questions> {

	@NotNull
	public LifeStyleQuestion question;

	@Override
	public int getValue(Questions q) {
		switch (question) {
		case ENERGY:
			return q.energy;
		case STRESS:
			return q.stress;
		case MEMORY:
			return q.memory;
		case SLEEP:
			return q.sleep;
		case EXERCISE_FREQUENCY:
			return q.exercise_frequency;
		case EXERCISE_INTENSITY:
			return q.exercise_intensity;
		case TOXINS:
			return q.toxins;
		case SPENDING:
			return q.spending;
		}

		throw new IllegalArgumentException("No question " + question);
	}

	public static enum LifeStyleQuestion {
		ENERGY, STRESS, MEMORY, SLEEP, EXERCISE_FREQUENCY, EXERCISE_INTENSITY, TOXINS, SPENDING
	}
}
