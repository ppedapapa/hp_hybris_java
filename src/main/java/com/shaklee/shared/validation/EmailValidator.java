package com.shaklee.shared.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;


public class EmailValidator implements
		ConstraintValidator<EmailValidator.Email, String> {

	@Override
	public void initialize(Email annotation) {
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		if (email == null)
			return true;

		return Validators.EMAIL_REGEX.matcher(email).matches();
	}

	@Documented
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR,
			ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
	@Constraint(validatedBy = EmailValidator.class)
	public @interface Email {

		public String message() default "Invalid email";

		Class<?>[] groups() default {};

		Class<? extends Payload>[] payload() default {};

		public String[] values() default {};
	}
}