package com.shaklee.shared.data.event;

import java.sql.Date;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.shaklee.shared.data.user.UserBase;

/**
 * An event from the event stream.
 * 
 * @author Elli Albek
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class StreamEvent {

	public int id, event_type;

	/**
	 * Used internally for fast sorting, do not expose.
	 */
	@JsonIgnore
	public long time;

	public String time_utc;

	public TargetUser target_user;

	// @JsonIgnore
	public String rank_id;
	
	public Date end_date;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	public static class TargetUser extends UserBase.User {
		public UserBase sponsor, bl;
		public String language;
	}
	
	@Override
	public String toString() {
		return id + "/" + event_type;
	}
}
