package org.example.commands;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.functionalClasses.CollectionManager;
import org.example.functionalClasses.Reader;
import org.example.functionalClasses.SetValues;
import org.example.functionalClasses.Settings;
import org.example.movieClasses.Movie;
import org.example.validators.Validator;
import org.example.validators.ValidatorList;

import java.io.EOFException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

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
        super("add {element}", "Добавить новый элемент в коллекцию.");
        this.collectionManager = collectionManager;
    }

    /**
     * Метод, запускающий выполнение команды.
     * @param arg
     */

    @Override
    public void execute(String arg) {
        System.out.println("Запись нового элемента в коллекцию ...");
        HashMap<Integer, String> data = new HashMap<>(new ReadMovieData(collectionManager).readMovieData());
        collectionManager.add(new Movie(collectionManager.incrementId(), data));
    }
}
