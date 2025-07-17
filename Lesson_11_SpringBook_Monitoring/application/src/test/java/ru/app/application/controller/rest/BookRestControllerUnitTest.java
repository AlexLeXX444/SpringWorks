package ru.app.application.controller.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.app.application.entity.Book;
import ru.app.application.service.BookService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class BookRestControllerUnitTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookRestController bookRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getBookById_ReturnsBook_WhenFound() {
        Book book = new Book();
        book.setId(1L);
        when(bookService.getBookById(1L)).thenReturn(Optional.of(book));

        var response = bookRestController.getBookById(1L);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(book);
    }

    @Test
    void getBookById_ReturnsNotFound_WhenNotExists() {
        when(bookService.getBookById(1L)).thenReturn(Optional.empty());

        var response = bookRestController.getBookById(1L);

        assertThat(response.getStatusCodeValue()).isEqualTo(404);
    }

    @Test
    void getAllBooks_ReturnsList_WhenNotEmpty() {
        List<Book> books = Arrays.asList(new Book(), new Book());
        when(bookService.getBooks()).thenReturn(books);

        var response = bookRestController.getAllBooks();

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).hasSize(2);
    }

    @Test
    void getAllBooks_ReturnsNotFound_WhenEmpty() {
        when(bookService.getBooks()).thenReturn(List.of());

        var response = bookRestController.getAllBooks();

        assertThat(response.getStatusCodeValue()).isEqualTo(404);
    }
}
