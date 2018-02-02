package com.shaklee.promo;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.shaklee.shared.validation.OneOfValidator.OneOf;
import com.shaklee.shared.validation.ShakleeIDValidator.ShakleeID;

public abstract class AbstractLocalizedData implements LocalizedData {

	/** can be null during sponsor flow **/
	@ShakleeID
	public String user_id;

	@NotNull
	@OneOf(values = { "US", "CA" }, message = "Only US and CA")
	public String country_code;

	// internal
	@JsonIgnore
	public String rulesetGroup;

	@Override
	@JsonIgnore
	public String getUserId() {
		return user_id;
	}

	@Override
	@JsonIgnore
	public String getCountryCode() {
		return country_code;
	}

	@Override
	public String getRulesetGroup() {
		return rulesetGroup;
	}

}
