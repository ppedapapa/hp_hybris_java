package com.shaklee.rulesets.healthQuestionaire.components;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.shaklee.promo.Condition;
import com.shaklee.promo.LocalizedData;
import com.shaklee.promo.PromoRequest;
import com.shaklee.promo.basic.AbstractComponent;
import com.shaklee.shared.validation.InputValidationException;
import com.shaklee.shared.validation.OneOfValidator.OneOf;

@Component
public class Country extends AbstractComponent<LocalizedData> implements Condition<PromoRequest<LocalizedData>> {

	@NotNull
	@OneOf(values = { "US", "CA" }, message = "Only US and CA")
	public String country_code;

	@Override
	public boolean evaluate(PromoRequest<LocalizedData> r) throws InputValidationException {
		return country_code.equals(r.request.getCountryCode());
	}
}
