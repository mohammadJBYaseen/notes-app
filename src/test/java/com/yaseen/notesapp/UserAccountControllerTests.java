package com.yaseen.notesapp;

import com.yaseen.notesapp.rest.dto.JwtResponse;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.crypto.SecretKey;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserAccountControllerTests {

	@Value(value="${local.server.port}")
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	@Test
	void createUser() {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
		form.set("email", "admin@test.com");
		form.set("passKey", "Admin0123!");
		form.set("firstname", "Admin@123");
		form.set("lastname", "Admin@123");

		HttpStatusCode statusCode = restTemplate.exchange(
				"http://localhost:" + this.port + "/users/create", HttpMethod.POST,
				new HttpEntity<>(form, headers),
				Void.class).getStatusCode();
		assertTrue(statusCode.is2xxSuccessful());
	}

	@SneakyThrows
	@Test
	void doLoginWithCreatedUser() {
		SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512); //or HS384 or HS512
		String base64Key = Encoders.BASE64.encode(key.getEncoded());

		System.out.println(base64Key);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
		form.set("email", "admin2@test.com");
		form.set("passKey", "Admin0123!");
		form.set("firstname", "Admin@123");
		form.set("lastname", "Admin@123");

		HttpStatusCode statusCode = restTemplate.exchange(
				"http://localhost:" + this.port + "/users/create", HttpMethod.POST,
				new HttpEntity<>(form, headers),
				Void.class).getStatusCode();
		assertTrue(statusCode.is2xxSuccessful());

		MultiValueMap<String, String> loginFrom = new LinkedMultiValueMap<>();
		loginFrom.set("username", "admin2@test.com");
		loginFrom.set("secret", "Admin0123!");
		Thread.sleep(1000);
		headers.setAccept(List.of(MediaType.APPLICATION_JSON));
		ResponseEntity<String> exchange = restTemplate.exchange(
				"http://localhost:" + this.port + "/users/authenticate", HttpMethod.POST,
				new HttpEntity<>(loginFrom, headers),
				String.class);
		System.out.println(exchange.getBody());
		assertTrue(exchange.getStatusCode().is2xxSuccessful());
	}

	@Test
	void shouldReturn400WhenUserEmailIsInvalid() {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
		form.set("email", "admin@");
		form.set("passKey", "Admin0123!");
		form.set("firstname", "Admin@123");
		form.set("lastname", "Admin@123");

		HttpStatusCode statusCode = restTemplate.exchange(
				"http://localhost:" + this.port + "/users/create", HttpMethod.POST,
				new HttpEntity<>(form, headers),
				Void.class).getStatusCode();
		assertThat(statusCode.value()).isEqualTo(400);
	}

	@Test
	void shouldReturn400WhenUserPasswordIsWeakInvalid() {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
		form.set("email", "admin@test.com");
		form.set("passKey", "123!");
		form.set("firstname", "Admin@123");
		form.set("lastname", "Admin@123");

		HttpStatusCode statusCode = restTemplate.exchange(
				"http://localhost:" + this.port + "/users/create", HttpMethod.POST,
				new HttpEntity<>(form, headers),
				Void.class).getStatusCode();
		assertThat(statusCode.value()).isEqualTo(400);
	}

}
