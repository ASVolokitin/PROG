package org.example.commands;

import org.example.functionalClasses.CollectionManager;
import org.example.requests.Request;
import org.example.responses.DefaultResponse;
import org.example.responses.Response;

public class Clear extends Command {

    /**
     * Команда clear. Очищает коллекцию.
     */

    private CollectionManager collectionManager;

    /**
     * Конструктор объекта команды Clear.
     * @param collectionManager
     */

    public Clear(CollectionManager collectionManager) {
        super("clear", "Очистить коллекцию.");
        this.collectionManager = collectionManager;
    }

    /**
     * Метод, запускающий выполнение команды.
     * @param request
     */

    public Response execute(Request request) {
        collectionManager.clear();
        String[] data = {"Коллекция очищена."};
        return new DefaultResponse("clear", data);
    }
}
