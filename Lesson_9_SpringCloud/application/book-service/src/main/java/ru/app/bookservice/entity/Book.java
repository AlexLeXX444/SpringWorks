package ru.app.bookservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String author;

    private String name;

    private int count;

    public Book(String author, String name, int count) {
        this.author = author;
        this.name = name;
        this.count = count;
    }

    public void bookCountUp() {
        this.count++;
    }

    public void bookCountDown() {
        this.count--;
    }
}
