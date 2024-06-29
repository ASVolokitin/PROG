package org.example.validators;

import java.time.DateTimeException;
import java.time.LocalDate;

public class PersonWeightValidator extends Validator {

    /**
     * Класс валидации введённого значения веса сценариста.
     */

    /**
     * Конструктор, задающий название и описание валидатора.
     */

    public PersonWeightValidator(){
        super("PersonWeightValidator", "Validates screenwriter's weight.");
    }

    /**
     * Метод, выполняющий валидацию введённого значения.
     * @param data
     * @return
     */

    public boolean validate(String data) {
        if (data == null || data.equals("")) return false;
        try {
            double parsedValue = Double.parseDouble(data);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
