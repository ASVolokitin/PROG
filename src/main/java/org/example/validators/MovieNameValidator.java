package org.example.validators;

public class MovieNameValidator extends Validator {

    /**
     * Класс валидации введённого названия фильма.
     */

    /**
     * Конструктор, задающий название и описание валидатора.
     */

    public MovieNameValidator(){
        super("MovieNameValidator", "Validates movie's name.");
    }

    /**
     * Метод, выполняющий валидацию введённого значения.
     * @param data
     * @return
     */

    public boolean validate(String data) {
        if (data != null && !data.equals("")) return true;
        return false;
    }
}
