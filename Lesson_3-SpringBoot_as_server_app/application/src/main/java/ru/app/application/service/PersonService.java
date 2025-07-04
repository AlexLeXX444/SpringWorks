package ru.app.application.service;

import org.springframework.stereotype.Service;
import ru.app.application.entity.Person;
import ru.app.application.repository.PersonRepository;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final NotificationService notificationService;

    public PersonService(PersonRepository personRepository, NotificationService notificationService) {
        this.personRepository = personRepository;
        this.notificationService = notificationService;
    }

    public Person savePerson(Person person) {
        notificationService.notify("create", "New user was created");
        return personRepository.save(person);
    }

    public List<Person> getAllPersons() {
        notificationService.notify("get", "Ge users listed");
        return personRepository.findAll();
    }
}
