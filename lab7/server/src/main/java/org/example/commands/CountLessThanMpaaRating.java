package org.example.commands;

import org.example.enums.MpaaRating;
import org.example.functionalClasses.*;
import org.example.movieClasses.Movie;
import org.example.requests.CountMpaaRequest;
import org.example.requests.Request;
import org.example.responses.DefaultResponse;
import org.example.responses.Response;

import java.util.ArrayList;
import java.util.stream.Stream;

public class CountLessThanMpaaRating extends Command {

    /**
     * Команда count_less_than_mpaa_rating. Возвращает количество фильмов коллекции, чей MPAA рейтинг ниже заданного.
     */

    private CollectionManager collectionManager;
    private MpaaComparator mpaaComparator = new MpaaComparator();
    private ArrayList<SetValues> settings = new ArrayList<SetValues>(new Settings().getSettings());

    /**
     * Конструктор объекта команды count_less_than_mpaa_rating.
     *
     * @param collectionManager
     */

    public CountLessThanMpaaRating(CollectionManager collectionManager) {
        super("count_less_than_mpaa_rating", "Вывести количество элементов, значение поля mpaaRating которых меньше заданного.");
        this.collectionManager = collectionManager;
    }

    /**
     * Метод, запускающий выполнение команды.
     *
     * @param request
     */

    public Response execute(Request request) {
        CountMpaaRequest countMpaaRequest = (CountMpaaRequest) request;
        int ratingValue = countMpaaRequest.getRatingValue();
        MpaaComparator mpaaComparator = new MpaaComparator();
        String[] data = new String[1];
        data[0] = String.valueOf(collectionManager.getCollection().stream().filter(movie -> mpaaComparator.getRating(movie.getMpaaRating()) < ratingValue).count());
        return new DefaultResponse("count_less_than_mpaa_rating", data);
    }
}
