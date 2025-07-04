package ru.app.application.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.app.application.entity.Person;
import ru.app.application.service.DataProcessingService;
import ru.app.application.service.PersonService;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final DataProcessingService dataProcessingService;
    private final PersonService personService;

    public TaskController(DataProcessingService dataProcessingService, PersonService personService) {
        this.dataProcessingService = dataProcessingService;
        this.personService = personService;
    }

    @GetMapping("/sort")
    public List<Person> sort() {
        return dataProcessingService.sortPersonsByAge(personService.getAllPersons());
    }

    @GetMapping("/filter/{age}")
    public List<Person> filter(@PathVariable("age") int age) {
        return dataProcessingService.filterPersonsByAge(personService.getAllPersons(), age);
    }

    @GetMapping("/calc")
    public double calc() {
        return dataProcessingService.calculateAverageAge(personService.getAllPersons());
    }
}
