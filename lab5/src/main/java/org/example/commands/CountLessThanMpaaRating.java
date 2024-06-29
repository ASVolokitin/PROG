package org.example.commands;

import org.example.enums.MpaaRating;
import org.example.functionalClasses.*;
import org.example.movieClasses.Movie;

import java.util.ArrayList;

public class CountLessThanMpaaRating extends Command{

    /**
     * Команда count_less_than_mpaa_rating. Возвращает количество фильмов коллекции, чей MPAA рейтинг ниже заданного.
     */

    private CollectionManager collectionManager;
    private MpaaComparator mpaaComparator = new MpaaComparator();
    private ArrayList<SetValues> settings = new ArrayList<SetValues>(new Settings().getSettings());

    /**
     * Конструктор объекта команды count_less_than_mpaa_rating.
     * @param collectionManager
     */

    public CountLessThanMpaaRating(CollectionManager collectionManager) {
        super("count_less_than_mpaa_rating mpaaRating", "Вывести количество элементов, значение поля mpaaRating которых меньше заданного.");
        this.collectionManager = collectionManager;
    }

    /**
     * Метод, запускающий выполнение команды.
     * @param arg
     */

    @Override
    public void execute(String arg) {
            try {
                MpaaRating parsedValue = Enum.valueOf(MpaaRating.class, arg);
                int curMpaaRating = mpaaComparator.getRating(parsedValue);
                int cnt = 0;
            for (Movie movie : collectionManager.getCollection()) {
               if (mpaaComparator.getRating(movie.getMpaaRating()) < curMpaaRating) {
                   cnt++;
               }
            }
            System.out.println("Количество фильмов, рейтинг MPAA которых меньше, чем " + parsedValue.toString() + ": " + Integer.toString(cnt));
            } catch (IllegalArgumentException e) {
                System.out.println("Введите значение из списка допустимых значений. " + settings.get(5).getComment() + ".");
            }

        }
    }
