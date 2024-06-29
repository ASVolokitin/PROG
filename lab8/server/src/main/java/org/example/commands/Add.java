package org.example.commands;

import org.example.functionalClasses.CollectionManager;
import org.example.movieClasses.Movie;
import org.example.requests.AddRequest;
import org.example.requests.Request;
import org.example.responses.AddResponse;

public class Add extends Command {

    /**
     * Команда add. Добавляет в коллекцию новый элемент.
     */

    private CollectionManager collectionManager;

    /**
     * Конструктор объекта команды Add.
     * @param collectionManager
     */

    public Add(CollectionManager collectionManager) {
        super("add", "Добавить новый элемент в коллекцию.");
        this.collectionManager = collectionManager;
    }

    /**
     * Метод, запускающий выполнение команды.
     * @param request
     */

    @Override
    public AddResponse execute(Request request) {
        System.out.println("Запись нового элемента в коллекцию ...");
        AddRequest addRequest = (AddRequest) request;
        Movie movie = new Movie(addRequest.getData());
        collectionManager.add(movie, request.getLogin());
        return new AddResponse(movie.getName());
    }
}
