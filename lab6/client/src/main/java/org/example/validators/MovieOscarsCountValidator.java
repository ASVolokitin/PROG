package org.example.validators;

import java.util.Objects;

public class MovieOscarsCountValidator extends Validator {

    /**
     * Класс валидации введённого значения количества премий 'Оскар'.
     */

    /**
     * Конструктор, задающий название и описание валидатора.
     */

    public MovieOscarsCountValidator(){
        super("MovieOscarsCountValidator", "Validates movie's Oscars' count.");
    }

    /**
     * Метод, выполняющий валидацию введённого значения.
     * @param data
     * @return
     */

    public boolean validate(String data) {
        if (!Objects.equals(data, "")) {
            try {
                long parsedValue = Long.parseLong(data);
                if (parsedValue > 0) return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }
}
