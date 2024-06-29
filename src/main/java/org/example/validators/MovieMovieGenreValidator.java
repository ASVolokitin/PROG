package org.example.validators;

import org.example.enums.MovieGenre;

import java.util.Objects;

public class MovieMovieGenreValidator extends Validator {

    /**
     * Класс валидации введённого жанра фильма.
     */

    /**
     * Конструктор, задающий название и описание валидатора.
     */

    public MovieMovieGenreValidator() {
        super("MovieMovieGenreValidator", "Validates movie's genre.");
    }

    /**
     * Метод, выполняющий валидацию введённого значения.
     * @param data
     * @return
     */

    public boolean validate(String data) {
        if (Objects.equals(data, "") || Objects.equals(data, "null")) return true;
        else {
            try {
                MovieGenre parsedValue = Enum.valueOf(MovieGenre.class, data);
                return true;
            } catch (IllegalArgumentException e) {
                return false;
            }
        }
    }
}
