package com.shaklee.shared.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.math.BigDecimal;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

public class VolumeValidator implements
		ConstraintValidator<VolumeValidator.Volume, Float> {

	@Constraint(validatedBy = VolumeValidator.class)
	@Target({ METHOD, FIELD })
	@Retention(RUNTIME)
	public @interface Volume {
		String message() default "Not a valid moneraty value (must be positive and up to two decimal places)";

		Class<?>[] groups() default {};

		Class<? extends Payload>[] payload() default {};
	}

	@Override
	public void initialize(Volume arg0) {
	}

	@Override
	public boolean isValid(Float v, ConstraintValidatorContext ctx) {

		// no number, nothing to do.
		if (v == null)
			return true;

		BigDecimal d = BigDecimal.valueOf(v);

		// only two decimal digits
		if (d.scale() > 2)
			return false;

		return true;
	}
}
