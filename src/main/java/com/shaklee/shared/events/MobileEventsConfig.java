package com.shaklee.shared.events;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import com.shaklee.common.util.StringUtils;
import com.shaklee.itrack.common.util.PropertiesObject;

/**
 * Configuration of mobile events.
 * 
 * @author Elli Albek
 */
public class MobileEventsConfig {

	// event recipients
	public final String mobileUserEvents, mobileDownlineEvents, mobileBLEvents, mobilePwsHpEvents, userEvents,
			originalSponsorEvents, sponsorEvents;

	// internal custom events
	public final String mobileSalesExecEvents, mobileRegionalManagerEvents;

	// destinations
	public final String mobilePushEvents, mobilePullEvents, dboEvents, emailUserEvents;

	public MobileEventsConfig() {
		PropertiesObject prop = new PropertiesObject(MobileEventsConfig.class);

		userEvents = prop.getProperty("event.user");
		sponsorEvents = prop.getProperty("event.sponsor", "");
		originalSponsorEvents = prop.getProperty("event.originalSponsor", "");

		mobileUserEvents = userEvents; // prop.getProperty("event.mobile.user");
		mobileDownlineEvents = prop.getProperty("event.downline");
		// BL should also see the normal downline events.
		mobileBLEvents = concat(mobileDownlineEvents, prop.getProperty("event.bl"));
		mobilePushEvents = prop.getProperty("event.mobile.push", "");
		mobileRegionalManagerEvents = prop.getProperty("event.regionalManager", "");
		mobileSalesExecEvents = prop.getProperty("event.salesExec", "");
		emailUserEvents = prop.getProperty("event.email.user", "");
		mobilePullEvents = prop.getProperty("event.mobile.pull");

		// DBO Push
		dboEvents = prop.getProperty("event.dbo", "");

		mobilePwsHpEvents = prop.getProperty("event.mobile.mobilePwsHpEvents", "-1");
	}

	public static String concat(String s1, String s2) {
		s1 = StringUtils.clean(s1);
		s2 = StringUtils.clean(s2);
		if (s1 == null)
			return s2;
		if (s2 == null)
			return s1;

		Set<String> list = new LinkedHashSet<String>(Arrays.asList(s1.split("[,\\s]+")));
		list.addAll(Arrays.asList(s2.split("[,\\s]+")));
		return org.apache.commons.lang3.StringUtils.join(list, ',');
	}

	/**
	 * Subtract s2 from s1
	 */
	public static String intersectLists(String s1, String s2) {
		Set<String> list = new LinkedHashSet<String>(Arrays.asList(s1.split("[,\\s]+")));
		list.retainAll(new HashSet<String>(Arrays.asList(s2.split("[,\\s]+"))));
		return org.apache.commons.lang3.StringUtils.join(list, ',');
	}
}
