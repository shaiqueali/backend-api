package com.assignment.api.media.web.facade;

import com.assignment.api.media.service.MediaService;
import com.assignment.api.media.web.dto.MediaDownloadResponse;
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

    public Mono<String> uploadBankDocument(final FilePart file) {
        log.debug("Upload document request [{}]", file);
        final Mono<String> uploadedFile = mediaService.uploadMedia(Mono.just(file));
        log.info("Document with id [{}] uploaded", uploadedFile.share().block());
        return uploadedFile;
    }

    public Mono<InputStreamResource> downloadBankDocument(final String documentId) {
        log.debug("Download document with documentId {}", documentId);
        return mediaService.downloadMedia(documentId).map(InputStreamResource::new);
    }

    public Mono<Void> deleteBankDocument(final String documentId) {
        log.debug("Delete document with documentId {}", documentId);
        return mediaService.deleteMedia(documentId);
    }
}
