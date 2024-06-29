package org.example.commands;

import org.example.functionalClasses.TCPClient;
import org.example.requests.AddRequest;
import org.example.responses.AddResponse;

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
            data.put(0, "Kedr Livanskiy");
            data.put(1, "192");
            data.put(2, "345");
            data.put(3, "4");
            data.put(4, "DRAMA");
            data.put(5, "NC_17");
            data.put(6, "Яна Кедрина");
            data.put(7, "2006-11-11");
            data.put(8, "58.3");
            data.put(9, "684");
            data.put(10, "239");
            data.put(11, "Россия");
            data.put(12, client.getLogin());
        }
        AddResponse response = (AddResponse) client.send(new AddRequest(client.getLogin(), client.getPassword(), data));
        System.out.println("Операция " + response.getName() + " выполнена успешно.");
    }
}
