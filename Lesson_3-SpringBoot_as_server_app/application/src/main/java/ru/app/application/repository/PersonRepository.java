package ru.app.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.app.application.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
