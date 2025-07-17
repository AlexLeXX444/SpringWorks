package ru.app.application.controller.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.app.application.entity.Book;
import ru.app.application.service.BookService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BookRestController.class)
class BookRestControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @InjectMocks
    private BookRestController bookRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getBookById_ShouldReturnBook_WhenExists() throws Exception {
        Book book = new Book();
        book.setId(1L);
        book.setName("Java Guide");

        when(bookService.getBookById(1L)).thenReturn(Optional.of(book));

        mockMvc.perform(get("/book/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Java Guide")); // исправлено на "name"
    }

    @Test
    void getBookById_ShouldReturnNotFound_WhenNotExists() throws Exception {
        when(bookService.getBookById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/book/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllBooks_ShouldReturnList_WhenNotEmpty() throws Exception {
        List<Book> books = Arrays.asList(
                new Book("Book One", "Author A", 1),
                new Book("Book Two", "Author B", 2)
        );

        when(bookService.getBooks()).thenReturn(books);

        mockMvc.perform(get("/book/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getAllBooks_ShouldReturnNotFound_WhenEmpty() throws Exception {
        when(bookService.getBooks()).thenReturn(List.of());

        mockMvc.perform(get("/book/all"))
                .andExpect(status().isNotFound());
    }
}