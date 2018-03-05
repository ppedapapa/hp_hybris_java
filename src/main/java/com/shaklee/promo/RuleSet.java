package com.shaklee.promo;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.shaklee.common.util.JSONSerializer;

/**
 * A promo has a few rulesets. A ruleset contains the qualification condition;
 * the action taken (some price change) and an optional message. One of action
 * or message is required.
 * 
 * T is the type of data that this promo can act upon.
 * 
 * @author Elli Albek
 */
public class RuleSet<T> {

	public int id; // database ID

	@JsonIgnore
	public Promo<T> promo;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	// @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include =
	// JsonTypeInfo.As.PROPERTY, property = "class")
	public Condition<T> condition;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "class")
	public List<Action<T>> actions;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "class")
	public List<Action<T>> messages;

	@Override
	public boolean equals(Object other) {
		return ((RuleSet<?>) other).id == id;
	}

	@Override
	public String toString() {
		return "RuleSet-" + promo.code
				+ JSONSerializer.toJacksonJaxbJson(this, false);
	}
}