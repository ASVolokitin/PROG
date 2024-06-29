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
        data[0] = "Это коллекция типа " + collectionManager.getType() + ";";
        data[1] = "Коллекция хранит элементы типа <Movie>";
        data[2] = "Коллекция была создана в " + collectionManager.getInitializationDate() + ";";
        data[3] = "Коллекция была в последний раз обновлена в " + collectionManager.getModificationDate() + ";";
        data[4] = "Количество элементов в коллекции на данный момент: " + collectionManager.getCollectionSize() + ";";
        return new DefaultResponse("info", data);
    }
}
