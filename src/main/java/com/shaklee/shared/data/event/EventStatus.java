package com.shaklee.shared.data.event;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Status flags that can be attached to events.
 * 
 * @author Elli Albek
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class EventStatus {

	public EventStatus() {
	}

	public EventStatus(EventStatus eventStatus) {
		this.type = eventStatus.type;
		this.status = eventStatus.status;
	}

	@NotNull
	public StatusType type;
	@NotNull
	public Boolean status;

	public static enum StatusType {
		READ, HIDE;
	}
}