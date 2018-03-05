package com.shaklee.rulesets.healthQuestionaire.components.content;

import java.util.HashMap;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.shaklee.healthPrint.data.HPRequest;
import com.shaklee.promo.Action;
import com.shaklee.promo.basic.AbstractComponent;
import com.shaklee.promo.basic.PromoActionUtils;
import com.shaklee.promo.basic.PromoMessage;

/**
 * Multi-Vitamin limit
 * 
 * @author ekoca
 *
 */
@Component
public class MultiVitaminIntroLimit extends AbstractComponent<HPRequest<?, ?, ?>>
		implements Action<HPRequest<?, ?, ?>> {

	private static final String BUNDLE = "bundle";
	private static final String LIMIT = "limit";
	@NotNull
	public String promo_code;
	@NotNull
	public String action_class;

	public List<String> limits;

	@Override
	public void exec(HPRequest<?, ?, ?> q) {
		// Step 1: verify PromoCode And Action class is available
		boolean success = MultiVitaminHelper.hasPromoCodeAndAction(q.response, promo_code, action_class);

		// add messages
		if (success) {
			for (String limit : limits) {
				String[] limitAr = limit.split("\\s+");
				if (limitAr.length > 1) {
					PromoMessage message = new PromoMessage();
					message.key = limitAr[0] + "_" + LIMIT;
					message.params = new HashMap<String, Object>();
					message.params.put(BUNDLE, limitAr[0]);
					message.params.put(LIMIT, limitAr[1]);
					PromoActionUtils.addMessage(q, ruleset, message);
				}
			}
		}
	}
}