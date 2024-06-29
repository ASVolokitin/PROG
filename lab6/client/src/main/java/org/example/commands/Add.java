package org.example.commands;

import org.example.functionalClasses.CollectionManager;
import org.example.functionalClasses.TCPClient;
import org.example.movieClasses.Movie;
import org.example.requests.AddRequest;
import org.example.requests.Request;
import org.example.responses.AddResponse;
import org.example.responses.Response;

import java.io.IOException;
import java.util.HashMap;


public class Add extends Command {

    /**
     * Команда add. Добавляет в коллекцию новый элемент.
     */

    private TCPClient client;

    /**
     * Конструктор объекта команды Add.
     */

    public Add(TCPClient client) {
        super("add", "Добавить новый элемент в коллекцию.");
        this.client = client;
    }

    /**
     * Метод, запускающий выполнение команды.
     * @param arg
     */

    public void execute (String arg) throws IOException {
        System.out.println("Запись нового элемента в коллекцию ...");
//        HashMap<Integer, String> data = new HashMap<>(new ReadMovieData().readMovieData());
        HashMap<Integer, String> data = new HashMap<>(); {
            data.put(0, "4");
            data.put(1, "3");
            data.put(2, "4");
            data.put(3, "4");
            data.put(4, "DRAMA");
            data.put(5, "NC_17");
            data.put(6, "4");
            data.put(7, "2006-11-11");
            data.put(8, "4");
            data.put(9, "4");
            data.put(10, "4");
            data.put(11, "4");
        }
        AddResponse response = (AddResponse) client.send(new AddRequest(data));
        System.out.println("Операция " + response.getName() + " выполнена успешно.");
    }
}
