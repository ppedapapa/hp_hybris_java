package com.shaklee.common.util.validation;

import static com.shaklee.common.util.CollectionUtils.add;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.spi.ValidationProvider;

import com.shaklee.shared.validation.InputValidationException;

/**
 * Bean validation using javax.validation API and Apache implementation
 * 
 * @author Elli Albek
 */

public class BeanValidator {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Validator validator = BeanValidator
			.<Configuration> createValidator("org.apache.bval.jsr303.ApacheValidationProvider");

	@SuppressWarnings("unchecked")
	static <T extends Configuration<T>> Validator createValidator(String className) {
		Class<ValidationProvider<T>> providerClass;
		try {
			providerClass = (Class<ValidationProvider<T>>) Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(
					"Validator class not found '" + className + "'. Include implementation jar such as Apache bval", e);
		}
		return Validation.byProvider(providerClass).configure().buildValidatorFactory().getValidator();
	}

	public Set<ConstraintViolation<Object>> validate(Object bean) {
		return validator.validate(bean);
	}

	private Set<ConstraintViolation<Object>> validate(Collection<Object> beans) {
		if (beans.isEmpty())
			return Collections.emptySet();

		Set<ConstraintViolation<Object>> errors = new HashSet<ConstraintViolation<Object>>(2);

		for (Object bean : beans) {
			Set<ConstraintViolation<Object>> e = validator.validate(bean);
			if (e != null && e.isEmpty() == false)
				errors.addAll(e);
		}

		return errors;
	}

	public void assertValid(Object bean) throws InputValidationException {
		Set<ConstraintViolation<Object>> errors = validate(bean);
		if (errors == null || errors.isEmpty())
			return;
		String message = "Data error in " + bean.getClass().getSimpleName();
		throwInputValidationException(message, errors);
	}

	private void assertValid(Collection<Object> beans) throws InputValidationException {
		Set<ConstraintViolation<Object>> errors = validate(beans);
		if (errors == null || errors.isEmpty())
			return;

		String message;
		Iterator<?> it = beans.iterator();
		if (it.hasNext())
			message = "Data error in " + it.next().getClass().getSimpleName();
		else
			message = "Data error in objects array";
		throwInputValidationException(message, errors);
	}

	public static void throwInputValidationException(String message, Set<ConstraintViolation<Object>> errors)
			throws InputValidationException {

		if (errors == null || errors.isEmpty())
			return;

		Map<String, List<String>> mv = new HashMap<String, List<String>>();
		for (ConstraintViolation<Object> error : errors) {
			add(mv, error.getPropertyPath().toString(), error.getMessage());
		}

		throw new InputValidationException(message, mv);
	}

	public static final BeanValidator INSTANCE = new BeanValidator();
}
