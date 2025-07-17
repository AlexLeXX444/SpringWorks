package ru.app.application.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reader_id")
    private Reader reader;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    private LocalDateTime startAt;

    private LocalDateTime endAt;

    private boolean isActive;

    @PrePersist
    protected void onCreate() {
        startAt = LocalDateTime.now();
        endAt = LocalDateTime.now();
        isActive = true;
    }

    public Issue (Reader reader, Book book) {
        this.reader = reader;
        this.book = book;
    }

    public boolean getIsActive() {
        return this.isActive;
    }
}
