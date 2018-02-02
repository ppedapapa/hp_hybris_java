package com.shaklee.shared.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Collection;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import com.shaklee.common.util.CollectionUtils;

public class OneOfValidator implements ConstraintValidator<OneOfValidator.OneOf, String> {
	/**
	 * String array of possible enum values
	 */
	private Collection<String> values;

	@Override
	public boolean isValid(String s, ConstraintValidatorContext arg1) {
		return values.contains(s);
	}
	
	@Override
	public void initialize(final OneOf constraintAnnotation) {
		this.values = CollectionUtils.fastSearchCollection(constraintAnnotation.values());
	}


	@Target(value = { ElementType.METHOD, ElementType.FIELD,
			ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR,
			ElementType.PARAMETER })
	@Retention(RetentionPolicy.RUNTIME)
	@Constraint(validatedBy = { OneOfValidator.class })
	@Documented
	public @interface OneOf {
		public String message() default "Value not allowed";

		Class<?>[] groups() default {};

		Class<? extends Payload>[] payload() default {};

		public String[] values() default {};
	}
}
