package com.shaklee.shared.data.event;

import java.util.Arrays;
import java.util.List;

import com.shaklee.shared.data.Country2;

public class EventStreamManagerSubscription {

	public static final List<Character> HISPANIC = Arrays.asList('H', 'X', 'Y', 'Z');

	public String shakleeId;
	public Country2 country;
	public String state;
	public Integer language;
	public Character ethnicity;

	public void setShakleeId(String shakleeId) {
		this.shakleeId = shakleeId;
	}

	public void setCountry(String country) {
		this.country = Country2.getCountry(country);
	}

	public void setLanguage(Integer language) {
		this.language = language;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setEthnicity(Character ethnicity) {
		this.ethnicity = ethnicity;
	}

	@Override
	public String toString() {
		// String isHispacic = hispanic ? "hispanic" : "-";
		return shakleeId + '(' + country + ',' + state + ',' + language + ',' + ethnicity + ')' + '\n';
	}
}
