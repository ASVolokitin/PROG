package org.example.commands;

import org.example.functionalClasses.CollectionManager;
import org.example.functionalClasses.Reader;
import org.example.functionalClasses.SetValues;
import org.example.functionalClasses.Settings;
import org.example.movieClasses.Movie;
import org.example.requests.AddIfMaxRequest;
import org.example.requests.Request;
import org.example.responses.AddIfMaxResponse;
import org.example.validators.Validator;
import org.example.validators.ValidatorList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class AddIfMax extends Command {

    /**
     * Команда add_if_max. Добавляет в коллекцию новый элемент, если его значение (количество премий 'Оскар') превышает максимальное по коллекции.
     */

    private CollectionManager collectionManager;

    /**
     * Конструктор объекта команды AddIfMax.
     *
     * @param collectionManager
     */

    public AddIfMax(CollectionManager collectionManager) {
        super("add_if_max", "Добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции.");
        this.collectionManager = collectionManager;
    }

    /**
     * Метод, запускающий выполнение команды.
     *
     * @param request
     */

    @Override
    public AddIfMaxResponse execute(Request request) {
        AddIfMaxRequest addIfMaxRequest = (AddIfMaxRequest) request;
        Movie movie = new Movie(addIfMaxRequest.getData());
        long oscarsCount = movie.getOscarsCount();
        AddIfMaxResponse response = new AddIfMaxResponse("add_if_max");
        if (collectionManager.getCollectionSize() > 0) {
            int maxOscarsCount = (int) collectionManager.getCollection().stream().max(Comparator.comparing(Movie::getOscarsCount)).get().getOscarsCount();
            if (oscarsCount > maxOscarsCount) {
                response.setResult("Запись нового элемента в коллекцию ...");
                collectionManager.add(new Movie(addIfMaxRequest.getData()), request.getLogin());
                response.setResult("\nФильм %s успешно добавлен в коллекцию.".formatted(movie.getName()));
            } else {
                response.setResult("Фильм не будет добавлен, поскольку количество полученных им премий 'Оскар' не превышает максимальное значение в коллекции.");
            }
            return response;
        } else {
            System.out.println("Коллекция пуста.");
            response.setResult("Запись нового элемента в коллекцию ...");
            collectionManager.add(new Movie(addIfMaxRequest.getData()), request.getLogin());
            response.setResult("\nФильм %s успешно добавлен в коллекцию.".formatted(movie.getName()));
            return response;
        }
    }
}