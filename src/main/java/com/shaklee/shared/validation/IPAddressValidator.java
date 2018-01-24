package com.shaklee.shared.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import com.shaklee.shared.validation.IPAddressValidator.IPAddress;


/**
 * Constraint on Remote address, enforces IP4 or IP6 pattern.
 * Allows nulls, use @NotNull constraint in addition to enforce required values.
 * 
 * @author Emre Koca
 */
public class IPAddressValidator implements ConstraintValidator<IPAddress, String> {

	@Constraint(validatedBy = IPAddressValidator.class)
	@Target({ METHOD, FIELD })
	@Retention(RUNTIME)
	public @interface IPAddress {
		String message() default "Not a valid IP Address";

		Class<?>[] groups() default {};

		Class<? extends Payload>[] payload() default {};
	}

	private static final String ipv4Pattern = "(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])";
	private static final String ipv6Pattern = "([0-9a-f]{1,4}:){7}([0-9a-f]){1,4}";
	private static final Pattern VALID_IPV4_PATTERN = Pattern.compile(ipv4Pattern, Pattern.CASE_INSENSITIVE);
	private static final Pattern VALID_IPV6_PATTERN = Pattern.compile(ipv6Pattern, Pattern.CASE_INSENSITIVE);

	@Override
	public void initialize(IPAddress arg0) {
	}

	@Override
	public boolean isValid(String IPAddress, ConstraintValidatorContext ctx) {
		return isValid(IPAddress);
	}

	public static final boolean isValid(String IPAddress) {
		// Allows nulls, use @NotNull constraint in addition to enforce required values.
		if (IPAddress == null)
			return true;

		return isIpAddress(IPAddress);
	}

	public static boolean isIpAddress(String IPAddress) {
		// Check if it is IPV4 address
		if (VALID_IPV4_PATTERN.matcher(IPAddress).matches()) {
			return true;
		}
		// Check if it is IPV6 address
		Matcher m2 = VALID_IPV6_PATTERN.matcher(IPAddress);
		return m2.matches();
	}
}
