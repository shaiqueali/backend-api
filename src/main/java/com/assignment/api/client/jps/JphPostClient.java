package com.assignment.api.client.jps;


import com.assignment.api.client.jps.dto.JphCreatePostDataRequest;
import com.assignment.api.client.jps.dto.JphCreatePostDataResponse;
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
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class JphPostClient {

    WebClient webClient;

    JphClientConfigProperties jphClientConfigProperties;

    public Mono<JphCreatePostDataResponse> createPost(final JphCreatePostDataRequest request) {
        log.info("Start calling json placeholder create post api with request [{}]", request);
        final String url = UriComponentsBuilder.fromUriString(jphClientConfigProperties.getCreatePostUri()).toUriString();
        log.debug("json placeholder create post api is being called with url [{}]", url);
        final Mono<JphCreatePostDataResponse> response = webClient.post()
                .uri(url)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(request), JphCreatePostDataRequest.class)
                .retrieve()
                .bodyToMono(JphCreatePostDataResponse.class);
        log.info("Finish calling json placeholder create post api with response [{}]", response);
        return response;
    }

}
