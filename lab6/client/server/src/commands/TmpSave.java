package org.example.commands;

import org.example.functionalClasses.CollectionManager;
import org.example.movieClasses.Movie;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class TmpSave extends Command {
    private CollectionManager collectionManager;
    private File tmp = new File("./src/main/java/org/example/sources/tmp.xml");

    /**
     * Конструктор объекта команды save.
     * @param collectionManager
     */

    public TmpSave(CollectionManager collectionManager) {
        super("tmpsave", "Сохранить коллекцию во временный файл.");
        this.collectionManager = collectionManager;
    }

    public void execute(String arg) {
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

        try (FileOutputStream fos = new FileOutputStream(tmp)){
            byte[] buffer = xmlString.getBytes();
            fos.write(buffer, 0, buffer.length);
        } catch (IOException e) {
        }

        // String xmlString = xmlMapper.writeValueAsString()

    }

    public void removeFile() {
        tmp.delete();
    }

    private String replaceLtgt(String line) {
        line = line.replaceAll(">", "&gt;");
        line = line.replaceAll("<", "&lt;");
        return line;
    }
}
