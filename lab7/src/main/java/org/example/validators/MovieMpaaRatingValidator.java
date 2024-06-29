package org.example.validators;

import org.example.enums.MovieGenre;
import org.example.enums.MpaaRating;

import java.util.Objects;

public class MovieMpaaRatingValidator extends Validator {

    /**
     * Класс валидации введённого значения рейтинга MPAA.
     */

    /**
     * Конструктор, задающий название и описание валидатора.
     */

    public MovieMpaaRatingValidator() {
        super("MovieMpaaRatingValidator", "Validates movie's MPAA rating level.");
    }

    /**
     * Метод, выполняющий валидацию введённого значения.
     * @param data
     * @return
     */

    public boolean validate(String data) {
        if (Objects.equals(data, "")) return false;
        try {
            MpaaRating parsedValue = Enum.valueOf(MpaaRating.class, data);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
