package org.example.commands;

import org.example.enums.MpaaRating;
import org.example.functionalClasses.*;
import org.example.requests.BlankRequest;
import org.example.requests.CountMpaaRequest;
import org.example.responses.DefaultResponse;

import java.io.IOException;
import java.util.ArrayList;

public class CountLessThanMpaaRating extends Command{

    /**
     * Команда count_less_than_mpaa_rating. Возвращает количество фильмов коллекции, чей MPAA рейтинг ниже заданного.
     */

    private TCPClient client;
    private MpaaComparator mpaaComparator = new MpaaComparator();
    private ArrayList<SetValues> settings = new ArrayList<SetValues>(new Settings().getSettings());

    /**
     * Конструктор объекта команды count_less_than_mpaa_rating.
     * @param client
     */

    public CountLessThanMpaaRating(TCPClient client) {
        super("count_less_than_mpaa_rating", "Вывести количество элементов, значение поля mpaaRating которых меньше заданного.");
        this.client = client;
    }

    /**
     * Метод, запускающий выполнение команды.
     * @param arg
     */

    @Override
    public void execute(String arg) throws IOException  {
        try {
            MpaaRating parsedValue = Enum.valueOf(MpaaRating.class, arg);
            CountMpaaRequest request = new CountMpaaRequest(client.getLogin(), client.getPassword(), mpaaComparator.getRating(parsedValue));
            DefaultResponse response = (DefaultResponse) client.send(request);
            System.out.println("\nКоличество фильмов с рейтингом ниже " + arg + ": " + response.getStringData().strip());
        } catch (IllegalArgumentException e) {
            System.out.println("Введенное значение не является рейтингом по шкале MPAA.");
        }

        }
    }
