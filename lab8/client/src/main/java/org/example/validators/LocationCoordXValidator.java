package org.example.validators;

import java.util.Objects;

public class LocationCoordXValidator extends Validator {

    /**
     * Класс валидации введённого значения координаты X.
     */

    /**
     * Конструктор, задающий название и описание валидатора.
     */

    public LocationCoordXValidator() {
        super("LocationCoordXValidator", "Validates screenwriter location's X coordinate.");
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
