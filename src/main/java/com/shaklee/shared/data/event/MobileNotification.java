package com.shaklee.shared.data.event;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonRawValue;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * An extension to events that include mobile app specific data.
 * 
 * @author Elli Albek
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class MobileNotification extends StreamEvent {

	public String message;

	@JsonIgnore
	public MobileAppAction action;

	@JsonRawValue
	public String content;

	public List<EventStatus> status;

	public MobileNotification() {
	}

	public MobileNotification(StreamEvent event) {
		id = event.id;
		event_type = event.event_type;
		target_user = event.target_user;
		time = event.time;
		time_utc = event.time_utc;
		rank_id = event.rank_id;
		end_date = event.end_date;
	}

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	public static class MobileAppAction {
		public ACTION type;
		public String target;
	}

	public static enum ACTION {
		NAVIGATION, COMMUNICATION;
	}
}
