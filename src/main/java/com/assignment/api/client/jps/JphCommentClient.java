package com.assignment.api.client.jps;


import com.assignment.api.client.jps.dto.JphCreateCommentDataRequest;
import com.assignment.api.client.jps.dto.JphCreateCommentDataResponse;
import com.assignment.api.client.jps.dto.JphCreatePostDataRequest;
import com.assignment.api.config.JphClientConfigProperties;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class JphCommentClient {

    WebClient webClient;

    JphClientConfigProperties jphClientConfigProperties;

    public Mono<JphCreateCommentDataResponse> createComment(final JphCreateCommentDataRequest request) {
        log.info("Calling json placeholder create comment with request [{}]", request);
        final String url = UriComponentsBuilder.fromUriString(jphClientConfigProperties.getCreatePostUri()).toUriString();
        log.debug("json placeholder create comment api is being called with url [{}]", url);
        final Mono<JphCreateCommentDataResponse> response = webClient.post()
                .uri(url)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(request), JphCreatePostDataRequest.class)
                .retrieve()
                .bodyToMono(JphCreateCommentDataResponse.class);
        log.info("Comment created with id [{}]", response);
        return response;
    }

    public Mono<JphCreateCommentDataResponse> getCommentById(final Integer id) {
        log.info("Start calling json placeholder get comment with id [{}]", id);
        final String url = UriComponentsBuilder.fromUriString(jphClientConfigProperties.getGetCommentUri()).buildAndExpand(id).toUriString();
        log.debug("json placeholder get comment api is being called with url [{}]", url);
        final Mono<JphCreateCommentDataResponse> response = webClient.get()
                .uri(url)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(JphCreateCommentDataResponse.class);
        log.info("Comment fetched [{}]", response);
        return response;
    }

    public Flux<JphCreateCommentDataResponse> getCommentByPostId(final Integer postId) {
        log.info("Calling json placeholder get comments by post id [{}] api", postId);
        String url = UriComponentsBuilder
                .fromUriString(jphClientConfigProperties.getGetCommentsByPostUri()).queryParam("postId", postId).toUriString();
        log.debug("json placeholder get comments by post id is being called with url [{}]", url);
        final Flux<JphCreateCommentDataResponse> response = webClient.get()
                .uri(url)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToFlux(JphCreateCommentDataResponse.class);
        log.info("Comments by post id [{}] fetch - response [{}]", postId, response);
        return response;

    }
}
