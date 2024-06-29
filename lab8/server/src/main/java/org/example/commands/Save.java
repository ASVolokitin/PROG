package org.example.commands;

import org.example.functionalClasses.CollectionManager;
import org.example.movieClasses.Movie;
import org.example.requests.Request;
import org.example.responses.DefaultResponse;

import java.io.FileOutputStream;
import java.io.IOException;

public class Save extends Command {

    /**
     * Команда save. Сохраняет коллекцию в файл.
     */

    private CollectionManager collectionManager;

    /**
     * Конструктор объекта команды save.
     * @param collectionManager
     */

    public Save(CollectionManager collectionManager) {
        super("save", "Сохранить коллекцию в файл.");
        this.collectionManager = collectionManager;
    }

    /**
     * Метод, запускающий выполнение команды.
     * @param request
     */

    @Override
    public DefaultResponse execute(Request request) {
        String xmlString = "";
        xmlString += "<movies>\n";
        for (Movie movie : collectionManager.getCollection()) {
            xmlString += "\t<movie>\n";
            xmlString += "\t\t<name>" + replaceLtgt(movie.getName()) + "</name>\n";
            xmlString += "\t\t<coordinates>\n";
            xmlString += "\t\t\t<x>" + movie.getCoordinates().getX() + "</x>\n";
            xmlString += "\t\t\t<y>" + movie.getCoordinates().getY() + "</y>\n";
            xmlString += "\t\t</coordinates>\n";
            xmlString += "\t\t<oscarsCount>" + movie.getOscarsCount() + "</oscarsCount>\n";
            xmlString += "\t\t<genre>" + (movie.getGenre() == null ? "" : movie.getGenre()) + "</genre>\n";
            xmlString += "\t\t<mpaaRating>" + movie.getMpaaRating() + "</mpaaRating>\n";
            xmlString += "\t\t<screenwriter>\n";
            xmlString += "\t\t\t<name>" + replaceLtgt(movie.getScreenwriter().getName()) + "</name>\n";
            xmlString += "\t\t\t<birthday>" + (movie.getScreenwriter().getBirthday() == null ? "" : movie.getScreenwriter().getBirthday()) + "</birthday>\n";
            xmlString += "\t\t\t<weight>" + movie.getScreenwriter().getWeight() + "</weight>\n";
            xmlString += "\t\t\t<location>\n";
            xmlString += "\t\t\t\t<x>" + movie.getScreenwriter().getLocation().getX() + "</x>\n";
            xmlString += "\t\t\t\t<y>" + movie.getScreenwriter().getLocation().getY() + "</y>\n";
            xmlString += "\t\t\t\t<name>" + replaceLtgt((movie.getScreenwriter().getLocation().getName() == null ? "" : movie.getScreenwriter().getLocation().getName())) + "</name>\n";
            xmlString += "\t\t\t</location>\n";
            xmlString += "\t\t</screenwriter>\n";
            xmlString += "\t</movie>\n";
        }
        xmlString += "</movies>";


        try (FileOutputStream fos = new FileOutputStream("./lab7/apps/server/src/main/java/org/example/sources/output.xml")){
            byte[] buffer = xmlString.getBytes();
            fos.write(buffer, 0, buffer.length);
            System.out.println("Коллекция была успешно сохранена в файл.");

        } catch (IOException e) {
            System.out.println("Возникла ошибка во время записи коллекции в файл");
        }

        // String xmlString = xmlMapper.writeValueAsString()
        return new DefaultResponse("blank");
    }

    private String replaceLtgt(String line) {
        line = line.replaceAll(">", "&gt;");
        line = line.replaceAll("<", "&lt;");
        return line;
    }
}
