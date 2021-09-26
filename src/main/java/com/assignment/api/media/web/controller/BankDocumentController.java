package com.assignment.api.media.web.controller;


import com.assignment.api.annotation.DefaultApiResponse;
import com.assignment.api.aspect.LogAround;
import com.assignment.api.media.web.dto.MediaDownloadResponse;
import com.assignment.api.media.web.facade.BankDocumentFacade;
import com.assignment.api.validator.ValidFileExtension;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/document")
@Api(tags = "Document", description = "Documents")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BankDocumentController {

    BankDocumentFacade bankDocumentFacade;

    @DefaultApiResponse
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @LogAround(message = "Upload bank document Api")
    @ApiOperation(value = "Upload bank document Api", nickname = "uploadBankDocument", notes = "This API is used for uploading bank document")
    public ResponseEntity<Mono<String>> uploadBankDocument(@ValidFileExtension(message = "File extension not supported") @RequestPart(value = "file", required = false) final FilePart file) {
        return new ResponseEntity<>(bankDocumentFacade.uploadBankDocument(file), HttpStatus.OK);
    }

    @DefaultApiResponse
    @GetMapping(value = "/{documentId}", produces = MediaType.APPLICATION_PDF_VALUE)
    @LogAround(message = "Download bank document Api")
    @ApiOperation(value = "Download bank document Api", nickname = "downloadBankDocument", notes = "This API is used for download bank document")
    public ResponseEntity<Mono<InputStreamResource>> downloadBankDocument(@PathVariable("documentId") final String documentId) {
        return new ResponseEntity<>(bankDocumentFacade.downloadBankDocument(documentId), HttpStatus.OK);
    }

    @DefaultApiResponse
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @LogAround(message = "Download all bank documents Api")
    @ApiOperation(value = "Download all bank documents Api", nickname = "downloadAllBankDocument", notes = "This API is used for download all bank documents")
    public ResponseEntity<Flux<MediaDownloadResponse>> downloadAllBankDocument() {
        return new ResponseEntity<>(bankDocumentFacade.downloadAllBankDocument(), HttpStatus.OK);
    }

    @DefaultApiResponse
    @DeleteMapping(value = "/{documentId}")
    @LogAround(message = "Delete bank document Api")
    @ApiOperation(value = "Delete bank document Api", nickname = "deleteBankDocument", notes = "This API is used for Delete bank document")
    public ResponseEntity<Mono<Void>> deleteBankDocument(@PathVariable("documentId") final String documentId) {
        return new ResponseEntity<>(bankDocumentFacade.deleteBankDocument(documentId), HttpStatus.OK);
    }
}
