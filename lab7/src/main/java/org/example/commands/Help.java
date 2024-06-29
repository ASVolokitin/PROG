package org.example.commands;

import org.example.functionalClasses.TCPClient;
import org.example.requests.Request;
import org.example.responses.DefaultResponse;

import java.io.IOException;


public class Help extends Command {

    private TCPClient client;

    /**
     * Команда help. Выводит список доступных команд и их описание.
     */


    /**
     * Конструктор объекта команды help.
     * @param client
     */

    public Help(TCPClient client) {
        super("help", "Вывести справку по доступным командам.");
        this.client = client;
    }

    /**
     * Метод, запускающий выполнение команды.
     * @param arg
     */

    @Override
    public void execute(String arg) throws IOException {
        DefaultResponse response = (DefaultResponse) client.send(new Request("help", client.getLogin(), client.getPassword()));
        System.out.println(response.getStringData());

    }
}
