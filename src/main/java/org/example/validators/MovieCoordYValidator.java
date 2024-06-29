package org.example.validators;

import java.util.Objects;

public class MovieCoordYValidator extends Validator {

    /**
     * Класс валидации введённого значения координаты Y.
     */

    /**
     * Конструктор, задающий название и описание валидатора.
     */

    public MovieCoordYValidator() {
        super("MovieCoordXValidator", "Validates movie's Y coordinate.");
    }

    /**
     * Метод, выполняющий валидацию введённого значения.
     * @param data
     * @return
     */

    public boolean validate(String data) {
        if (!Objects.equals(data, "")) {
            try {
                Integer parsedValue = Integer.parseInt(data);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }
}
