package com.assignment.api.feature.post.controller;

import com.assignment.api.client.jps.dto.JphCreatePostDataRequest;
import com.assignment.api.client.jps.dto.JphCreatePostDataResponse;
import com.assignment.api.feature.post.facade.PostFacade;
import com.assignment.api.feature.utils.MockUtils;
import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@FieldDefaults(level = AccessLevel.PRIVATE)
@WebFluxTest(value = PostController.class, excludeAutoConfiguration = {ReactiveSecurityAutoConfiguration.class})
public class PostControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    PostFacade postFacade;

    @Test
    @SneakyThrows
    public void testCreatePost() {
        final JphCreatePostDataRequest request = Optional.ofNullable(MockUtils.getResource("mock/create-post-request.json", JphCreatePostDataRequest.class)).orElse(new JphCreatePostDataRequest());
        final JphCreatePostDataResponse response = Optional.ofNullable(MockUtils.getResource("mock/create-post-response.json", JphCreatePostDataResponse.class)).orElse(new JphCreatePostDataResponse());
        when(postFacade.createPost(any(JphCreatePostDataRequest.class))).thenReturn(Mono.just(response));
        webTestClient.post().uri("/v1/post")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), JphCreatePostDataRequest.class)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody()
                .jsonPath("$.id").isEqualTo(101)
                .jsonPath("$.documentId").isEqualTo("1")
                .jsonPath("$.body").isEqualTo("body");

    }

}
