package org.example.commands;

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
        System.out.print("Вы действительно хотите выйти? (y/n): ");
        String cmd = Reader.Read().strip();
        while (true) {
            if (cmd.equals("y")) System.exit(0);
            else if (cmd.equals("n")) return;
            System.out.print("Введите 'y' для выхода и 'n' для отмены (без кавычек): ");
            cmd = Reader.Read().strip();
        }
    }
}
