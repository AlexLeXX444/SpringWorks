package ru.app.application.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.app.application.entity.Book;
import ru.app.application.entity.Issue;
import ru.app.application.entity.Reader;
import ru.app.application.service.BookService;
import ru.app.application.service.IssueService;
import ru.app.application.service.ReaderService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/issue")
public class IssueRestController {

    private final IssueService issueService;
    private final ReaderService readerService;
    private final BookService bookService;

    @PutMapping("/open")
    public ResponseEntity<Issue> openNewIssue(@RequestParam long readerId, @RequestParam long bookId) {
        Optional<Reader> reader = readerService.getReaderById(readerId);
        Optional<Book> book = bookService.getBookById(bookId);
        if (reader.isEmpty() || book.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        Optional<Issue> issue = issueService.openIssue(reader.get(), book.get());

        return issue.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/reader/{id}")
    public ResponseEntity<List<Issue>> getIssuesByReaderId(@PathVariable("id") long readerId) {
        Optional<Reader> reader = readerService.getReaderById(readerId);

        if (reader.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<Issue> issues = issueService.getIssuesByReader(reader.get());

        if (!issues.isEmpty()) {
            return ResponseEntity.ok(issues);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<List<Issue>> getIssuesBuBookId(@PathVariable("id") long bookId) {
        Optional<Book> book = bookService.getBookById(bookId);

        if (book.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<Issue> issues = issueService.getIssuesByBook(book.get());

        if (!issues.isEmpty()) {
            return ResponseEntity.ok(issues);
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/close/{id}")
    public ResponseEntity<Issue> closeIssueById(@PathVariable("id") long id) {
        if (issueService.closeIssue(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
