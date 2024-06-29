package org.example.validators;

public class LocationNameValidator extends Validator {

    /**
     * Класс валидации введённого названия места.
     */

    /**
     * Конструктор, задающий название и описание валидатора.
     */

    public LocationNameValidator() {
        super("LocationNameValidator", "Validates screenwriter location's name.");
    }

    /**
     * Метод, выполняющий валидацию названия места.
     * @param data
     * @return
     */

    public boolean validate(String data) {

        return true;
    }
}
