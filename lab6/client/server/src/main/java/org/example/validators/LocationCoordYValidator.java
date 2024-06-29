package org.example.validators;

import java.util.Objects;

public class LocationCoordYValidator extends Validator {

    /**
     * Класс валидации введённого значения координаты Y.
     */

    /**
     * Конструктор, задающий название и описание валидатора.
     */

    public LocationCoordYValidator() {
        super("LocationCoordYValidator", "Validates screenwriter location's Y coordinate.");
    }

    /**
     * Метод, выполняющий валидацию введённого значения.
     * @param data
     * @return
     */

    public boolean validate(String data) {
        if (!Objects.equals(data, "")) {
            try {
                Long parsedValue = Long.parseLong(data);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }
}
