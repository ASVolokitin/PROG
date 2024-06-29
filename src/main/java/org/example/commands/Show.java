package org.example.commands;

import org.example.functionalClasses.TCPClient;
import org.example.requests.BlankRequest;
import org.example.responses.DefaultResponse;

import java.io.IOException;

public class Show extends Command {

    /**
     * Команда show. Выводит содержимое коллекции.
     */

    private TCPClient client;

    /**
     * Конструктор объекта команды show.
     * @param client
     */

    public Show(TCPClient client) {
        super("show", "Вывести в стандартный поток вывода все элементы коллекции в строковом представлении.");
        this.client = client;
    }

    /**
     * Метод, запускающий выполнение команды.
     * @param arg
     */

    @Override
    public void execute(String arg) {
        try {
            DefaultResponse response = (DefaultResponse) client.send(new BlankRequest(client.getLogin(), client.getPassword(), "show"));
            System.out.println(response.getStringData());
        } catch (IOException e) {
            System.out.println("IO");
        }
    }
}
