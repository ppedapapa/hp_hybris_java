package com.shaklee.entity;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Base64;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shaklee.common.util.JSONSerializer;

@Component
@Scope(value="session")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class HPEntity {
	private String country;
	private String lang;
	private boolean userLogged;
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public boolean isUserLogged() {
		return userLogged;
	}
	public void setUserLogged(boolean userLogged) {
		this.userLogged = userLogged;
	}
	
	@Override
	public String toString() {
		String json = JSONSerializer.toJacksonJaxbJson(this, false, true);
		
		byte[] encodedBytes = Base64.getEncoder().encode(json.getBytes());
		
		return new String(encodedBytes);
		
	}

	public JSONObject toJSON() {
		try {
			return new JSONObject(toString());
		} catch (JSONException e) {
			throw new RuntimeException(e);
		}
	}
}
