package ru.app.application.controller.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.app.application.entity.Reader;
import ru.app.application.service.ReaderService;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ReaderRestControllerUnitTest {

    @Mock
    private ReaderService readerService;

    @InjectMocks
    private ReaderRestController readerRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getReaderById_ReturnsReader_WhenFound() {
        Reader reader = new Reader("John Doe");
        reader.setId(1L);

        when(readerService.getReaderById(1L)).thenReturn(Optional.of(reader));

        var response = readerRestController.getReaderById(1L);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(reader);
    }

    @Test
    void getReaderById_ReturnsNotFound_WhenNotExists() {
        when(readerService.getReaderById(1L)).thenReturn(Optional.empty());

        var response = readerRestController.getReaderById(1L);

        assertThat(response.getStatusCodeValue()).isEqualTo(404);
    }

    @Test
    void getAllReaders_ReturnsList_WhenNotEmpty() {
        List<Reader> readers = List.of(
                new Reader("Alice"),
                new Reader("Bob")
        );

        when(readerService.getReaders()).thenReturn(readers);

        var response = readerRestController.getAllReaders();

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).hasSize(2);
    }

    @Test
    void getAllReaders_ReturnsNotFound_WhenEmpty() {
        when(readerService.getReaders()).thenReturn(List.of());

        var response = readerRestController.getAllReaders();

        assertThat(response.getStatusCodeValue()).isEqualTo(404);
    }
}
