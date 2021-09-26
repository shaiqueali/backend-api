package com.assignment.api.feature.comment.facade;

import com.assignment.api.client.jps.JphCommentClient;
import com.assignment.api.client.jps.dto.JphCreateCommentDataRequest;
import com.assignment.api.client.jps.dto.JphCreateCommentDataResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CommentFacade {

    JphCommentClient jphCommentClient;

    public Mono<JphCreateCommentDataResponse> createComment(final JphCreateCommentDataRequest request) {
        log.debug("Create comment with request [{}]", request);
        return jphCommentClient.createComment(request);
    }

    public Mono<JphCreateCommentDataResponse> getCommentById(final Integer id) {
        log.debug("Get comment by id [{}]", id);
        return jphCommentClient.getCommentById(id);
    }

    public Flux<JphCreateCommentDataResponse> getCommentByPostId(final Integer postId) {
        log.debug("Get comments by post id [{}]", postId);
        return jphCommentClient.getCommentByPostId(postId);
    }
}
