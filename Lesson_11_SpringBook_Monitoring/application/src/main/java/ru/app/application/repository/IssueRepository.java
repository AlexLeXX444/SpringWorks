package ru.app.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.app.application.entity.Book;
import ru.app.application.entity.Issue;
import ru.app.application.entity.Reader;

import java.util.List;
import java.util.Optional;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
    List<Issue> findByReader(Reader reader);

    List<Issue> findByBook(Book book);
}
