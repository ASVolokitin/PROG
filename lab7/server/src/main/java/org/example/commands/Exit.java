package org.example.commands;

import org.example.functionalClasses.CollectionManager;
import org.example.functionalClasses.Reader;
import org.example.requests.Request;
import org.example.responses.DefaultResponse;

public class Exit extends Command {

    /**
     * Команда exit. Завершает выполнение программы.
     */

    private CollectionManager collectionManager;

    /**
     * Конструктор объекта команды exit.
     * @param collectionManager
     */

    public Exit(CollectionManager collectionManager) {
        super("exit", "Завершить программу (без сохранения в файл).");
        this.collectionManager = collectionManager;
    }

    /**
     * Метод, запускающий выполнение команды.
     * @param request
     */

    @Override
    public DefaultResponse execute(Request request) {
        return new DefaultResponse("blank");
    }
}
