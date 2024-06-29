package org.example.commands;

import org.example.functionalClasses.CollectionManager;

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
    public void execute(String arg) {
        System.out.println("Это коллекция типа " + collectionManager.getType() + ";");
        System.out.println("Коллекция хранит элементы типа <Movie>");
        System.out.println("Коллекция была создана в " + collectionManager.getInitializationDate() + ";");
        System.out.println("Коллекция была в последний раз обновлена в " + collectionManager.getModificationDate() + ";");
        System.out.println("Количество элементов в коллекции на данный момент: " + collectionManager.getCollectionSize() + ";");
    }
}
