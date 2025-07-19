package ru.app.application.controller.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import ru.app.application.entity.Reader;
import ru.app.application.service.ReaderService;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReaderRestController.class)
class ReaderRestControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReaderService readerService;

    @Test
    void getReaderById_ShouldReturnReader_WhenExists() throws Exception {
        Reader reader = new Reader("John Doe");
        reader.setId(1L);

        when(readerService.getReaderById(1L)).thenReturn(Optional.of(reader));

        mockMvc.perform(get("/reader/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    void getReaderById_ShouldReturnNotFound_WhenNotExists() throws Exception {
        when(readerService.getReaderById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/reader/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllReaders_ShouldReturnList_WhenNotEmpty() throws Exception {
        List<Reader> readers = List.of(
                new Reader("Alice"),
                new Reader("Bob")
        );

        when(readerService.getReaders()).thenReturn(readers);

        mockMvc.perform(get("/reader/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getAllReaders_ShouldReturnNotFound_WhenEmpty() throws Exception {
        when(readerService.getReaders()).thenReturn(List.of());

        mockMvc.perform(get("/reader/list"))
                .andExpect(status().isNotFound());
    }
}