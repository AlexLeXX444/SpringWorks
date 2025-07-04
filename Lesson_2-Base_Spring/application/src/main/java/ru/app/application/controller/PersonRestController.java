package ru.app.application.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.app.application.entity.Person;
import ru.app.application.service.PersonService;

import java.util.List;

@RestController
public class PersonRestController {

    private final PersonService personService;

    public PersonRestController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("person/all")
    public List<Person> getAll() {
        return personService.findAll();
    }

    @GetMapping("person/delete/{id}")
    public void delete(@PathVariable Long id) {
        personService.deleteById(id);
    }
}
