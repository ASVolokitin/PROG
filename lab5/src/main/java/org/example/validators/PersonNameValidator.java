package org.example.validators;

public class PersonNameValidator extends Validator {

    /**
     * Класс валидации введённого значения имени сценариста.
     */

    /**
     * Конструктор, задающий название и описание валидатора.
     */

    public PersonNameValidator(){
        super("PersonNameValidator", "Validates screenwriter's name.");
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
