package org.example.validators;

public class MovieCoordXValidator extends Validator {

    /**
     * Класс валидации введённого значения координаты X.
     */

    /**
     * Конструктор, задающий название и описание валидатора.
     */

    public MovieCoordXValidator() {
        super("MovieCoordXValidator", "Validates movie's X coordinate.");
    }

    /**
     * Метод, выполняющий валидацию введённого значения.
     * @param data
     * @return
     */

    public boolean validate(String data) {
        if (!data.equals("")) {
            try {
                int parsedValue = Integer.parseInt(data);
                if (parsedValue > -252) return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }
}
