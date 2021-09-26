package com.assignment.api.feature.comment.controller;


import com.assignment.api.client.jps.dto.JphCreateCommentDataRequest;
import com.assignment.api.client.jps.dto.JphCreateCommentDataResponse;
import com.assignment.api.client.jps.dto.JphCreatePostDataRequest;
import com.assignment.api.feature.comment.facade.CommentFacade;
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
@WebFluxTest(value = CommentController.class, excludeAutoConfiguration = {ReactiveSecurityAutoConfiguration.class})
class CommentControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    CommentFacade commentFacade;

    @Test
    @SneakyThrows
    public void testCreateComment() {
        final JphCreateCommentDataRequest request = Optional.ofNullable(MockUtils.getResource("mock/create-comment-request.json", JphCreateCommentDataRequest.class)).orElse(new JphCreateCommentDataRequest());
        final JphCreateCommentDataResponse response = Optional.ofNullable(MockUtils.getResource("mock/create-comment-response.json", JphCreateCommentDataResponse.class)).orElse(new JphCreateCommentDataResponse());
        when(commentFacade.createComment(any(JphCreateCommentDataRequest.class))).thenReturn(Mono.just(response));
        webTestClient.post().uri("/v1/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), JphCreatePostDataRequest.class)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody()
                .jsonPath("$.id").isEqualTo(101)
                .jsonPath("$.postId").isEqualTo(1)
                .jsonPath("$.name").isEqualTo("comment")
                .jsonPath("$.email").isEqualTo("email@abc.com")
                .jsonPath("$.body").isEqualTo("body");
    }

    @Test
    @SneakyThrows
    public void testGetCommentById() {
        final Integer commentId = 1;
        final JphCreateCommentDataResponse response = Optional.ofNullable(MockUtils.getResource("mock/create-comment-response.json", JphCreateCommentDataResponse.class)).orElse(new JphCreateCommentDataResponse());
        when(commentFacade.getCommentById(any(Integer.class))).thenReturn(Mono.just(response));
        webTestClient.get().uri("/v1/comment/{id}", commentId)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.id").isNotEmpty();
    }
}
