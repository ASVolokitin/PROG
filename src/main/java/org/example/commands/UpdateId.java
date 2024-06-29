package org.example.commands;

import org.example.functionalClasses.CollectionManager;
import org.example.functionalClasses.Reader;
import org.example.functionalClasses.SetValues;
import org.example.functionalClasses.Settings;
import org.example.movieClasses.Movie;
import org.example.validators.Validator;
import org.example.validators.ValidatorList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class UpdateId extends Command {

    /**
     * Команда update_by_id. Обновляет поля элемента по заданному id.
     */

    private CollectionManager collectionManager;
    private ArrayList<SetValues> settings = new ArrayList<SetValues>(new Settings().getSettings());
    private HashMap<Integer, Validator> validatorList = new HashMap<>(new ValidatorList().getValidatorList());

    /**
     * Конструктор объекта команды update_id.
     * @param collectionManager
     */

    public UpdateId(CollectionManager collectionManager) {
        super("update_id {element}", "Обновить значение элемента коллекции, id которого равен заданному.");
        this.collectionManager = collectionManager;
    }

    /**
     * Метод, запускающий выполнение команды.
     * @param arg
     */

    @Override
    public void execute(String arg) {
        try {
            long id = Long.parseLong(arg);
            if (id <= 0) throw new NumberFormatException("ID должен быть положительным целым числом.");
            for (Movie movie : collectionManager.getCollection()) {
                if (movie.getId() == id) {
                    collectionManager.removeById(id);
                    System.out.println("Обновление полей элемента ...");
                    collectionManager.add(new Movie(id, new ReadMovieData(collectionManager).readMovieData()));
                    return;
                }
            }
            System.out.println("В коллекции нет фильма с заданным ID.");
            if (!Reader.isReadingConsole()) {
                System.out.println("Дальнейшее выполнение скрипта невозможно.");
                Reader.finishFile();
                Reader.setReadingConsole(true);
            }
        } catch (NumberFormatException e) {
            System.out.println("ID должен быть положительным целым числом.");
            if (!Reader.isReadingConsole()) {
                System.out.println("Дальнейшее выполнение скрипта невозможно.");
                Reader.finishFile();
                Reader.setReadingConsole(true);
            }
        }
    }
}
