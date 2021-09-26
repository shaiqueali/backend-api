package com.assignment.api.media.service;

import com.assignment.api.media.web.dto.MediaDownloadResponse;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.InputStream;

public interface MediaService {

    Mono<Void> deleteMedia(final String documentId);

    Flux<MediaDownloadResponse> downloadAllMedias();

    Mono<String> uploadMedia(final Mono<FilePart> file);

    Mono<InputStream> downloadMedia(final String documentId);

}
