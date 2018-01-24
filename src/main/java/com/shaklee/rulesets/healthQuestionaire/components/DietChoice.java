package com.shaklee.rulesets.healthQuestionaire.components;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.shaklee.healthPrint.components.AbstractSelectRule;
import com.shaklee.rulesets.healthQuestionaire.Questions;

@Component
public class DietChoice extends AbstractSelectRule<Questions> {

	@NotNull
	public Diet question;

	@Override
	public int getValue(Questions q) {
		switch (question) {
		case fruits:
			return q.fruits;
		case vegetables:
			return q.vegetables;
		case grains:
			return q.grains;
		case dairy:
			return q.dairy;
		case healthy_fats:
			return q.healthy_fats;
		case water:
			return q.water;
		case sugar_drinks:
			return q.sugar_drinks;
		case junk_food:
			return q.junk_food;
		case breakfast:
			return q.breakfast;
		case organic:
			return q.organic;
		}

		throw new IllegalArgumentException("No question " + question);
	}

	public static enum Diet {
		fruits, vegetables, grains, dairy, healthy_fats, water, sugar_drinks, junk_food, breakfast, organic;
	}
}
