package com.shaklee.shared.data.user;

/**
 * Enum for supported compensation plans.
 * 
 * @author Elli Albek
 */
public enum CompPlan {
	LEGACY, DISTRIBUTOR;

	/**
	 * Return the value of the enum or null if the value does not exist.
	 */
	public static CompPlan value(String name) {
		if (name == null)
			return null;

		try {
			return CompPlan.valueOf(name);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
}
