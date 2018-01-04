package com.shaklee.promo;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


public abstract class AbstractLocalizedData implements LocalizedData {

	/** can be null during sponsor flow **/
	public String user_id;

	@NotNull
	public String country_code;

	// internal
	@JsonIgnoreProperties
	public String rulesetGroup;

	@Override
	@JsonIgnoreProperties
	public String getUserId() {
		return user_id;
	}

	@Override
	@JsonIgnoreProperties
	public String getCountryCode() {
		return country_code;
	}

	@Override
	public String getRulesetGroup() {
		return rulesetGroup;
	}

}
