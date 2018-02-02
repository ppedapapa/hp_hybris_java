package com.shaklee.rulesets.healthQuestionaire.components;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.shaklee.promo.Condition;
import com.shaklee.promo.PromoRequest;
import com.shaklee.promo.basic.AbstractComponent;
import com.shaklee.promo.basic.cart.Cart;
import com.shaklee.rulesets.healthQuestionaire.Questions;
import com.shaklee.shared.validation.InputValidationException;

@Component
public class PriceTier extends AbstractComponent<Questions>implements Condition<PromoRequest<Questions>> {

	@NotNull
	@Size(min = 1)
	public List<Cart.PriceTier> price_tier;

	@Override
	public boolean evaluate(PromoRequest<Questions> q) throws InputValidationException {
		return false;
		
		//Cart.PriceTier user_tier = q.request.price_tier;
		//return (user_tier != null && price_tier.contains(user_tier));
	}
}