package org.apache.coyote.http11.http.response;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.coyote.http11.http.BaseHttpHeaders;

public class HttpResponseHeader extends BaseHttpHeaders {

	private static final String SET_COOKIE = "Set-Cookie";
	private static final String JSESSIONID = "JSESSIONID=";

	public HttpResponseHeader() {
		super(new LinkedHashMap<>());
	}

	public void addHeader(String key, String value) {
		addHeader(key, List.of(value));
	}

	public void addHeader(String key, List<String> values) {
		headers.put(key, values);
	}

	public void addJSessionId(String sessionId) {
		addHeader(SET_COOKIE, JSESSIONID + sessionId);
	}

	public String toResponseMessage() {
		List<String> lines = new ArrayList<>();
		headers.forEach((key, value) -> {
			String values = String.join(HEADER_VALUE_DELIMITER, value);
			lines.add(key + HEADER_DELIMITER + values + " ");
		});

		return String.join("\r\n", lines);
	}
}
