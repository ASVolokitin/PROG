package org.example.movieClasses;

import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;

public class Movies {

    /**
     * Класс объекта коллекции фильмов.
     */

    private LinkedList<Movie> movies = new LinkedList<>();
    private Date initializationDate;

    /**
     * Конструктор объекта коллекции.
     */

    public Movies() {
        this.initializationDate = new Date();
    }

    /**
     * Метод, возвращающий содержимое коллекции.
     * @return
     */

    public LinkedList<Movie> getMovies() {
        return movies;
    }

    /**
     * Метод, возвращающий дату инициализации коллекции.
     * @return
     */

    public Date getInitializationDate() {
        return initializationDate;
    }

    /**
     * Метод, возвращающий количество элементов коллекции.
     * @return
     */

    public int getMoviesAmount() {
        return movies.size();
    }

    /**
     * Метод, выполняющий сортировку фильмов внутри коллекции.
     * @return
     */


}
