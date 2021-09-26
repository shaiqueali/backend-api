package com.assignment.api.feature.post.controller;


import com.assignment.api.annotation.DefaultApiResponse;
import com.assignment.api.client.jps.dto.JphCreatePostDataRequest;
import com.assignment.api.client.jps.dto.JphCreatePostDataResponse;
import com.assignment.api.feature.post.facade.PostFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/posts")
@Api(tags = "Post", description = "Post")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PostController {

    PostFacade postFacade;

    @DefaultApiResponse
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create post Api", nickname = "createPost", notes = "This API is used for creating a post")
    public ResponseEntity<Mono<JphCreatePostDataResponse>> createPost(@RequestBody final JphCreatePostDataRequest request) {
        return new ResponseEntity<>(postFacade.createPost(request), HttpStatus.CREATED);
    }
}
