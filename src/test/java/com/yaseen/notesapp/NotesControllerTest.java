package com.yaseen.notesapp;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NotesControllerTest {

    @Value(value="${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void getNotesForNotLoggedInUserShouldReturn401() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpStatusCode statusCode = restTemplate.exchange(
                "http://localhost:" + this.port + "/notes", HttpMethod.GET,
                new HttpEntity<>(headers),
                Void.class).getStatusCode();
        assertThat(statusCode.value()).isEqualTo(401);
    }

   //TODO add test cases to cover create notes for logged in user.
   //TODO add test cases to cover search for notes from logged user


}
