package com.assignment.api.feature.post.facade;

import com.assignment.api.client.jps.JphPostClient;
import com.assignment.api.client.jps.dto.JphCreatePostDataRequest;
import com.assignment.api.client.jps.dto.JphCreatePostDataResponse;
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
public class PostFacadeTest {

    @Mock
    JphPostClient jphPostClient;

    @InjectMocks
    PostFacade postFacade;

    @Test
    public void testCreatePost() {
        when(jphPostClient.createPost(any(JphCreatePostDataRequest.class))).thenReturn(Mono.just(new JphCreatePostDataResponse()));
        postFacade.createPost(new JphCreatePostDataRequest());
        verify(jphPostClient, atLeastOnce()).createPost(any(JphCreatePostDataRequest.class));
    }
}
