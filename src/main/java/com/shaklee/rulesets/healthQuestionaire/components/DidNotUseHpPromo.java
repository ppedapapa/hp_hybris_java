package com.shaklee.rulesets.healthQuestionaire.components;

import java.util.Collection;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shaklee.promo.Condition;
import com.shaklee.promo.PromoRequest;
import com.shaklee.promo.basic.AbstractComponent;
import com.shaklee.promo.impl.PromoTrackingDAO;
import com.shaklee.rulesets.healthQuestionaire.Questions;

/**
 * A variation of the same component in promo engine. In this case guest is
 * considered as a new users that did not use the promotion.
 * 
 * @author Elli Albek
 */
@Component
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class DidNotUseHpPromo extends AbstractComponent<PromoRequest<Questions>>
		implements Condition<PromoRequest<Questions>> {

	@Autowired
	PromoTrackingDAO trackingDAO;

	public Collection<Integer> promo_id;

	@Override
	public boolean evaluate(PromoRequest<Questions> questions) {
		if (questions.request.user_id == null)
			// no user, no promo history
			return true;

		if (questions.request.is_guest != null && questions.request.is_guest)
			// no user, no promo history
			return true;

		if (promo_id == null)
			// default to the code of the promo that contains this rule
			return trackingDAO.userUsedPromo(questions.request.user_id, ruleset.promo.id) == false;

		Integer[] promos = promo_id.toArray(new Integer[promo_id.size()]);
		return trackingDAO.userUsedPromo(questions.request.user_id, promos) == false;
	}
}