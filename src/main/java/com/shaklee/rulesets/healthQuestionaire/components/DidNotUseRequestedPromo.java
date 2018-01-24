package com.shaklee.rulesets.healthQuestionaire.components;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shaklee.promo.Condition;
import com.shaklee.promo.PromoRequest;
import com.shaklee.promo.basic.AbstractComponent;
import com.shaklee.promo.basic.cart.Cart.RequestPromo;
import com.shaklee.promo.impl.PromoTrackingDAO;
import com.shaklee.rulesets.healthQuestionaire.Questions;

/**
 * Checks if user did not use the requested promo codes by shaklee id. If the
 * shaklee id is not available, it uses the email address to check if user did
 * not use the requested promo codes.
 * 
 * If the promo codes are not available in the request, return false
 * 
 * @author Emre Koca
 */
@Component
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class DidNotUseRequestedPromo extends AbstractComponent<PromoRequest<Questions>>
		implements Condition<PromoRequest<Questions>> {

	@Autowired
	PromoTrackingDAO trackingDAO;

	@Override
	public boolean evaluate(PromoRequest<Questions> questions) {
		if (questions.request.request_promos == null)
			return false;
		List<String> promoCodes = getRequestPromoCodes(questions.request.request_promos);
		if (questions.request.user_id != null)
			return countTrackingByShakleeID(promoCodes, questions.request.user_id);
		if (questions.request.email != null)
			return countTrackingByEmail(promoCodes, questions.request.email);
		// no user id and no email so no promo history
		return false;
	}

	public boolean countTrackingByShakleeID(List<String> promoCodes, final String shaklee_id) {
		return trackingDAO.userUsedPromoCodes(shaklee_id, promoCodes.toArray(new String[promoCodes.size()])) == false;
	}

	public boolean countTrackingByEmail(List<String> promoCodes, final String email) {
		return trackingDAO.guestUsedPromoCodes(email, promoCodes.toArray(new String[promoCodes.size()])) == false;
	}

	private List<String> getRequestPromoCodes(List<RequestPromo> requestPromos) {
		List<String> promoCodes = new ArrayList<String>(requestPromos.size());
		for (RequestPromo promo : requestPromos) {
			promoCodes.add(promo.promo_code);
		}
		return promoCodes;
	}
}