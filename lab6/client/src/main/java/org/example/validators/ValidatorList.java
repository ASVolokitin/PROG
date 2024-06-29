package org.example.validators;

import java.util.HashMap;

public class ValidatorList {

    /**
     * Класс словаря валидаторов.
     */

    private HashMap<Integer, Validator> validatorList = new HashMap<>();

    /**
     * Конструктор, создающий словарь валидаторов.
     */

    public ValidatorList() {
        validatorList.put(0, new MovieNameValidator());
        validatorList.put(1, new MovieCoordXValidator());
        validatorList.put(2, new MovieCoordYValidator());
        validatorList.put(3, new MovieOscarsCountValidator());
        validatorList.put(4, new MovieMovieGenreValidator());
        validatorList.put(5, new MovieMpaaRatingValidator());
        validatorList.put(6, new PersonNameValidator());
        validatorList.put(7, new PersonBirthdayValidator());
        validatorList.put(8, new PersonWeightValidator());
        validatorList.put(9, new LocationCoordXValidator());
        validatorList.put(10, new LocationCoordYValidator());
        validatorList.put(11, new LocationNameValidator());
    }

    /**
     * Метод, возвращающий словарь валидаторов
     * @return
     */

    public HashMap<Integer, Validator> getValidatorList() {
        return validatorList;
    }
}
