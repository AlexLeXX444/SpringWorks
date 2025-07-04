package ru.app.application.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.app.application.entity.Person;
import ru.app.application.repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final PersonRepository personRepository;
    private final List<Person> persons = new ArrayList<>();

    public DataInitializer(PersonRepository personRepository) {
        this.personRepository = personRepository;
        persons.add(new Person("Иван"));
        persons.add(new Person("Игорь"));
        persons.add(new Person("Сергей"));
        persons.add(new Person("Роман"));
        persons.add(new Person("Влад"));
    }

    @Override
    public void run(String... args) throws Exception {
        if (personRepository.count() == 0) {
            personRepository.saveAll(persons);
        }
    }



}
