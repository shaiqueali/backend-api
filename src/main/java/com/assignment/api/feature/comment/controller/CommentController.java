package com.assignment.api.feature.comment.controller;


import com.assignment.api.annotation.DefaultApiResponse;
import com.assignment.api.aspect.LogAround;
import com.assignment.api.client.jps.dto.JphCreateCommentDataRequest;
import com.assignment.api.client.jps.dto.JphCreateCommentDataResponse;
import com.assignment.api.feature.comment.facade.CommentFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/comments")
@Api(tags = "Comment", description = "Comment")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CommentController {

    CommentFacade commentFacade;

    @DefaultApiResponse
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @LogAround(message = "Create comment Api")
    @ApiOperation(value = "Create comment Api", nickname = "createComment", notes = "This API is used for creating a comment")
    public ResponseEntity<Mono<JphCreateCommentDataResponse>> createComment(@Valid @RequestBody final JphCreateCommentDataRequest request) {
        return new ResponseEntity<>(commentFacade.createComment(request), HttpStatus.CREATED);
    }

    @DefaultApiResponse
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @LogAround(message = "Get comment by id Api")
    @ApiOperation(value = "Get comment by id Api", nickname = "getCommentById", notes = "This API is used for get a comment by id")
    public ResponseEntity<Mono<JphCreateCommentDataResponse>> getCommentById(@PathVariable("id") final Integer id) {
        return new ResponseEntity<>(commentFacade.getCommentById(id), HttpStatus.OK);
    }

    @DefaultApiResponse
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @LogAround(message = "Get comment by post id Api")
    @ApiOperation(value = "Get comment by post id Api", nickname = "getCommentByPostId", notes = "This API is used for get a comment by post id")
    public ResponseEntity<Flux<JphCreateCommentDataResponse>> getCommentsByPostId(@RequestParam("postId") final Integer postId) {
        return new ResponseEntity<>(commentFacade.getCommentByPostId(postId), HttpStatus.OK);
    }
}
