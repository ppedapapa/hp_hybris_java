package com.shaklee.rulesets.healthQuestionaire.components;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shaklee.DAO.UserDAO;
import com.shaklee.promo.Condition;
import com.shaklee.promo.PromoRequest;
import com.shaklee.promo.basic.AbstractComponent;
import com.shaklee.rulesets.healthQuestionaire.Questions;

/**
 * A variation of the same component in promo engine. In this case guest is
 * considered as a new users that did not use the promotion.
 * 
 * @author Elli Albek
 */
@Component
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class UserRankSmallerThan extends AbstractComponent<PromoRequest<Questions>>
		implements Condition<PromoRequest<Questions>> {

	@Autowired
	UserDAO userDAO;

	public int rank;

	@Override
	public boolean evaluate(PromoRequest<Questions> questions) {
		if (questions.request.user_id == null)
			// no user, no promo history
			return true;

		if (questions.request.is_guest != null && questions.request.is_guest)
			// no user, no promo history
			return true;

		int actualRank = userDAO.getUserRank(questions.request.user_id);
		return actualRank < rank;
	}
}