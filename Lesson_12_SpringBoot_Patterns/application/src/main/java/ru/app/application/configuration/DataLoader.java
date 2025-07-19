package ru.app.application.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import ru.app.application.service.BookService;
import ru.app.application.service.ReaderService;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final ReaderService readerService;
    private final BookService bookService;

    @Override
    public void run(String... args) throws Exception {
        this.feelBooks();
        this.feelReaders();
    }

    private void feelBooks() {
        if (bookService.getBooks().isEmpty()) {
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
                bookService.addBook(bookAuthors.get(i), bookNames.get(i), i);
            }
        }
    }

    private void feelReaders() {
        if (readerService.getReaders().isEmpty()) {
            List<String> readerNames = new ArrayList<>();
            readerNames.add("Alex");
            readerNames.add("Rolf");
            readerNames.add("Mark");
            readerNames.add("Sherman");
            readerNames.add("Lex");

            for (String s : readerNames) {
                readerService.addReader(s);
            }
        }
    }
}
