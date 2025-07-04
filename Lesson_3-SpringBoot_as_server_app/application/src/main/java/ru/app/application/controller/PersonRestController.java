package ru.app.application.controller;

import org.springframework.web.bind.annotation.*;
import ru.app.application.entity.Person;
import ru.app.application.service.DataProcessingService;
import ru.app.application.service.PersonService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class PersonRestController {

    private final PersonService personService;
    private final DataProcessingService dataProcessingService;

    public PersonRestController(PersonService personService, DataProcessingService dataProcessingService) {
        this.personService = personService;
        this.dataProcessingService = dataProcessingService;
    }

    @PostMapping("/body")
    public Person postPerson(@RequestBody Person person) {
        return personService.savePerson(person);
    }

    @GetMapping("")
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }
}
