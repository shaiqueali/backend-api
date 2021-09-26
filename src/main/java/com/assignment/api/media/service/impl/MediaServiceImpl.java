package com.assignment.api.media.service.impl;

import com.assignment.api.exception.BusinessServiceException;
import com.assignment.api.media.service.MediaService;
import com.assignment.api.media.web.dto.MediaDownloadResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.ReactiveGridFsResource;
import org.springframework.data.mongodb.gridfs.ReactiveGridFsTemplate;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.InputStream;

import static com.assignment.api.exception.dto.ErrorCodeEnum.*;


@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MediaServiceImpl implements MediaService {

    ReactiveGridFsTemplate reactiveGridFsTemplate;

    @Override
    public Flux<MediaDownloadResponse> downloadAllMedias() {
        log.debug("Download all medias from mongodb");
        return reactiveGridFsTemplate.find(new Query()).map(gridFSFile -> {
            final MediaDownloadResponse response = new MediaDownloadResponse();
            response.setDocumentId(gridFSFile.getObjectId().toHexString());
            response.setDocumentName(gridFSFile.getFilename());
            return response;
        });
    }

    @Override
    public Mono<Void> deleteMedia(final String documentId) {
        log.debug("Delete media from mongodb with documentId [{}]", documentId);
        return reactiveGridFsTemplate.delete(new Query(Criteria.where("_id").is(documentId)));
    }

    @Override
    public Mono<String> uploadMedia(final Mono<FilePart> file) {
        log.debug("Store media into mongodb [{}]", file);
        return file.flatMap(filePart -> reactiveGridFsTemplate.store(filePart.content(), filePart.filename()).map(ObjectId::toHexString));
    }

//    @Override
//    public Mono<InputStream> downloadMedia(final String objectId) {
//        log.debug("Start calling download media from mongodb [{}]", objectId);
//        return reactiveGridFsTemplate.findOne(new Query(Criteria.where("_id").is(objectId))).map(reactiveGridFsTemplate::getResource)
//                .flatMap(reactiveGridFsResource -> reactiveGridFsResource.flatMap(ReactiveGridFsResource::getInputStream));
//    }

    @Override
    public Mono<InputStream> downloadMedia(final String documentId) {
        log.debug("Download media from mongodb with documentId [{}]", documentId);
        return reactiveGridFsTemplate.findOne(new Query(Criteria.where("_id").is(documentId))).map(reactiveGridFsTemplate::getResource)
                .flatMap(reactiveGridFsResource -> reactiveGridFsResource.flatMap(ReactiveGridFsResource::getInputStream))
                .switchIfEmpty(Mono.error(new BusinessServiceException(FILE_NOT_FOUND, "file not found in the system")));
    }
}
