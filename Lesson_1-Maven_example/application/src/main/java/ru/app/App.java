package ru.app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class App
{
    public static void main( String[] args )
    {
        // Создаем объект Person
        Person person = new Person("Иван", "Иванов", 30);

        // Инициализируем Gson с красивым выводом
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Сериализация в JSON
        String json = gson.toJson(person);
        System.out.println("Объект в JSON:\n" + json);

        // Десериализация обратно в объект
        Person fromJson = gson.fromJson(json, Person.class);
        System.out.println("\nОбъект из JSON: " + fromJson);

        // Проверка работы equals()
        System.out.println("\nОбъекты идентичны: " + person.equals(fromJson));
    }
}
