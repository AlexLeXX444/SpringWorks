package ru.app.application.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.app.application.entity.Person;
import ru.app.application.service.PersonService;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final PersonService personService;

    @Override
    public void run(String... args) throws Exception {
        if (personService.getAllPersons().isEmpty()) {
            int i = 0;
            for (i = 0; i < 10; i++) {
                Person person = new Person();
                person.setAge(i + 20);
                person.setName("Name " + i);
                person.setEmail("Name " + i + "@gmail.com");
                personService.addPerson(person);
            }
            System.out.println("Add " + i + " persons in database.");
        }

    }
}
