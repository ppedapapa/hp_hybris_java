package com.shaklee.shared.data;

/**
 * ISO country codes and their mappings to itrack.
 * 
 * Two letter country code.
 * 
 * @author Elli Albek
 */
public enum Country2 {

	us("USA", "us"), ca("CAN", "can"), mx("MEX", "mex");

	public final String country3, mcCountryUrl;

	private Country2(String country3, String mcCountryUrl) {
		this.country3 = country3;
		this.mcCountryUrl = mcCountryUrl;
	}

	/**
	 * A utility method that gets the Country value and handles nulls, unlike
	 * Country.valueOf() which does not handle nulls;
	 */
	public static Country2 getCountry(String country) {
		if (country == null)
			return null;
		return Country2.valueOf(country);
	}

	/**
	 * A utility method that gets the Country by 3 letter country codes. Return
	 * null if not found.
	 */
	public static Country2 getBy3LetterCode(String country3) {

		for (Country2 c : Country2.values()) {
			if (c.country3.equals(country3))
				return c;
		}
		return null;
	}
}