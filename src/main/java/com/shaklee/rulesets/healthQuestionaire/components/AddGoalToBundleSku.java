package com.shaklee.rulesets.healthQuestionaire.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.stereotype.Component;

import com.shaklee.healthPrint.data.Bundle;
import com.shaklee.healthPrint.data.HPRequest;
import com.shaklee.healthPrint.data.SKU;
import com.shaklee.healthPrint.data.SKUList;
import com.shaklee.promo.Action;
import com.shaklee.promo.basic.AbstractComponent;
import com.shaklee.rulesets.healthQuestionaire.Questions;
import com.shaklee.rulesets.healthQuestionaire.Questions.HealthGoal;

/**
 * Add goal tag to bundle SKUs if applicable.
 * 
 * @author Emre Koca
 */
@Component
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class AddGoalToBundleSku extends AbstractComponent<HPRequest<Questions, Bundle, SKU>>
		implements Action<HPRequest<Questions, Bundle, SKU>> {
	public Map<HealthGoal, List<String>> goal_skus;

	public void setGoal_skus(Map<HealthGoal, List<String>> goal_skus) {
		this.goal_skus = goal_skus;
	}

	@Override
	public void exec(HPRequest<Questions, Bundle, SKU> req) {
		if (req.bundles == null || req.request.health_goals == null)
			return;

		for (SKUList<Bundle, SKU> b : req.bundles) {
			Collection<SKU> bundleSkus = b.skus;
			groupSKUsByGoal(bundleSkus, req.request.health_goals);
			System.out.println(b.skus);
		}
	}

	private void groupSKUsByGoal(Collection<SKU> bundleSkus, List<HealthGoal> health_goals) {
		for (Map.Entry<HealthGoal, List<String>> entry : goal_skus.entrySet()) {
			HealthGoal goal = entry.getKey();
			if (health_goals.contains(goal)) {
				List<String> goalSkus = entry.getValue();
				for (SKU sku : bundleSkus) {
					if (goalSkus.contains(sku.sku)) {
						if (sku.goal == null) {
							sku.goal = new ArrayList<String>();
						}
						sku.goal.add(goal.toString().toLowerCase());
					}
				}
			}
		}

	}

	public static class NullComparators implements Comparator<SKU> {
		@Override
		public int compare(SKU current, SKU next) {
			if (current == null && next == null) {
				return 0;
			}

			if (current == null) {
				return 1;
			}

			return -1;
		}
	}

	static final NullComparators nullAtEnd = new NullComparators();

}