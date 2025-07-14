package ru.app.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.app.bookservice.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book getBookById(long bookId);
}
