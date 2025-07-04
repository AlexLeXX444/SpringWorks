package ru.app.application.service;

import org.springframework.stereotype.Service;
import ru.app.application.entity.Person;
import ru.app.application.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Optional<Person> findById(long id) {
        return personRepository.findById(id);
    }

    public void deleteById(long id) {
        personRepository.deleteById(id);
    }
}
