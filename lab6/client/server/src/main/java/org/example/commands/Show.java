package org.example.commands;

import org.example.functionalClasses.CollectionManager;
import org.example.movieClasses.Movie;
import org.example.requests.Request;
import org.example.responses.DefaultResponse;
import org.example.responses.Response;

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
     * @param request
     */

    @Override
    public Response execute(Request request) {
        String[] data = new String[collectionManager.getCollectionSize()];
        String res = "";
        boolean cnt = false;
        int i = 0;
        for (Movie movie : collectionManager.getSortedCollection()) {
            String line = movie.toString();
            line = line.replaceAll("&gt;", ">");
            line = line.replaceAll("&lt;", "<");
            res += line;
            data[i++] = line;
            cnt = true;
        }
        if (!cnt) {
            data = new String[1];
            data[0] = "Коллекция пуста.";
        }
        return new DefaultResponse("show", data);

    }
}
