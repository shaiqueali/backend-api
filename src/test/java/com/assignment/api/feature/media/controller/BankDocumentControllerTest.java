package com.assignment.api.feature.media.controller;

import com.assignment.api.media.web.controller.BankDocumentController;
import com.assignment.api.media.web.facade.BankDocumentFacade;
import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@FieldDefaults(level = AccessLevel.PRIVATE)
@WebFluxTest(value = BankDocumentController.class, excludeAutoConfiguration = {ReactiveSecurityAutoConfiguration.class})
public class BankDocumentControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    BankDocumentFacade bankDocumentFacade;

    @Test
    @SneakyThrows
    void testUploadBankDocument() {
        MockMultipartFile file = new MockMultipartFile("test", "test.pdf", MediaType.APPLICATION_PDF_VALUE, "test".getBytes());
        final String documentId = "1";
        when(bankDocumentFacade.uploadBankDocument(any(FilePart.class))).thenReturn(Mono.just(documentId));
        webTestClient.post().uri("/v1/document")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(Mono.just(file), FilePart.class)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody();

    }
}
