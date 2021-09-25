package com.assignment.api.feature.comment.facade;

import com.assignment.api.client.jps.JphCommentClient;
import com.assignment.api.client.jps.dto.JphCreateCommentDataRequest;
import com.assignment.api.client.jps.dto.JphCreateCommentDataResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CommentFacade {

    JphCommentClient jphCommentClient;

    public Mono<JphCreateCommentDataResponse> createComment(final JphCreateCommentDataRequest request) {
        log.debug("Start calling create comment api service with request {}", request);
        final Mono<JphCreateCommentDataResponse> response = jphCommentClient.createComment(request);
        log.debug("Finish calling create comment api service with response {}", response);
        return response;
    }
}
