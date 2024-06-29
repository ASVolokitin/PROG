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

public class AddIfMax extends Command {

    /**
     * Команда add_if_max. Добавляет в коллекцию новый элемент, если его значение (количество премий 'Оскар') превышает максимальное по коллекции.
     */

    private CollectionManager collectionManager;

    /**
     * Конструктор объекта команды AddIfMax.
     * @param collectionManager
     */

    public AddIfMax(CollectionManager collectionManager) {
        super("add_if_max {element}", "Добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции.");
        this.collectionManager = collectionManager;
    }

    /**
     *  Метод, запускающий выполнение команды.
     * @param arg
     */

    @Override
    public void execute(String arg) {
        try {
            long oscarsCount = Long.parseLong(arg);
            if (oscarsCount <= 0) throw new NumberFormatException("Количество премий 'Оскар' должно быть целым положительным числом.");
            if (oscarsCount > collectionManager.getMaxOscarsCount()) {
                System.out.println("Запись нового элемента в коллекцию ...");
                HashMap<Integer, String> data = new HashMap<>(new ReadMovieData(collectionManager).readMovieData());
                collectionManager.add(new Movie(collectionManager.incrementId(), data));
            } else {
                System.out.println("Фильм не будет добавлен, поскольку количество полученных им премий 'Оскар' не превышает максимальное значение в коллекции.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Количество премий 'Оскар' должно быть целым положительным числом.");
        }
    }
}
