package com.assignment.api.feature.comment.facade;

import com.assignment.api.client.jps.JphCommentClient;
import com.assignment.api.client.jps.dto.JphCreateCommentDataRequest;
import com.assignment.api.client.jps.dto.JphCreateCommentDataResponse;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class CommentFacadeTest {

    @Mock
    JphCommentClient jphCommentClient;

    @InjectMocks
    CommentFacade commentFacade;

    @Test
    public void testCreateComment() {
        when(jphCommentClient.createComment(any(JphCreateCommentDataRequest.class))).thenReturn(Mono.just(new JphCreateCommentDataResponse()));
        commentFacade.createComment(new JphCreateCommentDataRequest());
        verify(jphCommentClient, atLeastOnce()).createComment(any(JphCreateCommentDataRequest.class));
    }
}
