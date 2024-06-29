package org.example.movieClasses;

import java.time.LocalDate;

public class Person {

    /**
     * Класс, определяющий информацию о сценаристе.
     */

    private String name; //Поле не может быть null, Строка не может быть пустой
    private LocalDate birthday; //Поле может быть null
    private Double weight; //Поле не может быть null, Значение поля должно быть больше 0
    private Location location; //Поле не может быть null

    /**
     * Конструктор по умолчанию. Необходим для парсинга входных файлов.
     */

    public Person() {
        String name;
        LocalDate birthday;
        Double weight;
        Location location;

    }

    /**
     * Конструктор, задающий информацию о сценаристе.
     * @param name
     * @param birthday
     * @param weight
     * @param location
     */

    public Person(String name, LocalDate birthday, Double weight, Location location) {
        this.name = name;
        this.birthday = birthday;
        this.weight = weight;
        this.location = location;
    }

    /**
     * Метод, возвращающий имя сценариста.
     * @return
     */

    public String getName() {
        return name;
    }

    /**
     * Метод, возвращающий дату рождения сценариста.
     * @return
     */

    public LocalDate getBirthday() {
        return birthday;
    }

    /**
     * Метод, возвращающий вес сценариста.
     * @return
     */

    public Double getWeight() {
        return weight;
    }

    /**
     * Метод, возвращающий место рождения сценариста.
     * @return
     */

    public Location getLocation() {
        return location;
    }

    /**
     * Метод, возвращающий информацию о сценаристе в строковом представлении.
     * @return
     */

    @Override
    public String toString() {
        return "\n\tИмя: " + name +
                "\n\tДата рождения: " + (birthday == null ? "--информация отсутствует--" : birthday) +
                "\n\tВес: " + weight +
                " кг\n\tМесто рождения: " + location;
    }
}
