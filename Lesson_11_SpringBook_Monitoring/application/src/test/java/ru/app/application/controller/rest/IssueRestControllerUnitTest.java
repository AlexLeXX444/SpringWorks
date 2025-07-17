package ru.app.application.controller.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.app.application.entity.Book;
import ru.app.application.entity.Issue;
import ru.app.application.entity.Reader;
import ru.app.application.service.BookService;
import ru.app.application.service.IssueService;
import ru.app.application.service.ReaderService;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class IssueRestControllerUnitTest {

    @Mock
    private IssueService issueService;

    @Mock
    private ReaderService readerService;

    @Mock
    private BookService bookService;

    @InjectMocks
    private IssueRestController issueRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void openNewIssue_ReturnsOk_WhenIssueOpened() {
        Reader reader = new Reader("Alice");
        reader.setId(1L);

        Book book = new Book("Java",  "Guide", 1);
        book.setId(1L);

        Issue issue = new Issue(reader, book);
        issue.setId(1L);

        when(readerService.getReaderById(1L)).thenReturn(Optional.of(reader));
        when(bookService.getBookById(1L)).thenReturn(Optional.of(book));
        when(issueService.openIssue(reader, book)).thenReturn(Optional.of(issue));

        var response = issueRestController.openNewIssue(1L, 1L);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(issue);
    }

    @Test
    void openNewIssue_ReturnsNoContent_WhenReaderNotFound() {
        when(readerService.getReaderById(1L)).thenReturn(Optional.empty());

        var response = issueRestController.openNewIssue(1L, 1L);

        assertThat(response.getStatusCodeValue()).isEqualTo(204);
    }

    @Test
    void openNewIssue_ReturnsBadRequest_WhenIssueNotCreated() {
        Reader reader = new Reader("Alice");
        reader.setId(1L);

        Book book = new Book("Java",  "Guide", 1);
        book.setId(1L);

        when(readerService.getReaderById(1L)).thenReturn(Optional.of(reader));
        when(bookService.getBookById(1L)).thenReturn(Optional.of(book));
        when(issueService.openIssue(reader, book)).thenReturn(Optional.empty());

        var response = issueRestController.openNewIssue(1L, 1L);

        assertThat(response.getStatusCodeValue()).isEqualTo(400);
    }

    @Test
    void getIssuesByReaderId_ReturnsList_WhenExists() {
        Reader reader = new Reader("Alice");
        reader.setId(1L);

        Issue issue = new Issue(reader, new Book("Java",  "Guide", 1));
        issue.setId(1L);

        when(readerService.getReaderById(1L)).thenReturn(Optional.of(reader));
        when(issueService.getIssuesByReader(reader)).thenReturn(List.of(issue));

        var response = issueRestController.getIssuesByReaderId(1L);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).hasSize(1);
    }

    @Test
    void getIssuesByReaderId_ReturnsNoContent_WhenReaderNotFound() {
        when(readerService.getReaderById(1L)).thenReturn(Optional.empty());

        var response = issueRestController.getIssuesByReaderId(1L);

        assertThat(response.getStatusCodeValue()).isEqualTo(204);
    }

    @Test
    void getIssuesByReaderId_ReturnsNotFound_WhenEmptyList() {
        Reader reader = new Reader("Alice");
        reader.setId(1L);

        when(readerService.getReaderById(1L)).thenReturn(Optional.of(reader));
        when(issueService.getIssuesByReader(reader)).thenReturn(List.of());

        var response = issueRestController.getIssuesByReaderId(1L);

        assertThat(response.getStatusCodeValue()).isEqualTo(404);
    }

    @Test
    void closeIssueById_ReturnsOk_WhenClosed() {
        when(issueService.closeIssue(1L)).thenReturn(true);

        var response = issueRestController.closeIssueById(1L);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    void closeIssueById_ReturnsNotFound_WhenNotClosed() {
        when(issueService.closeIssue(1L)).thenReturn(false);

        var response = issueRestController.closeIssueById(1L);

        assertThat(response.getStatusCodeValue()).isEqualTo(404);
    }
}