package ru.app.application.controller.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import ru.app.application.entity.Book;
import ru.app.application.entity.Issue;
import ru.app.application.entity.Reader;
import ru.app.application.service.BookService;
import ru.app.application.service.IssueService;
import ru.app.application.service.ReaderService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(IssueRestController.class)
class IssueRestControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IssueService issueService;

    @MockBean
    private ReaderService readerService;

    @MockBean
    private BookService bookService;

    @Test
    void openNewIssue_ShouldReturnOk_WhenIssueCreated() throws Exception {
        Reader reader = new Reader("Alice");
        reader.setId(1L);

        Book book = new Book("Java",  "Guide", 1);
        book.setId(1L);

        Issue issue = new Issue(reader, book);
        issue.setId(1L);

        when(readerService.getReaderById(1L)).thenReturn(Optional.of(reader));
        when(bookService.getBookById(1L)).thenReturn(Optional.of(book));
        when(issueService.openIssue(reader, book)).thenReturn(Optional.of(issue));

        mockMvc.perform(put("/issue/open")
                        .param("readerId", "1")
                        .param("bookId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.reader.name").value("Alice"))
                .andExpect(jsonPath("$.book.author").value("Java"));
    }

    @Test
    void openNewIssue_ShouldReturnNoContent_WhenReaderNotFound() throws Exception {
        when(readerService.getReaderById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(put("/issue/open")
                        .param("readerId", "1")
                        .param("bookId", "1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void getIssuesByReaderId_ShouldReturnList_WhenExists() throws Exception {
        Reader reader = new Reader("Alice");
        reader.setId(1L);

        Issue issue = new Issue(reader, new Book("Java",  "Guide", 1));
        issue.setId(1L);

        when(readerService.getReaderById(1L)).thenReturn(Optional.of(reader));
        when(issueService.getIssuesByReader(reader)).thenReturn(List.of(issue));

        mockMvc.perform(get("/issue/reader/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].reader.name").value("Alice"));
    }

    @Test
    void getIssuesByReaderId_ShouldReturnNoContent_WhenReaderNotFound() throws Exception {
        when(readerService.getReaderById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/issue/reader/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void closeIssueById_ShouldReturnOk_WhenSuccess() throws Exception {
        when(issueService.closeIssue(1L)).thenReturn(true);

        mockMvc.perform(put("/issue/close/1"))
                .andExpect(status().isOk());
    }

    @Test
    void closeIssueById_ShouldReturnNotFound_WhenFail() throws Exception {
        when(issueService.closeIssue(1L)).thenReturn(false);

        mockMvc.perform(put("/issue/close/1"))
                .andExpect(status().isNotFound());
    }
}