package com.assignment.api.feature.media.facade;


import com.assignment.api.media.service.MediaService;
import com.assignment.api.media.web.dto.MediaDownloadResponse;
import com.assignment.api.media.web.facade.BankDocumentFacade;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.InputStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BankDocumentFacadeTest {

    @Mock
    MediaService mediaService;

    @InjectMocks
    BankDocumentFacade bankDocumentFacade;

    @Test
    public void testDownloadAllBankDocument() {
        when(mediaService.downloadAllMedias()).thenReturn(Flux.just(new MediaDownloadResponse()));
        bankDocumentFacade.downloadAllBankDocument();
        verify(mediaService, atLeastOnce()).downloadAllMedias();
    }

    @Test
    public void testUploadBankDocument() {
        FilePart file = mock(FilePart.class);
        when(mediaService.uploadMedia(any(Mono.class))).thenReturn(Mono.just(new String()));
        bankDocumentFacade.uploadBankDocument(file);
        verify(mediaService, atLeastOnce()).uploadMedia(any(Mono.class));
    }

    @Test
    public void testDownloadBankDocument() {
        final String documentId = "1";
        InputStream resource = mock(InputStream.class);
        when(mediaService.downloadMedia(any(String.class))).thenReturn(Mono.just(resource));
        bankDocumentFacade.downloadBankDocument(documentId);
        verify(mediaService, atLeastOnce()).downloadMedia(any(String.class));
    }

    @Test
    public void testDeleteBankDocument() {
        final String documentId = "1";
        bankDocumentFacade.deleteBankDocument(documentId);
        verify(mediaService, atLeastOnce()).deleteMedia(any(String.class));
    }
}
