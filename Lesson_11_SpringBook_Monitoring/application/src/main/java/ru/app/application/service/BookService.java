package ru.app.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.app.application.entity.Book;
import ru.app.application.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book addBook(String author, String name, int count) {
        return bookRepository.save(new Book (author, name, count));
    }

    public Optional<Book> getBookById(long id) {
        return bookRepository.findById(id);
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public boolean deleteBookById(long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void countUp(long id) {
        if (bookRepository.existsById(id)) {
            Book book = this.getBookById(id).get();
            book.setCount(book.getCount() + 1);
            bookRepository.save(book);
        }
    }

    public void countDown(long id) {
        if (bookRepository.existsById(id)) {
            Book book = this.getBookById(id).get();
            if (book.getCount() > 0) {
                book.setCount(book.getCount() - 1);
                bookRepository.save(book);
            }
        }
    }
}
