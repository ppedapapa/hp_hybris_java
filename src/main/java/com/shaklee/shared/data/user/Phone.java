package com.shaklee.shared.data.user;

import static com.shaklee.common.util.StringUtils.startsWithIgnoreCase;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.shaklee.common.util.StringUtils;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Phone {

	// private static final Pattern NON_NUMBERS = Pattern.compile("[^0-9.]");
	private String number;

	private PHONE_TYPE phone_type;
	public Boolean sms_enabled;

	public static enum PHONE_TYPE {
		HOME, MOBILE, WORK;

		public static PHONE_TYPE fromLegacy(String type) {
			type = StringUtils.clean(type);
			if (type != null) {
				if (startsWithIgnoreCase(type, "home")) {
					return PHONE_TYPE.HOME;
				} else if (startsWithIgnoreCase(type, "cell")) {
					return PHONE_TYPE.MOBILE;
				} else if (startsWithIgnoreCase(type, "MOBILE")) {
					return PHONE_TYPE.MOBILE;
				} else if (startsWithIgnoreCase(type, "work")) {
					return PHONE_TYPE.WORK;
				}
			}
			return null;
		}
	}

	public PHONE_TYPE getPhone_type() {
		return phone_type;
	}

	public void setPhone_type(PHONE_TYPE phone_type) {
		this.phone_type = phone_type;
		// if (phone_type == null)
		// sms_enabled = null;
		// else
		sms_enabled = phone_type == PHONE_TYPE.MOBILE;
	}

	public void setNumber(String number) {
		this.number = cleanPhoneNumber(number);
	}

	public String getNumber() {
		return number;
	}

	public static String cleanPhoneNumber(String s) {
		// original regex implementation is little slow
		// return NON_NUMBERS.matcher(s).replaceAll("");

		final int len = s.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			final char num = s.charAt(i);
			if (num >= '0' && num <= '9')
				sb.append(num);
		}
		return sb.toString();
	}

	public static Phone createLegacyContact(String number, String type) {
		number = StringUtils.clean(number);
		if (number == null)
			return null;

		Phone phone = new Phone();
		phone.setNumber(number);
		phone.setPhone_type(PHONE_TYPE.fromLegacy(type));
		return phone;
	}

	public static Phone createPhone(String number, PHONE_TYPE type) {
		if (number == null)
			return null;

		Phone phone = new Phone();
		phone.setNumber(number);
		phone.setPhone_type(type);
		return phone;
	}

	public static Phone createPhone(String number, String type) {
		if (number == null)
			return null;

		Phone phone = new Phone();
		phone.setNumber(number);
		if (type != null)
			phone.setPhone_type(PHONE_TYPE.valueOf(type));

		return phone;
	}
}