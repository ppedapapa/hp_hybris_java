package com.shaklee.rulesets.healthQuestionaire.components;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.shaklee.healthPrint.data.HPRequest;
import com.shaklee.promo.Condition;
import com.shaklee.promo.PromoRequest;
import com.shaklee.promo.PromoRequest.PromoAction;
import com.shaklee.promo.basic.AbstractComponent;
import com.shaklee.shared.validation.InputValidationException;

/**
 * I never use this component so please test it throughly before referencing
 * this class anywhere else
 * 
 * @author ekoca
 *
 */
@Component
public class HasPromoCodeAndAction extends AbstractComponent<PromoRequest<?>> implements Condition<HPRequest<?, ?, ?>> {

	@NotNull
	public String promo_code;

	@NotNull
	public String action_class;

	public static boolean verifyPromoCode(PromoAction pa, String promo_code) {
		return pa.promo_code.equals(promo_code);
	}

	public static boolean verifyClass(com.shaklee.promo.PromoRequest.Action action, String action_class) {
		return action.getClass().getName().endsWith(action_class);
	}

	public boolean hasPromoCodeAndAction(HPRequest<?, ?, ?> q) {
		if (q.response != null) {
			for (PromoAction pa : q.response) {
				if (verifyPromoCode(pa, promo_code)) {
					for (com.shaklee.promo.PromoRequest.Action action : pa.actions) {
						// one of these action is enough
						if (verifyClass(action, action_class)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean evaluate(HPRequest<?, ?, ?> q) throws InputValidationException {
		return hasPromoCodeAndAction(q);
	}
}