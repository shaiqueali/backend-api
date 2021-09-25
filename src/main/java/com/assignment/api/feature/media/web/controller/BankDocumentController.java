package com.assignment.api.feature.media.web.controller;


import com.assignment.api.annotation.DefaultApiResponse;
import com.assignment.api.feature.media.web.dto.MediaDownloadResponse;
import com.assignment.api.feature.media.web.facade.BankDocumentFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/documents")
@Api(tags = "Document", description = "Documents")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BankDocumentController {

    BankDocumentFacade bankDocumentFacade;

    @DefaultApiResponse
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ApiOperation(value = "Upload bank document Api", nickname = "uploadBankDocument", notes = "This API is used for uploading bank document")
    public ResponseEntity<Mono<String>> uploadBankDocument(@RequestPart(value = "file", required = false) final Mono<FilePart> file) {
        return new ResponseEntity<>(bankDocumentFacade.uploadBankDocument(file), HttpStatus.OK);
    }

    @DefaultApiResponse
    @GetMapping(value = "/{documentId}", produces = MediaType.APPLICATION_PDF_VALUE)
    @ApiOperation(value = "Download bank document Api", nickname = "downloadBankDocument", notes = "This API is used for download bank document")
    public ResponseEntity<Mono<InputStreamResource>> downloadBankDocument(@PathVariable("documentId") final String documentId) {
        return new ResponseEntity<>(bankDocumentFacade.downloadBankDocument(documentId), HttpStatus.OK);
    }

    @DefaultApiResponse
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Download all bank documents Api", nickname = "downloadAllBankDocument", notes = "This API is used for download all bank documents")
    public ResponseEntity<Flux<MediaDownloadResponse>> downloadAllBankDocument() {
        return new ResponseEntity<>(bankDocumentFacade.downloadAllBankDocument(), HttpStatus.OK);
    }

    @DefaultApiResponse
    @DeleteMapping(value = "/{documentId}")
    @ApiOperation(value = "Delete bank document Api", nickname = "deleteBankDocument", notes = "This API is used for Delete bank document")
    public ResponseEntity<Mono<Void>> deleteBankDocument(@PathVariable("documentId") final String documentId) {
        return new ResponseEntity<>(bankDocumentFacade.deleteBankDocument(documentId), HttpStatus.OK);
    }
}
