package org.example.commands;

import org.example.functionalClasses.CollectionManager;
import org.example.movieClasses.Movie;
import org.example.requests.Request;
import org.example.requests.UpdateByIDRequest;
import org.example.responses.DefaultResponse;

public class UpdateByID extends Command {

    private CollectionManager collectionManager;

    public UpdateByID(CollectionManager collectionManager) {
        super("update_by_id", "Обновить поля элемента с заданным ID.");
        this.collectionManager = collectionManager;
    }

    public DefaultResponse execute(Request request) {
        UpdateByIDRequest updateByIDRequest = (UpdateByIDRequest) request;
        long id = updateByIDRequest.getId();
        Movie movie = new Movie(id, updateByIDRequest.getData());
        DefaultResponse response;
        if (collectionManager.getCollectionSize() > 0) {
            if (collectionManager.getById(id) != null) {
                if (collectionManager.getById(id).getLogin().equals(request.getLogin())) {
                    collectionManager.updateById(movie);
                    response = new DefaultResponse("update_by_id", new String[]{"Фильм с id %d успешно обновлен.".formatted(id)});
                }
                else response = new DefaultResponse("update_by_id", new String[]{"Вы не имеете доступа к фильму с id %d.".formatted(id)});
            } else response = new DefaultResponse("update_by_id", new String[]{"Фильма с id %d нет в коллекции.".formatted(id)});
        } else response = new DefaultResponse("update_by_id", new String[]{"Коллекция пуста."});
        return response;
    }
}
