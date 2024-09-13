package com.techcourse.web.controller;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.apache.coyote.http11.http.request.HttpRequest;
import org.apache.coyote.http11.http.response.HttpResponse;
import org.apache.coyote.http11.http.response.HttpStatusCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RootPageControllerTest {

	@DisplayName("/ 경로에 대한 요청을 처리할 수 있다.")
	@Test
	void isSupport() {
		HttpRequest request = new HttpRequest("GET / HTTP/1.1", null, null);
		Controller controller = RootPageController.getInstance();

		boolean isSupport = controller.isSupport(request);

		assertThat(isSupport).isTrue();
	}

	@DisplayName("POST 요청시 405를 반환한다.")
	@Test
	void doPost() throws Exception {
		HttpRequest request = new HttpRequest("POST / HTTP/1.1", List.of(), null);
		HttpResponse response = new HttpResponse();

		Controller controller = RootPageController.getInstance();


		controller.service(request, response);

		assertThat(response).extracting("startLine")
			.extracting("statusCode")
			.isEqualTo(HttpStatusCode.METHOD_NOT_ALLOWED);
	}

	@DisplayName("GET / 요청을 처리한다.")
	@Test
	void service() throws Exception {
		List<String> headers = List.of("Host: example.com", "Accept: text/html");
		HttpRequest request = new HttpRequest("GET / HTTP/1.1", headers, null);
		HttpResponse response = new HttpResponse();

		Controller controller = RootPageController.getInstance();
		controller.service(request, response);

		assertThat(controller.isSupport(request)).isTrue();
		assertThat(response)
			.extracting("startLine")
			.extracting("statusCode")
			.isEqualTo(HttpStatusCode.OK);
		assertThat(response)
			.extracting("body")
			.extracting("content")
			.isEqualTo("Hello world!".getBytes());
	}
}
