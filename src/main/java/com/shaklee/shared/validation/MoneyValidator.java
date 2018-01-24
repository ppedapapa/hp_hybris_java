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

public class MoneyValidator implements
		ConstraintValidator<MoneyValidator.Money, BigDecimal> {

	@Constraint(validatedBy = MoneyValidator.class)
	@Target({ METHOD, FIELD })
	@Retention(RUNTIME)
	public @interface Money {
		String message() default "Not a valid moneraty value (must be positive and up to two decimal places)";

		Class<?>[] groups() default {};

		Class<? extends Payload>[] payload() default {};
	}

	@Override
	public void initialize(Money arg0) {
	}

	@Override
	public boolean isValid(BigDecimal m, ConstraintValidatorContext ctx) {

		// no number, nothing to do.
		if (m == null)
			return true;

		// must be positive
		if (m.signum() < 0)
			return false;

		// only two decimal digits
		if (m.scale() > 2)
			return false;

		return true;
	}
}
