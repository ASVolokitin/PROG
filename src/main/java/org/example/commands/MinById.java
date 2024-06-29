package org.example.commands;

import org.example.functionalClasses.CollectionManager;
import org.example.movieClasses.Movie;

public class MinById extends Command {

    /**
     * Команда min_by_id. Выводит объект коллекции с минимальным id.
     */

    private CollectionManager collectionManager;

    /**
     * Конструктор объекта команды min_by_id.
     * @param collectionManager
     */

    public MinById(CollectionManager collectionManager) {
        super("min_by_id", "Вывести любой объект из коллекции, значение поля id которого является минимальным.");
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
        long minId = collectionManager.getLastId();
        for (Movie movie : collectionManager.getCollection()) {
            if (movie.getId() < minId) {
                minId = movie.getId();
            }
        }
        System.out.println(collectionManager.getById(minId));

    }
}
