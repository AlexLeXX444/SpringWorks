package ru.app.bookservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.app.bookservice.entity.Book;
import ru.app.bookservice.service.BookService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable long id) {
        Optional<Book> book = bookService.getBookById(id);
        if (book.isPresent()) {
            return ResponseEntity.ok(book.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        if (books.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(books);
    }

    @PutMapping("/count-up/{id}")
    public ResponseEntity<Book> setBookCountUp(@PathVariable long id) {
        Optional<Book> book = bookService.setBookCountUp(id);
        if (book.isPresent()) {
            return ResponseEntity.ok(book.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/count-down/{id}")
    public ResponseEntity<Book> setBookCountDown(@PathVariable long id) {
        Optional<Book> book = bookService.setBookCountDown(id);
        if (book.isPresent()) {
            if (book.get().getCount() > 0) {
                return ResponseEntity.ok(book.get());
            }
        }
        return ResponseEntity.notFound().build();
    }
}
