package com.shaklee.rulesets.healthQuestionaire.components.content;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.shaklee.common.util.JSONSerializer;
import com.shaklee.common.util.collections.ContingMap;
import com.shaklee.healthPrint.data.Bundle;
import com.shaklee.healthPrint.data.HPRequest;
import com.shaklee.healthPrint.data.SKU;
import com.shaklee.promo.Action;
import com.shaklee.promo.basic.AbstractComponent;
import com.shaklee.promo.basic.PromoActionUtils;
import com.shaklee.promo.basic.PromoMessage;
import com.shaklee.rulesets.healthQuestionaire.Questions;
import com.shaklee.rulesets.healthQuestionaire.components.content.MultiVitaminHelper.MultiVitaminQuestion;

/**
 * Multi-Vitamin introduction
 * 
 * @author ekoca
 *
 */
@Component
public class MultiVitaminIntro extends AbstractComponent<HPRequest<Questions, Bundle, SKU>> implements Action<HPRequest<Questions, Bundle, SKU>> {

	@NotNull
	public String promo_code;
	@NotNull
	public String action_class;
	public List<String> question_rules;
	public List<String> goal_rules;
	public List<String> filler_messages;

	private List<MVIntro> questionRules;
	private List<MVIntro> goalRules;
	private List<MVIntro> fillerMessages;

	public void setQuestion_rules(List<String> question_rules) {
		questionRules = MultiVitaminHelper.createMultiVitaminRules(question_rules);
	}

	public void setGoal_rules(List<String> goal_rules) {
		goalRules = MultiVitaminHelper.createMultiVitaminRules(goal_rules);
	}

	public void setFiller_messages(List<String> filler_messages) {
		fillerMessages = MultiVitaminHelper.createMultiVitaminRules(filler_messages);
	}

	@Override
	public void exec(HPRequest<Questions, Bundle, SKU> q) {
		// Step 1: verify PromoCode And Action class is available
		boolean success = MultiVitaminHelper.hasPromoCodeAndAction(q.response, promo_code, action_class);

		// add messages
		if (success) {
			List<MVIntro> macthes = getMacthedRules(q);
			arbitrate(macthes);
			setFillerMessages(q, macthes);
			arbitrate(macthes);
			createPromoMessage(q, macthes);
		}
	}

	private void setFillerMessages(HPRequest<Questions, Bundle, SKU> q, List<MVIntro> macthes) {
		if (macthes.size() == 1) {
			MultiVitaminHelper.addAllFillerMessages(q, macthes, fillerMessages);
		} else {
			ContingMap<Bundle> counter = MultiVitaminHelper.countMessagesByTiers(macthes);
			if (!counter.isEmpty()) {
				int tier2Count = counter.getValue(Bundle.TIER_2);
				int tier3Count = counter.getValue(Bundle.TIER_3);
				if (tier2Count < 2) {
					tier2Count++;
					tier3Count++;
					MultiVitaminHelper.addFillerMessageByTier(q, macthes, fillerMessages, MultiVitaminHelper.VITALIZER);
				}
				if (tier3Count < 3 || tier2Count == tier3Count) {
					tier3Count++;
					MultiVitaminHelper.addFillerMessageByTier(q, macthes, fillerMessages,
							MultiVitaminHelper.LIFE_STRIP);
				}
			}
		}
	}

	private void createMessage(HPRequest<Questions, Bundle, SKU> q, MVIntro mvIntro) {
		PromoMessage message = new PromoMessage();
		message.key = mvIntro.key;
		if (mvIntro.skus != null) {
			message.params = new HashMap<String, Object>();
			message.params.put(MultiVitaminHelper.BUNDLES, mvIntro.bundles);
		}
		PromoActionUtils.addMessage(q, ruleset, message);
	}

	private void arbitrate(List<MVIntro> macthes) {
		arbitrateMatchCancelsGroup(macthes);
		arbitrateMatchCancelsSamePriority(macthes);
	}

	private void createPromoMessage(HPRequest<Questions, Bundle, SKU> q, List<MVIntro> macthes) {
		for (MVIntro mvIntro : macthes) {
			createMessage(q, mvIntro);
		}
	}

	private void getMacthedGoalRules(HPRequest<Questions, Bundle, SKU> q, List<MVIntro> macthes) {
		if (q.request.health_goals == null)
			return;

		for (MVIntro rule : goalRules) {
			boolean contains = MultiVitaminHelper.containsHealthGoal(rule.question, q.request);
			if (contains) {
				MultiVitaminHelper.applyToBundles(q, rule);
				macthes.add(rule);
			}
		}
	}

	private List<MVIntro> getMacthedRules(HPRequest<Questions, Bundle, SKU> q) {
		List<MVIntro> macthes = new ArrayList<MVIntro>();
		for (MVIntro rule : questionRules) {
			int choosen = MultiVitaminQuestion.getAnswer(rule.question, q.request);
			if (choosen == rule.answer) {
				MultiVitaminHelper.applyToBundles(q, rule);
				macthes.add(rule);
			}
		}
		// Get macthes for Goal
		getMacthedGoalRules(q, macthes);

		return macthes;
	}

	private void arbitrateMatchCancelsGroup(List<MVIntro> macthes) {
		Collections.sort(macthes, MultiVitaminHelper.compareByGroupAndPriority);
		Iterator<MVIntro> it = macthes.iterator();
		MVIntro prev = it.next();
		while (it.hasNext()) {
			MVIntro current = it.next();
			if (prev.group == current.group) {
				if (current.priority >= prev.priority) {
					it.remove();
				}
			} else {
				prev = current;
			}
		}
	}

	private void arbitrateMatchCancelsSamePriority(List<MVIntro> macthes) {
		Collections.sort(macthes, MultiVitaminHelper.compareByPriority);
		Iterator<MVIntro> it = macthes.iterator();
		MVIntro prev = it.next();
		while (it.hasNext()) {
			MVIntro current = it.next();
			if (prev.priority == current.priority) {
				it.remove();
			} else {
				prev = current;
			}
		}
	}

	/**
	 * Multi-vitamin Introduction plain java bean representation
	 * 
	 * @author ekoca
	 *
	 */
	protected static class MVIntro {
		public String question;
		public int answer;
		public int priority;
		public int group;
		public String key;
		public List<String> skus;
		public List<Bundle> bundles;

		@Override
		public String toString() {
			return JSONSerializer.toJacksonJaxbJson(this, true);
		}

	}
}