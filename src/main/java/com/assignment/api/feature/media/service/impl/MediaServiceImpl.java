package com.assignment.api.feature.media.service.impl;

import com.assignment.api.feature.media.service.MediaService;
import com.assignment.api.feature.media.web.dto.MediaDownloadResponse;
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


@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MediaServiceImpl implements MediaService {

    ReactiveGridFsTemplate reactiveGridFsTemplate;

    @Override
    public Flux<MediaDownloadResponse> downloadAllMedias() {
        log.debug("Start calling download all medias from mongodb");
        return reactiveGridFsTemplate.find(new Query()).map(gridFSFile -> {
            final MediaDownloadResponse response = new MediaDownloadResponse();
            response.setDocumentId(gridFSFile.getObjectId().toHexString());
            response.setDocumentName(gridFSFile.getFilename());
            return response;
        });
    }

    @Override
    public Mono<Void> deleteMedia(final String objectId) {
        log.debug("Start deleting media from mongodb with objectId [{}]", objectId);
        return reactiveGridFsTemplate.delete(new Query(Criteria.where("_id").is(objectId)));
    }

    @Override
    public Mono<String> uploadMedia(final Mono<FilePart> file) {
        log.debug("Start calling upload media into mongodb [{}]", file);
        return file.flatMap(filePart -> reactiveGridFsTemplate.store(filePart.content(), filePart.filename()).map(ObjectId::toHexString));
    }

    @Override
    public Mono<InputStream> downloadMedia(final String objectId) {
        log.debug("Start calling download media from mongodb [{}]", objectId);
        return reactiveGridFsTemplate.findOne(new Query(Criteria.where("_id").is(objectId))).map(reactiveGridFsTemplate::getResource)
                .flatMap(reactiveGridFsResource -> reactiveGridFsResource.flatMap(ReactiveGridFsResource::getInputStream));
    }
}
