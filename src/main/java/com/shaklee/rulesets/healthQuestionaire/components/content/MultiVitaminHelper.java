package com.shaklee.rulesets.healthQuestionaire.components.content;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.shaklee.common.util.collections.ContingMap;
import com.shaklee.healthPrint.data.Bundle;
import com.shaklee.healthPrint.data.HPRequest;
import com.shaklee.healthPrint.data.SKU;
import com.shaklee.healthPrint.data.SKUList;
import com.shaklee.promo.PromoRequest.PromoAction;
import com.shaklee.promo.basic.PromoMessage;
import com.shaklee.rulesets.healthQuestionaire.Questions;
import com.shaklee.rulesets.healthQuestionaire.Questions.HealthGoal;
import com.shaklee.rulesets.healthQuestionaire.components.HasPromoCodeAndAction;
import com.shaklee.rulesets.healthQuestionaire.components.content.MultiVitaminIntro.MVIntro;

/**
 * Helper class for the multi-vitamin introduction implementation
 * 
 * @author ekoca
 *
 */
public class MultiVitaminHelper {

	public static final String BUNDLES = "bundles";
	public static final String VITALIZER = "vitalizer";
	public static final String LIFE_STRIP = "life_strip";
	public static final List<Bundle> ALL_BUNDLES = Bundle.ALL_TIERS;

	public static boolean hasPromoCodeAndAction(List<PromoAction> response, String promo_code, String action_class) {
		if (response != null) {
			for (PromoAction pa : response) {
				if (HasPromoCodeAndAction.verifyPromoCode(pa, promo_code)) {
					for (com.shaklee.promo.PromoRequest.Action action : pa.actions) {
						// one of these action is enough
						if (HasPromoCodeAndAction.verifyClass(action, action_class)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public static MVIntro createMultiVitaminBean(final String[] rule) {
		MVIntro mvIntro = new MVIntro();
		mvIntro.question = rule[0].trim();
		mvIntro.answer = Integer.valueOf(rule[1].trim());
		mvIntro.group = Integer.valueOf(rule[2].trim());
		mvIntro.priority = Integer.valueOf(rule[3].trim());
		mvIntro.key = rule[4].trim();
		mvIntro.skus = new ArrayList<String>(rule.length - 4);
		for (int i = 5; i < rule.length; i++) {
			// mvIntro.applicable.add(Bundle.valueOf(rule[i]));
			mvIntro.skus.add(rule[i]);
		}
		return mvIntro;
	}

	public static List<MVIntro> createMultiVitaminRules(List<String> question_rules) {
		List<MVIntro> rules = new ArrayList<MVIntro>(30);
		for (final String qRule : question_rules) {
			final String[] rule = qRule.split("\\s+");
			MVIntro mvIntro = MultiVitaminHelper.createMultiVitaminBean(rule);
			rules.add(mvIntro);
		}
		return rules;
	}

	public static void addAllFillerMessages(HPRequest<?, Bundle, SKU> q, List<MVIntro> macthes,
			List<MVIntro> fillerMessages) {
		for (MVIntro msg : fillerMessages) {
			applyToBundles(q, msg);
			macthes.add(msg);
		}
	}

	public static void addFillerMessageByTier(HPRequest<?, Bundle, SKU> q, List<MVIntro> macthes,
			List<MVIntro> fillerMessages, String type) {
		for (MVIntro mvIntro : fillerMessages) {
			if (mvIntro.question.equals(type)) {
				applyToBundles(q, mvIntro);
				macthes.add(mvIntro);
			}
		}
	}

	public static void applyToBundles(HPRequest<?, Bundle, SKU> q, MVIntro rule) {
		rule.bundles = new ArrayList<Bundle>(3);
		for (SKUList<Bundle, SKU> bundle : q.bundles) {
			if (ALL_BUNDLES.contains(bundle.getBundle())) {
				for (SKU sku : bundle.skus) {
					if (rule.skus.contains(sku.sku))
						rule.bundles.add(bundle.getBundle());
				}
			}
		}
	}

	public static ContingMap<Bundle> countMessagesByTiers(List<MVIntro> matches) {
		ContingMap<Bundle> counter = new ContingMap<Bundle>();
		for (MVIntro mvIntro : matches) {
			for (Bundle bundle : mvIntro.bundles) {
				counter.increment(bundle);
			}
		}
		return counter;
	}

	public static PromoMessage createPromoMessage(MVIntro mvIntro) {
		PromoMessage message = new PromoMessage();
		message.key = mvIntro.key;
		return message;
	}

	public static enum MultiVitaminQuestion {
		energy, stress, memory, sleep, exercise_frequency, exercise_intensity, spending, fruits, vegetables, grains, dairy, healthy_fats, water, sugar_drinks, junk_food, breakfast, organic;

		public static int getAnswer(String question, Questions req) {
			switch (MultiVitaminQuestion.valueOf(question)) {
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

	public static boolean containsHealthGoal(String goal, Questions req) {
		// check goals
		return req.health_goals.contains(HealthGoal.valueOf(goal));
	}

	public static class CompareByGroupAndPriority implements Comparator<MVIntro> {
		@Override
		public int compare(MVIntro current, MVIntro next) {
			int c = current.group - next.group;
			if (c == 0) {
				return current.priority - next.priority;
			}
			return c;
		}
	}

	static final CompareByGroupAndPriority compareByGroupAndPriority = new CompareByGroupAndPriority();

	public static class CompareByPriority implements Comparator<MVIntro> {
		@Override
		public int compare(MVIntro current, MVIntro next) {
			return current.priority - next.priority;
		}
	}

	static final CompareByPriority compareByPriority = new CompareByPriority();
}
