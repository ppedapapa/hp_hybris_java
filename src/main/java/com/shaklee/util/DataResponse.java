package com.shaklee.util;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import com.shaklee.util.StatusResponse;

/**
 * Generic response with data object. Most services need this object for
 * response.
 * 
 * @author Elli Albek
 * 
 * @param <T>
 *            A java bean with return data, or a collection of beans.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class DataResponse<T> extends StatusResponse {

	public T data;

	public DataResponse(int status, String message) {
		super(status, message);
	}

	public DataResponse(int status) {
		super(status);
	}

	public DataResponse(T data) {
		this(SUCCESS);
		this.data = data;
	}

	public static <D> DataResponse<D> success(D data) {
		return new DataResponse<D>(SUCCESS).data(data);
	}

	public static <D> DataResponse<D> success(String message) {
		return new DataResponse<D>(SUCCESS, message);
	}

	@Override
	public DataResponse<T> message(String message) {
		super.message(message);
		return this;
	}

	public DataResponse<T> data(T data) {
		this.data = data;
		return this;
	}
}