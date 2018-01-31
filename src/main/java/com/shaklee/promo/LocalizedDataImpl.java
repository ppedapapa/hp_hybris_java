package com.shaklee.promo;

public class LocalizedDataImpl implements LocalizedData {

	private final String userId, countryCode, rulesetGroup;

	public LocalizedDataImpl(LocalizedData original) {
		this(original.getUserId(), original.getCountryCode(), original.getRulesetGroup());
	}

	public LocalizedDataImpl(String userId, String countryCode, String rulesetGroup) {
		this.userId = userId;
		this.countryCode = countryCode;
		this.rulesetGroup = rulesetGroup;
	}

	//@Override
	public String getUserId() {
		return userId;
	}

	@Override
	public String getCountryCode() {
		return countryCode;
	}

	@Override
	public String getRulesetGroup() {
		return rulesetGroup;
	}
}