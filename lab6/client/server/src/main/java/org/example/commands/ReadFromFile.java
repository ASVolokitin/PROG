package org.example.commands;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.functionalClasses.CollectionManager;
import org.example.functionalClasses.Reader;
import org.example.functionalClasses.SetValues;
import org.example.functionalClasses.Settings;
import org.example.movieClasses.Movie;
import org.example.validators.Validator;
import org.example.validators.ValidatorList;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class ReadFromFile extends Command {

    /**
     * Команда read_from_file. Наполняет коллекцию данными из файла.
     */

    private CollectionManager collectionManager;
    private ArrayList<SetValues> settings = new ArrayList<SetValues>(new Settings().getSettings());
    private HashMap<Integer, Validator> validatorList = new HashMap<>(new ValidatorList().getValidatorList());

    /**
     * Конструктор объекта команды read_from_file.
     * @param collectionManager
     */

    public ReadFromFile(CollectionManager collectionManager) {
        super("read_from_file", "Заполняет коллекцию фильмами из xml файла.");
        this.collectionManager = collectionManager;
    }

    /**
     * Метод, запускающий выполнение команды.
     * @param arg
     */

    public void execute(String arg) {
        HashMap<Integer, String> data = readFromFileMovieData(arg);
    }

    public HashMap<Integer, String> readFromFileMovieData(String arg) {
        HashMap<Integer, String> data = new HashMap<>();
        try {
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.registerModule(new JavaTimeModule());
            System.out.println(System.getProperty("user.dir"));
            Scanner scanner = new Scanner(Paths.get("./servclient_lab6/apps/server/src/main/java/org/example/sources/output.xml"));
            String readContent = "";
            while (scanner.hasNextLine()) readContent += scanner.nextLine();
            List<Movie> movies = xmlMapper.readValue(readContent, new TypeReference<List<Movie>>(){});
            for (Movie movie : movies) {
                System.out.println("Запись нового элемента в коллекцию ...");
                data.put(0, String.valueOf(movie.getName()));
                data.put(1, String.valueOf(movie.getCoordinates().getX()));
                data.put(2, String.valueOf(movie.getCoordinates().getY()));
                data.put(3, String.valueOf(movie.getOscarsCount()));
                data.put(4, String.valueOf(movie.getGenre()));
                data.put(5, String.valueOf(movie.getMpaaRating()));
                data.put(6, String.valueOf(movie.getScreenwriter().getName()));
                data.put(7, String.valueOf(movie.getScreenwriter().getBirthday()));
                data.put(8, String.valueOf(movie.getScreenwriter().getWeight()));
                data.put(9, String.valueOf(movie.getScreenwriter().getLocation().getX()));
                data.put(10, String.valueOf(movie.getScreenwriter().getLocation().getY()));
                data.put(11, movie.getScreenwriter().getLocation().getName());
                if (validateData(data)) addMovie(data);
                else System.out.println("Неверный формат входных данных.");
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла. Проверьте правильность названия файла, его расположения (директория sources), и правильность формата вводимых данных.");
        }
        return data;
    }

    public boolean validateData(HashMap<Integer, String> data) {
        int step = 0;
        while (step < settings.size()) {
            if (!validatorList.get(step).validate(data.get(step))) {
                System.out.println(settings.get(step).getComment());
                return false;
            }
            step++;
        }
        return true;
    }

    public void addMovie(HashMap<Integer, String> data) {
        HashMap<Integer, String> stringData = new HashMap<>();
        int step = 0;
        for (Object o : data.values()) {
            stringData.put(step, String.valueOf(o));
            step++;
        }
        collectionManager.add(new Movie(collectionManager.incrementId(), stringData));
    }
}
