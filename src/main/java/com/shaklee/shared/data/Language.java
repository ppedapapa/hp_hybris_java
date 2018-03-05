package com.shaklee.shared.data;

/**
 * Languages supported by member center.
 * 
 * @author Elli Albek
 */
public enum Language {
	en, fr, es,;

	/**
	 * Return the value of the enum or null if the value does not exist.
	 */
	public static Language value(String name) {
		if (name == null)
			return null;

		try {
			return Language.valueOf(name);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
	
	public int itrackCode(){
		return ordinal() + 1;
	}

	public static Language fromItrack(int itrackLanguageCode) {
		switch (itrackLanguageCode) {
		case 2:
			return fr;
		case 3:
			return es;
		default:
			return en;
		}
	}
}