package com.shaklee.rulesets.healthQuestionaire.components;

import org.springframework.stereotype.Component;

import com.shaklee.healthPrint.data.Bundle;
import com.shaklee.healthPrint.data.HPRequest;
import com.shaklee.promo.Action;
import com.shaklee.promo.basic.AbstractComponent;

/**
 * Set recommended bundle
 * 
 * @author Elli Albek
 */
@Component
public class BundleRecommendation extends AbstractComponent<Object>
		implements Action<HPRequest<?, Bundle, ?>> {

	public Bundle bundle;

	@Override
	public void exec(HPRequest<?, Bundle, ?> q) {
		q.recommended = bundle;
	}
}