package com.assignment.api.feature.comment.controller;


import com.assignment.api.client.jps.dto.JphCreateCommentDataRequest;
import com.assignment.api.client.jps.dto.JphCreateCommentDataResponse;
import com.assignment.api.client.jps.dto.JphCreatePostDataRequest;
import com.assignment.api.feature.comment.controller.utils.MockUtils;
import com.assignment.api.feature.comment.facade.CommentFacade;
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
@WebFluxTest(value = CommentController.class, excludeAutoConfiguration = {ReactiveSecurityAutoConfiguration.class})
class CommentControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    CommentFacade commentFacade;

    @Test
    @SneakyThrows
    void testCreateComment() {
        final JphCreateCommentDataRequest request = Optional.ofNullable(MockUtils.getResource("mock/create-comment-request.json", JphCreateCommentDataRequest.class)).orElse(new JphCreateCommentDataRequest());
        final JphCreateCommentDataResponse response = Optional.ofNullable(MockUtils.getResource("mock/create-comment-response.json", JphCreateCommentDataResponse.class)).orElse(new JphCreateCommentDataResponse());
        when(commentFacade.createComment(any(JphCreateCommentDataRequest.class))).thenReturn(Mono.just(response));
        webTestClient.post().uri("/v1/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), JphCreatePostDataRequest.class)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody()
                .jsonPath("$.id").isNotEmpty();
    }
}
