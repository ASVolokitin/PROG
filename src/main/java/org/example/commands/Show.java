package org.example.commands;

import org.example.functionalClasses.CollectionManager;
import org.example.movieClasses.Movie;

public class Show extends Command {

    /**
     * Команда show. Выводит содержимое коллекции.
     */

    private CollectionManager collectionManager;

    /**
     * Конструктор объекта команды show.
     * @param collectionManager
     */

    public Show(CollectionManager collectionManager) {

        super("show", "Вывести в стандартный поток вывода все элементы коллекции в строковом представлении.");
        this.collectionManager = collectionManager;
    }

    /**
     * Метод, запускающий выполнение команды.
     * @param arg
     */

    @Override
    public void execute(String arg) {
        boolean cnt = false;
        for (Movie movie : collectionManager.getSortedCollection()) {
            String line = movie.toString();
            line = line.replaceAll("&gt;", ">");
            line = line.replaceAll("&lt;", "<");
            System.out.println(line);
            cnt = true;
        }
        if (!cnt) System.out.println("Коллекция пуста.");

    }
}
