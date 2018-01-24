package com.shaklee.shared.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import com.shaklee.shared.validation.ShakleeIDValidator.ShakleeID;

/**
 * Constraint on Shaklee ID, enforces pattern. Allows nulls, use @NotNul
 * constraint in addition to enforce required values.
 * 
 * @author Elli Albek
 */
public class ShakleeIDValidator implements
		ConstraintValidator<ShakleeID, String> {

	@Constraint(validatedBy = ShakleeIDValidator.class)
	@Target({ METHOD, FIELD })
	@Retention(RUNTIME)
	public @interface ShakleeID {
		String message() default "Not a valid Shaklee ID";

		Class<?>[] groups() default {};

		Class<? extends Payload>[] payload() default {};
	}

	@Override
	public void initialize(ShakleeID arg0) {
	}

	/**
	 * The validator implementation is not using the REGEX for improved speed.
	 * This implementation does not allocate memory.
	 */
	@Override
	public boolean isValid(String shakleeId, ConstraintValidatorContext ctx) {
		return isValid(shakleeId);
		// return Validators.isValidShakleeIDFormat(shakleeId);
	}

	public static final boolean isValid(String shakleeId) {
		if (shakleeId == null)
			return true;

		final int len = shakleeId.length();
		if (len != 7)
			return false;

		return isUppercaseLetter(shakleeId.charAt(0))
				&& isUppercaseLetter(shakleeId.charAt(1))
				&& isDigit(shakleeId.charAt(2)) && isDigit(shakleeId.charAt(3))
				&& isDigit(shakleeId.charAt(4)) && isDigit(shakleeId.charAt(5))
				&& isDigit(shakleeId.charAt(6));
	}

	private static final boolean isDigit(char c) {
		return c >= '0' && c <= '9';
	}

	private static final boolean isUppercaseLetter(char c) {
		return c >= 'A' && c <= 'Z';
	}
}
