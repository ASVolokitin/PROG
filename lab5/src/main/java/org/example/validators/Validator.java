package org.example.validators;

public abstract class Validator {

    /**
     * Базовый класс валидатора.
     */

    private String name;
    private String description;

    /**
     * Конструктор валидатора по умолчанию.
     * @param name
     * @param description
     */

    public Validator(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Метод, реализуемый всеми валидаторами.
     * @param data
     * @return
     */

    public boolean validate(String data) {return false;}
}
