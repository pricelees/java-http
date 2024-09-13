package com.techcourse.web.util;

import static org.assertj.core.api.Assertions.*;

import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.techcourse.model.FormUrlEncodedBody;

class FormUrlEncodedParserTest {

	@DisplayName("Form Url Encoded 문자열을 파싱한다.")
	@Test
	void parse() {
		String body = "name=abc&password=1234";

		FormUrlEncodedBody result = FormUrlEncodedParser.parse(body);

		assertThat(result.getValue("name")).isEqualTo("abc");
		assertThat(result.getValue("password")).isEqualTo("1234");
	}

	@DisplayName("입력된 문자열이 없는 경우 예외를 던진다.")
	@Test
	void parse_WithEmptyInput() {
		String body = "";

		assertThatThrownBy(() -> FormUrlEncodedParser.parse(body))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("Body is empty");
	}

	@DisplayName("Key만 존재하면 빈 문자열로 Value를 반환한다.")
	@Test
	void parse_WithEmptyValue() {
		String body = "name=&password=1234";

		FormUrlEncodedBody result = FormUrlEncodedParser.parse(body);

		assertThat(result.getValue("name")).isEqualTo("");
		assertThat(result.getValue("password")).isEqualTo("1234");
	}
}
