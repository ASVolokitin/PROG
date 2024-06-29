package org.example.commands;

import org.example.functionalClasses.CollectionManager;

public class RemoveHead extends Command {

    /**
     * Команда remove_head. Выводит первый элемент коллекции и удаляет его.
     */

    private CollectionManager collectionManager;

    /**
     * Конструктор объекта команды remove_head.
     * @param collectionManager
     */

    public RemoveHead(CollectionManager collectionManager) {
        super("remove_head", "Вывести первый элемент коллекции и удалить его.");
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
        collectionManager.removeById(collectionManager.getFirst().getId());

    }
}
