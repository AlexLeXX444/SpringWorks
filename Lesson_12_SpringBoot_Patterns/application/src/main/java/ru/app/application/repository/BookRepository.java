package ru.app.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.app.application.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
