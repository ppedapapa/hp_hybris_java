package com.shaklee.shared.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.samskivert.mustache.Template;
import com.shaklee.shared.dao.mobile.MobileProfileDAO;
import com.shaklee.shared.data.Country2;
import com.shaklee.shared.data.Language;
import com.shaklee.shared.data.event.MobileNotification;
import com.shaklee.shared.data.event.MobileNotification.ACTION;
import com.shaklee.shared.data.event.MobileNotification.MobileAppAction;
import com.shaklee.shared.data.event.StreamEvent;
import com.shaklee.shared.data.user.UserBase;
import com.shaklee.shared.data.user.UserBase.Profile;
import com.shaklee.shared.model.rb.ResoruceBundleCache;
import com.shaklee.shared.util.DataUtils;

/**
 * Format event messages.
 * 
 * @author Elli Albek
 */
@Component
public class MobileEventFormatter {

	private static final Logger logger = Logger.getLogger(MobileEventFormatter.class);

	private final MobileAppAction defaultAction;

	@Autowired
	ResoruceBundleCache cache;

	@Autowired
	MobileProfileDAO profileDAO;

	public MobileEventFormatter() {
		defaultAction = new MobileAppAction();
		defaultAction.type = ACTION.COMMUNICATION;
	}

	/**
	 * All events are formatter based on the locale of the user.
	 */
	public List<MobileNotification> createNotifications(String app, Profile user, final List<? extends StreamEvent> events) {
		final List<MobileNotification> notifications = new ArrayList<MobileNotification>(events.size());
		final Map<String, String> bundle = cache.getBundle(app, user.country, user.language);

		for (StreamEvent event : events) {
			titleCaseName(event.target_user);
			titleCaseName(event.target_user.sponsor);
			titleCaseName(event.target_user.bl);
			MobileNotification notification = new MobileNotification(event);
			notification.message = format("message", user.country, event, user, bundle);
			//String nav = bundle.get("event." + event.event_type + ".navigation");
			// if (nav != null) {
			// notification.action = new MobileAppAction();
			// notification.action.type = ACTION.NAVIGATION;
			// notification.action.target = nav;
			// } else {
			// notification.action = defaultAction;
			// }
			notification.content = bundle.get("event." + event.event_type + ".content");

			notifications.add(notification);
		}
		return notifications;
	}

	public String format(String messageType, Country2 country, StreamEvent evt, UserBase user,
			final Map<String, String> bundle) {
		String rbKey = "event." + evt.event_type + "." + messageType;
		try {
			String txt = bundle.get(rbKey);
			if (txt == null)
				return null;

			if (txt.indexOf("{{") < 0)
				// no template, optimization to exit quickly
				return txt;

			Template template = cache.templateCache.get(txt);

			Map<String, Object> data = new HashMap<String, Object>();
			// titleCaseName(evt.target_user);
			data.put("event", evt);
			// titleCaseName(user);
			data.put("profile", user);
			if (evt.rank_id != null)
				data.put("target_rank", profileDAO.getRank(country, evt.rank_id));

			return template.execute(data);
		} catch (Exception e) {
			logger.error("Error formatting resource '" + rbKey + "' (event " + evt.id + " user " + user.shaklee_id + ")", e);
			return null;
		}
	}

	public String format(String app, String messageType, Country2 country, Language language, StreamEvent evt,
			UserBase user) {
		final Map<String, String> bundle = cache.getBundle(app, country, language);
		return format(messageType, country, evt, user, bundle);
	}

	private static void titleCaseName(UserBase user) {
		if (user == null)
			return;
		user.first_name = DataUtils.titleCase(user.first_name);
		user.last_name = DataUtils.titleCase(user.last_name);
	}
}
