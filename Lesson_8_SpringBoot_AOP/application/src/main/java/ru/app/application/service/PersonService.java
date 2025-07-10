package ru.app.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.app.application.aop.annotation.TrackUserAction;
import ru.app.application.entity.Person;
import ru.app.application.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    @TrackUserAction
    public Person addPerson(Person person) {
        return personRepository.save(person);
    }

    @TrackUserAction
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }
}
