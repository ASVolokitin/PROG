package org.example.commands;

import org.example.functionalClasses.CollectionManager;
import org.example.movieClasses.Movie;

public class RemoveById extends Command {

    /**
     * Команда remove_by_id. Удаляет элемент из коллекции по его id.
     */

    private CollectionManager collectionManager;

    /**
     * Конструктор объекта команды remove_by_id.
     * @param collectionManager
     */

    public RemoveById(CollectionManager collectionManager) {
        super("remove_by_id id", "Удалить элемент из коллекции по его id.");
        this.collectionManager = collectionManager;
    }

    /**
     * Метод, запускающий выполнение команды.
     * @param arg
     */


    public void execute(String arg) {
        try {
            long id = Long.parseLong(arg);
            if (id <= 0) throw new NumberFormatException("ID должен быть положительным целым числом.");
            for (Movie movie : collectionManager.getCollection()) {
                if (movie.getId() == id) {
                    collectionManager.removeById(id);
                    System.out.println("Фильм был успешно удалён.");
                    return;
                }
            }
            System.out.println("В коллекции нет фильма с таким ID.");
        } catch (NumberFormatException e) {
            System.out.println("ID должен быть положительным целым числом.");
        }


    }
}
