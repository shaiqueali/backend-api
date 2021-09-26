package com.assignment.api.feature.media.controller;

import com.assignment.api.media.web.controller.BankDocumentController;
import com.assignment.api.media.web.facade.BankDocumentFacade;
import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

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
        final MultipartBodyBuilder multipartBodyBuilder = new MultipartBodyBuilder();
        multipartBodyBuilder.part("file", new ClassPathResource("sample.pdf"));
        when(bankDocumentFacade.uploadBankDocument(any(FilePart.class))).thenReturn(Mono.just("31323123"));
        final EntityExchangeResult<byte[]> result = webTestClient.post().uri("/v1/documents")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(multipartBodyBuilder.build()))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody().returnResult();
        Assertions.assertEquals("31323123", new String(result.getResponseBodyContent(), StandardCharsets.UTF_8));
    }

    @Test
    @SneakyThrows
    void testDownloadBankDocument() {
        final Resource resource = new ClassPathResource("sample.pdf");
        when(bankDocumentFacade.downloadBankDocument(any(String.class))).thenReturn(Mono.just(new InputStreamResource(resource.getInputStream())));
        final EntityExchangeResult<byte[]> result = webTestClient.get().uri("/v1/documents/{documentId}", 1)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody().returnResult();
        final InputStreamResource inputStreamResource = new InputStreamResource(new ByteArrayInputStream(result.getResponseBodyContent()));
        Assertions.assertTrue(inputStreamResource.exists());
    }
}
