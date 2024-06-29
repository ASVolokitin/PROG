package org.example.commands;

import org.example.functionalClasses.CollectionManager;
import org.example.requests.RemoveByIDRequest;
import org.example.requests.Request;
import org.example.responses.DefaultResponse;

public class RemoveByID extends Command {

    private CollectionManager collectionManager;

    public RemoveByID(CollectionManager collectionManager) {
        super("remove_by_id", "Удалить элемент из коллекции по его ID.");
        this.collectionManager = collectionManager;
    }

    public DefaultResponse execute(Request request) {
        RemoveByIDRequest removeByIDRequest = (RemoveByIDRequest) request;
        long id = removeByIDRequest.getId();
        String res = collectionManager.removeById(id, request.getLogin());
        return new DefaultResponse("remove_by_id",  new String[]{res});
    }
}
