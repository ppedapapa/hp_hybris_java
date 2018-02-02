package com.shaklee.shared.data.user;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * User generated resources such as profile photos.
 * 
 * @author Elli Albek
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class UserResource {

	public static enum STORAGE {
		FB, AEM;

		public static STORAGE value(String name) {
			if (name == null)
				return null;

			try {
				return STORAGE.valueOf(name);
			} catch (IllegalArgumentException e) {
				return null;
			}
		}
	};

	public static enum RESOURCE_TYPE {
		PHOTO;

		public static RESOURCE_TYPE value(String name) {
			if (name == null)
				return null;

			try {
				return RESOURCE_TYPE.valueOf(name);
			} catch (IllegalArgumentException e) {
				return null;
			}
		}
	};

	public String resource_base;
	public STORAGE storage;

	public static UserResource create(String resourceBase, String storage) {
		if (resourceBase == null)
			return null;

		UserResource r = new UserResource();
		r.resource_base = resourceBase;
		r.storage = STORAGE.valueOf(storage);
		return r;
	}

	public String getResource_base() {
		return resource_base;
	}

	public void setResource_base(String resource_base) {
		this.resource_base = resource_base;
	}

	public STORAGE getStorage() {
		return storage;
	}

	public void setStorage(STORAGE storage) {
		this.storage = storage;
	}
}
