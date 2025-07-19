package ru.app.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.app.application.entity.Book;
import ru.app.application.entity.Issue;
import ru.app.application.entity.Reader;
import ru.app.application.repository.IssueRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IssueService {

    private final IssueRepository issueRepository;
    private final BookService bookService;

    public Optional<Issue> openIssue(Reader reader, Book book) {
        if (book.getCount() > 0) {
            bookService.countDown(book.getId());
            return Optional.of(issueRepository.save(new Issue(reader, book)));
        }
        return Optional.empty();
    }

    public List<Issue> getIssuesByReader(Reader reader) {
        return issueRepository.findByReader(reader);
    }

    public List<Issue> getIssuesByBook(Book book) {
        return issueRepository.findByBook(book);
    }

    public boolean closeIssue(long id) {
        Optional<Issue> closedIssue = issueRepository.findById(id);
        if (closedIssue.isPresent()) {
            if (closedIssue.get().getIsActive()) {
                bookService.countUp(closedIssue.get().getBook().getId());
                closedIssue.get().setEndAt(LocalDateTime.now());
                closedIssue.get().setActive(false);
                issueRepository.save(closedIssue.get());
                return true;
            }
        }
        return false;
    }
}
