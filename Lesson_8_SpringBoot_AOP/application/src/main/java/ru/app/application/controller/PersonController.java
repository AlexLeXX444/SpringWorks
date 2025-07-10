package ru.app.application.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.app.application.entity.Person;
import ru.app.application.service.PersonService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping("/person/list")
    public String getAllPersons(Model model) {
        List<Person> persons = personService.getAllPersons();
        model.addAttribute("persons", persons);
        return "persons";
    }

    // Отображение формы добавления
    @GetMapping("/person/add")
    public String showAddPersonForm(Model model) {
        model.addAttribute("person", new Person());
        return "add-person";
    }

    // Обработка отправленной формы
    @PostMapping("/person/add")
    public String addPerson(@ModelAttribute("person") Person person) {
        personService.addPerson(person);
        return "redirect:/person/list";
    }
}
