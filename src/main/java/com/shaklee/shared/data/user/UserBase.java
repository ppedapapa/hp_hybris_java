package com.shaklee.shared.data.user;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.shaklee.shared.data.Country2;
import com.shaklee.shared.data.Language;

/**
 * Common shaklee user object.
 * 
 * @author Elli Albek
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class UserBase {

	public String first_name, last_name, shaklee_id;
	public UserResource profile_photo;
	public String email;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	public static class Profile extends User {

		@JsonIgnore
		public int rank_id;
		public String rank_code;
		public Boolean is_bl;
		public Country2 country;
		public Language language;
		public boolean is_gold_ambassador = false;

		public String pws_domain;
		public String pws_url;
		public CompPlan comp_plan;
		@JsonIgnore
		public String legacy_grandfathered_rank;
		public boolean is_grandfathered_rank;
		public User business_leader;
	}

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	public static class User extends UserBase {
		public List<Phone> phones;
	}
}