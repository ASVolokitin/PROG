package org.example.commands;

import org.example.functionalClasses.CollectionManager;

public class Head extends Command {

    /**
     * Команда head. Выводит первый элемент коллекции.
     */

    private CollectionManager collectionManager;

    /**
     * Конструктор объекта команды head.
     * @param collectionManager
     */

    public Head(CollectionManager collectionManager) {
        super("head", "Вывести первый элемент коллекции.");
        this.collectionManager = collectionManager;
    }

    /**
     * Метод, запускающий выполнение команды.
     * @param arg
     */

    @Override
    public void execute(String arg) {
        if (collectionManager.getCollectionSize() == 0) {
            System.out.println("Коллекция пуста.");
            return;
        }
        System.out.println(collectionManager.getFirst());

    }
}
