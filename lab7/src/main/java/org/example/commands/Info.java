package org.example.commands;

import org.example.functionalClasses.TCPClient;
import org.example.requests.BlankRequest;
import org.example.responses.DefaultResponse;

import java.io.IOException;

public class Info extends Command {

    /**
     * Команда info. Выводит информацию о коллекции.
     */

    private TCPClient client;

    /**
     * Конструктор объекта команды info.
     * @param client
     */

    public Info(TCPClient client) {
        super("info", "Вывести в стандартный поток вывода информацию о коллекции.");
        this.client = client;
    }

    /**
     * Метод, запускающий выполнение команды.
     * @param arg
     */

    @Override
    public void execute(String arg) throws IOException {
        DefaultResponse response = (DefaultResponse) client.send(new BlankRequest(client.getLogin(), client.getPassword(), "info"));
        System.out.println(response.getStringData());

    }
}
