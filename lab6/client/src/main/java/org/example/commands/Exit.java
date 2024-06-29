package org.example.commands;

import org.example.functionalClasses.CollectionManager;
import org.example.functionalClasses.CommandManager;
import org.example.functionalClasses.Reader;
import org.example.functionalClasses.TCPClient;
import org.example.requests.AddRequest;
import org.example.requests.BlankRequest;
import org.example.responses.AddResponse;
import org.example.responses.DefaultResponse;

import java.io.IOException;

public class Exit extends Command {

    /**
     * Команда exit. Завершает выполнение программы.
     */

    private TCPClient client;

    /**
     * Конструктор объекта команды exit.
     * @param client
     */

    public Exit(TCPClient client) {
        super("exit", "Завершить программу (без сохранения в файл).");
        this.client = client;
    }

    /**
     * Метод, запускающий выполнение команды.
     * @param arg
     */

    @Override
    public void execute(String arg) throws IOException {
        System.exit(0);
    }
}
