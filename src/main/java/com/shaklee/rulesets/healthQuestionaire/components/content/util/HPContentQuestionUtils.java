package com.shaklee.rulesets.healthQuestionaire.components.content.util;

import com.shaklee.rulesets.healthQuestionaire.Questions;

/**
 * Content questions utils that return choosen answer in the request.
 * 
 * @author ekoca
 *
 */
public enum HPContentQuestionUtils {
	energy, stress, memory, sleep, exercise_frequency, exercise_intensity, spending, fruits, vegetables, grains, dairy, healthy_fats, water, sugar_drinks, junk_food, breakfast, organic;

	public static int getAnswer(String question, Questions req) {
		switch (HPContentQuestionUtils.valueOf(question)) {
		// lifestyle
		case energy:
			return req.energy;
		case stress:
			return req.stress;
		case memory:
			return req.memory;
		case sleep:
			return req.sleep;
		case exercise_frequency:
			return req.exercise_frequency;
		case exercise_intensity:
			return req.exercise_intensity;
		// diet
		case fruits:
			return req.fruits;
		case vegetables:
			return req.vegetables;
		case grains:
			return req.grains;
		case dairy:
			return req.dairy;
		case healthy_fats:
			return req.healthy_fats;
		case water:
			return req.water;
		case sugar_drinks:
			return req.sugar_drinks;
		case junk_food:
			return req.junk_food;
		case breakfast:
			return req.breakfast;
		case organic:
			return req.organic;
		default:
			break;
		}
		throw new IllegalArgumentException("No question " + question);
	}
}
