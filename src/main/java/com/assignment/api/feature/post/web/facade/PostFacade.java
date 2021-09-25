package com.assignment.api.feature.post.web.facade;

import com.assignment.api.client.jps.JphPostClient;
import com.assignment.api.client.jps.dto.JphCreatePostDataRequest;
import com.assignment.api.client.jps.dto.JphCreatePostDataResponse;
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
public class PostFacade {

    JphPostClient jphPostClient;

    public Mono<JphCreatePostDataResponse> createPost(final JphCreatePostDataRequest request) {
        log.debug("Start calling create post api service with request {}", request);
        final Mono<JphCreatePostDataResponse> response = jphPostClient.createPost(request);
        log.debug("Finish calling create post api service with response {}", response);
        return response;
    }
}
