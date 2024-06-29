package org.example.commands;

import org.example.functionalClasses.CollectionManager;
import org.example.movieClasses.Movie;
import org.example.requests.Request;
import org.example.responses.DefaultResponse;
import org.example.responses.Response;
import org.example.responses.ShowResponse;

import java.util.HashMap;
import java.util.LinkedList;

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

//    @Override
//    public Response execute(Request request) {
//        String[] data = new String[collectionManager.getCollectionSize()];
//        String res = "";
//        boolean cnt = false;
//        int i = 0;
//        for (Movie movie : collectionManager.getCollection()) {
//            String line = movie.toString();
//            line = line.replaceAll("&gt;", ">");
//            line = line.replaceAll("&lt;", "<");
//            res += line;
//            data[i++] = line;
//            cnt = true;
//        }
//        if (!cnt) {
//            data = new String[1];
//            data[0] = "Коллекция пуста.";
//        }
//        return new DefaultResponse("show", data);
//    }

    @Override
    public Response execute(Request request) {
        LinkedList<HashMap<Integer, String>> data = new LinkedList<>();
        LinkedList<Movie> collection = collectionManager.getCollection();
        for (int i = 0; i < collectionManager.getCollectionSize(); ++i) {
            HashMap<Integer, String> movie = new HashMap<>();
            movie.put(0, collection.get(i).getId().toString());
            movie.put(1, collection.get(i).getName().toString());
            movie.put(2, collection.get(i).getCoordinates().getX().toString());
            movie.put(3, collection.get(i).getCoordinates().getY().toString());
            movie.put(4, collection.get(i).getCreationDate().toString());
            movie.put(5, String.valueOf(collection.get(i).getOscarsCount()));
            movie.put(6, String.valueOf(collection.get(i).getGenre() == null ? null : collection.get(i).getGenre().toString()));
            movie.put(7, collection.get(i).getMpaaRating().toString());
            movie.put(8, collection.get(i).getScreenwriter().getName().toString());
            movie.put(9, collection.get(i).getScreenwriter().getBirthday() == null ? null : collection.get(i).getScreenwriter().getBirthday().toString());
            movie.put(10, collection.get(i).getScreenwriter().getWeight().toString());
            movie.put(11, collection.get(i).getScreenwriter().getLocation().getX().toString());
            movie.put(12, collection.get(i).getScreenwriter().getLocation().getY().toString());
            movie.put(13, collection.get(i).getScreenwriter().getLocation().getName() == null ? null : collection.get(i).getScreenwriter().getLocation().getName().toString());
            movie.put(14, collection.get(i).getLogin().toString());
            data.add(movie);
        }

        return new ShowResponse("show", data);
    }
}
