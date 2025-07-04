package ru.app.application.service;

import org.springframework.stereotype.Service;
import ru.app.application.entity.Person;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataProcessingService {

    public List<Person> sortPersonsByAge(List<Person> persons) {
        return persons.stream()
                .sorted(Comparator.comparing(Person::getAge))
                .collect(Collectors.toList());
    }

    public List<Person> filterPersonsByAge(List<Person> persons, int age) {
        return persons.stream()
                .filter(user -> user.getAge() > age)
                .collect(Collectors.toList());
    }

    public double calculateAverageAge(List<Person> users) {
        return users.stream()
                .mapToInt(Person::getAge)
                .average()
                .orElse(0);
    }
}
