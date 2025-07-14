package ru.app.bookservice.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import ru.app.bookservice.entity.Book;
import ru.app.bookservice.service.BookService;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final BookService bookService;

    @Override
    public void run(String... args) throws Exception {
        if (bookService.getAllBooks().isEmpty()) {
            List<String> bookAuthors = new ArrayList<>();
            bookAuthors.add("Pushkin");
            bookAuthors.add("Tolstoy");
            bookAuthors.add("Lermontov");
            bookAuthors.add("Block");
            bookAuthors.add("Mayakovskiy");

            List<String> bookNames = new ArrayList<>();
            bookNames.add("Dubrovskiy");
            bookNames.add("Voina i mir");
            bookNames.add("Maskarad");
            bookNames.add("Dvenadscat");
            bookNames.add("Banya");

            for (int i = 0; i < bookAuthors.toArray().length; i++) {
                bookService.addNewBook(new Book(bookAuthors.get(i), bookNames.get(i), i));
            }
        }
    }
}
