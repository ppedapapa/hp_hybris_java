package com.shaklee.promo.basic;

import java.util.ArrayList;

import com.shaklee.promo.PromoRequest;
import com.shaklee.promo.PromoRequest.Action;
import com.shaklee.promo.PromoRequest.PromoAction;
import com.shaklee.promo.RuleSet;

/**
 * Utility methods for objects that need to manipulate the cart, mostly promo
 * actions that are cart specific.
 * 
 * @author Elli Albek
 */
public class PromoActionUtils {

	public static PromoAction getOrCreatePromoAction(PromoRequest<?> req, RuleSet<?> ruleset) {

		if (req.response != null) {
			for (PromoRequest.PromoAction action : req.response) {
				if (action.ruleset_id == ruleset.id)
					return action;
			}
		}

		if (req.response == null)
			req.response = new ArrayList<PromoAction>(2);

		PromoAction action = new PromoAction();
		action.promo_id = ruleset.promo.id;
		action.promo_code = ruleset.promo.code;
		action.ruleset_id = ruleset.id;
		req.response.add(action);
		return action;
	}

	public static PromoAction addAction(PromoRequest<?> req, RuleSet<?> ruleset, Action action) {
		PromoAction p = getOrCreatePromoAction(req, ruleset);
		if (p.actions == null)
			p.actions = new ArrayList<PromoRequest.Action>(2);
		p.actions.add(action);
		return p;
	}

	public static PromoAction addMessage(PromoRequest<?> req, RuleSet<?> ruleset, PromoMessage action) {
		PromoAction p = getOrCreatePromoAction(req, ruleset);
		if (p.messages == null)
			p.messages = new ArrayList<Action>();
		p.messages.add(action);
		return p;
	}
}