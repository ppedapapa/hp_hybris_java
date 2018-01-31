package com.shaklee.healthPrint.data;

import java.util.Arrays;
import java.util.List;

/**
 * The price tier. This class is improperly named "bundle" due to historical
 * reasons.
 * 
 * @author Elli Albek
 */
public enum Bundle {
	TIER_1, TIER_2, TIER_3, CONSIDER;

	public static List<Bundle> ALL_TIERS = Arrays.asList(TIER_1, TIER_2, TIER_3);
}