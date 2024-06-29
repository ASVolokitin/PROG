package org.example.validators;

import java.time.DateTimeException;
import java.time.LocalDate;

public class PersonBirthdayValidator extends Validator {

    /**
     * Класс валидации введённого значения даты рождения сценариста.
     */

    /**
     * Конструктор, задающий название и описание валидатора.
     */

    public PersonBirthdayValidator(){
        super("PersonBirthdayValidator", "Validates screenwriter's birthday.");
    }

    /**
     * Метод, выполняющий валидацию введённого значения.
     * @param data
     * @return
     */

    public boolean validate(String data) {
        if (data == null || data.equals("") || data.equals("null")) return true;
        try {
            LocalDate parsedValue = LocalDate.parse(data);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }
}
