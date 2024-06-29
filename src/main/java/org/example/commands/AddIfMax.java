package org.example.commands;

import org.example.functionalClasses.*;
import org.example.movieClasses.Movie;
import org.example.requests.AddIfMaxRequest;
import org.example.requests.AddRequest;
import org.example.responses.AddIfMaxResponse;
import org.example.responses.AddResponse;
import org.example.validators.Validator;
import org.example.validators.ValidatorList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class AddIfMax extends Command {

    /**
     * Команда add_if_max. Добавляет в коллекцию новый элемент, если его значение (количество премий 'Оскар') превышает максимальное по коллекции.
     */

    private TCPClient client;

    /**
     * Конструктор объекта команды AddIfMax.
     * @param client
     */

    public AddIfMax(TCPClient client) {
        super("add_if_max", "Добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции.");
        this.client = client;
    }

    /**
     *  Метод, запускающий выполнение команды.
     * @param arg
     */

    @Override
    public void execute(String arg) throws IOException {
        try {
//          HashMap<Integer, String> data = new HashMap<>(new ReadMovieData().readMovieData());
            HashMap<Integer, String> data = new HashMap<>(); {
                data.put(0, "HadnDadn");
                data.put(1, "495");
                data.put(2, "229");
                data.put(3, arg);
                data.put(4, "ADVENTURE");
                data.put(5, "PG_13");
                data.put(6, "Варвара Краминова");
                data.put(7, "2001-07-28");
                data.put(8, "63.12");
                data.put(9, "48");
                data.put(10, "821");
                data.put(11, "Россия");
                data.put(12, client.getLogin());
            }
            AddIfMaxResponse response = (AddIfMaxResponse) client.send(new AddIfMaxRequest(client.getLogin(), client.getPassword(), data));
            System.out.println(response.getResult());
        } catch (NumberFormatException e) {
            System.out.println("Количество премий 'Оскар' должно быть целым положительным числом.");
        }
    }
}
