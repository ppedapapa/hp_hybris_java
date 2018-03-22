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

	public static int getUserAnswer(String questionType, Questions questions) {
		switch (HPContentQuestionUtils.valueOf(questionType)) {
		// lifestyle
		case energy:
			return questions.energy;
		case stress:
			return questions.stress;
		case memory:
			return questions.memory;
		case sleep:
			return questions.sleep;
		case exercise_frequency:
			return questions.exercise_frequency;
		case exercise_intensity:
			return questions.exercise_intensity;
		// diet
		case fruits:
			return questions.fruits;
		case vegetables:
			return questions.vegetables;
		case grains:
			return questions.grains;
		case dairy:
			return questions.dairy;
		case healthy_fats:
			return questions.healthy_fats;
		case water:
			return questions.water;
		case sugar_drinks:
			return questions.sugar_drinks;
		case junk_food:
			return questions.junk_food;
		case breakfast:
			return questions.breakfast;
		case organic:
			return questions.organic;
		default:
			break;
		}
		throw new IllegalArgumentException("No question " + questionType);
	}
}
