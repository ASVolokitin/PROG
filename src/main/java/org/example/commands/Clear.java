package org.example.commands;

import org.example.functionalClasses.CollectionManager;

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
     * @param arg
     */

    public void execute(String arg) {
        collectionManager.clear();
        System.out.println("Коллекция очищена.");
    }
}
