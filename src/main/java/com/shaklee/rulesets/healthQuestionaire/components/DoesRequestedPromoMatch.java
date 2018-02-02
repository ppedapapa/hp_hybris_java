package com.shaklee.rulesets.healthQuestionaire.components;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.stereotype.Component;

import com.shaklee.promo.Condition;
import com.shaklee.promo.PromoRequest;
import com.shaklee.promo.basic.AbstractComponent;
import com.shaklee.promo.basic.cart.Cart.RequestPromo;
import com.shaklee.rulesets.healthQuestionaire.Questions;

/**
 * Checks if the requested promo code match with expected one of the promo codes
 * 
 * @author Emre Koca
 */
@Component
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class DoesRequestedPromoMatch extends AbstractComponent<PromoRequest<Questions>>
		implements Condition<PromoRequest<Questions>> {

	@NotNull
	public List<String> promo_code;

	@Override
	public boolean evaluate(PromoRequest<Questions> questions) {
		if (questions.request.request_promos == null)
			return false;

		for (RequestPromo promo : questions.request.request_promos) {
			if (promo_code.contains(promo.promo_code))
				return true;
		}

		return false;
	}
}