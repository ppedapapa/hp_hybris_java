package com.shaklee.rulesets.healthQuestionaire;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.shaklee.promo.AbstractLocalizedData;
import com.shaklee.promo.basic.cart.Cart.RequestPromo;
import com.shaklee.shared.validation.EmailValidator.Email;

/**
 * Diet questions take values 0-3 for 4 values.
 * 
 * @author Elli Albek
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Questions extends AbstractLocalizedData {

	/** iso_639_1 two letter language code **/
	@Size(min = 2, max = 2, message = "Only ISO 639-1 two letter language code")
	public String language;

	@NotNull
	@Min(0)
	public Integer age;

	@NotNull
	public Gender gender;

	public Pregnant pregnant;

	public Boolean is_guest;

	@Size(min = 1, max = 3)
	public List<HealthGoal> health_goals;

	public Integer weight_lbs, height_inches;

	/// lifestyle
	// @Min(0)
	public int energy = -1, stress = -1, sleep = -1, memory = -1, exercise_frequency = -1, exercise_intensity = -1,
			toxins = -1;

	// @Min(0)
	public int spending = -1;

	// diet
	// @Min(0)
	public int fruits, vegetables, grains, dairy, healthy_fats, water, sugar_drinks, junk_food, breakfast, organic;

	public List<DietaryRestriction> dietary_restrictions;

	@Email
	public String email;

	@Size(min = 1)
	public List<RequestPromo> request_promos;

	// public PriceTier price_tier;
	public static enum Gender {
		M, F
	}

	public static enum HealthGoal {
		OVERALL, HEART, JOINT, BONE, AGING, IMMUNE, BRAIN, ENERGY, DIGESTIVE, WEIGHT, SLEEP, STRESS, FITNESS, PERFORMANCE;
	}

	// public static enum Level {
	// LOW, MEDIUM, HIGH;
	// }

	public static enum DietaryRestriction {
		GLUTEN, NUTS, VEGETERIAN, SOY, DAIRY, KOSHER;
	}

	public static enum Pregnant {
		TRYING, PREGNANT, PMS, MENOPAUSE, NO;
	}
}