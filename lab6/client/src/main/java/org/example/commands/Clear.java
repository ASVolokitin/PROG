package org.example.commands;

import org.example.functionalClasses.TCPClient;
import org.example.requests.BlankRequest;
import org.example.responses.DefaultResponse;

import java.io.IOException;

public class Clear extends Command {

    private TCPClient client;
    /**
     * Команда clear. Очищает коллекцию.
     */


    /**
     * Конструктор объекта команды Clear.
     * @param client
     */

    public Clear(TCPClient client) {
        super("clear", "Очистить коллекцию.");
        this.client = client;
    }

    /**
     * Метод, запускающий выполнение команды.
     * @param arg
     */

    public void execute(String arg) throws IOException {
        DefaultResponse response = (DefaultResponse) client.send(new BlankRequest("clear"));
        System.out.println(response.getStringData());
    }
}
