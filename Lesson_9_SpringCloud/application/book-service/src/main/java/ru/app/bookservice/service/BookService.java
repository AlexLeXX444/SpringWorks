package ru.app.bookservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.app.bookservice.entity.Book;
import ru.app.bookservice.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Optional<Book> getBookById(long id) {
        return Optional.ofNullable(bookRepository.getBookById(id));
    }

    public Book addNewBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> setBookCountUp(long id) {
        Book updatedBook = bookRepository.getBookById(id);
        if (updatedBook != null) {
            updatedBook.bookCountUp();
            return Optional.of(bookRepository.save(updatedBook));
        }
        return Optional.empty();
    }

    public Optional<Book> setBookCountDown(long id) {
        Book updatedBook = bookRepository.getBookById(id);
        if (updatedBook != null) {
            if (updatedBook.getCount() > 0) {
                updatedBook.bookCountDown();
                return Optional.of(bookRepository.save(updatedBook));
            }
        }
        return Optional.empty();
    }
}
