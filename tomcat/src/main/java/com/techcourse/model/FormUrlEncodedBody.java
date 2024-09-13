package com.techcourse.model;

import java.util.Map;

public class FormUrlEncodedBody {

	private final Map<String, String> body;

	public FormUrlEncodedBody(Map<String, String> body) {
		this.body = body;
	}

	public String getValue(String key) {
		if (body.containsKey(key)) {
			return body.get(key);
		}

		throw new IllegalArgumentException("Key not found");
	}
}
