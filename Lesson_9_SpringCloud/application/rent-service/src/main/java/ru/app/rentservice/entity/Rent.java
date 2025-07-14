package ru.app.rentservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long readerId;

    private long bookId;

    private LocalDateTime startAt;

    private LocalDateTime endAt;

    @PrePersist
    protected void onCreate() {
        startAt = LocalDateTime.now();
        endAt = LocalDateTime.now();
    }

    public Rent(long readerId, long bookId) {
        this.readerId = readerId;
        this.bookId = bookId;
    }
}
