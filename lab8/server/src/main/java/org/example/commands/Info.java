package org.example.commands;

import org.example.functionalClasses.CollectionManager;
import org.example.requests.Request;
import org.example.responses.DefaultResponse;
import org.example.responses.Response;

public class Info extends Command {

    /**
     * Команда info. Выводит информацию о коллекции.
     */

    private CollectionManager collectionManager;

    /**
     * Конструктор объекта команды info.
     * @param collectionManager
     */

    public Info(CollectionManager collectionManager) {
        super("info", "Вывести в стандартный поток вывода информацию о коллекции.");
        this.collectionManager = collectionManager;
    }

    /**
     * Метод, запускающий выполнение команды.
     * @param arg
     */

    @Override
    public Response execute(Request request) {
        String[] data = new String[5];
        data[0] = collectionManager.getType().toString();
        data[1] = "<Movie>";
        data[2] = collectionManager.getInitializationDate().toString();
        data[3] = collectionManager.getModificationDate().toString();
        data[4] =  String.valueOf(collectionManager.getCollectionSize());
        return new DefaultResponse("info", data);
    }
}
