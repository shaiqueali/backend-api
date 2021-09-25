package com.assignment.api.feature.media.web.facade;

import com.assignment.api.feature.media.service.MediaService;
import com.assignment.api.feature.media.web.dto.MediaDownloadResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BankDocumentFacade {

    MediaService mediaService;

    public Flux<MediaDownloadResponse> downloadAllBankDocument() {
        log.debug("Start calling media all downloads service with");
        final Flux<MediaDownloadResponse> downloadedFiles = mediaService.downloadAllMedias();
        log.debug("Finish calling media all downloads service with");
        return downloadedFiles;
    }

    public Mono<String> uploadBankDocument(final Mono<FilePart> file) {
        log.debug("Start calling media upload service with file {}", file);
        final Mono<String> uploadedFile = mediaService.uploadMedia(file);
        log.debug("Finish calling media upload service with file {}", file);
        return uploadedFile;
    }

    public Mono<InputStreamResource> downloadBankDocument(final String documentId) {
        log.debug("Start calling media download service with documentId {}", documentId);
        final Mono<InputStreamResource> downloadedFile = mediaService.downloadMedia(documentId).map(InputStreamResource::new);
        log.debug("Finish calling media download service with documentId {}", documentId);
        return downloadedFile;
    }

    public Mono<Void> deleteBankDocument(final String documentId) {
        log.debug("Start calling media delete service with documentId {}", documentId);
        final Mono<Void> deleteMedia = mediaService.deleteMedia(documentId);
        log.debug("Finish calling media delete service with documentId {}", documentId);
        return deleteMedia;
    }
}
