package org.example.commands;

import org.example.functionalClasses.CollectionManager;
import org.example.functionalClasses.Reader;
import org.example.functionalClasses.SetValues;
import org.example.functionalClasses.Settings;
import org.example.validators.Validator;
import org.example.validators.ValidatorList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class ReadMovieData extends Command {

    private ArrayList<SetValues> settings = new ArrayList<SetValues>(new Settings().getSettings());
    private HashMap<Integer, Validator> validatorList = new HashMap<>(new ValidatorList().getValidatorList());

    public ReadMovieData() {
        super("(tech) read_from_input_movie_data", "reads movie data from console");
    }

    public void execute() {

    }

    public HashMap<Integer, String> readMovieData() {
        int step = 0;
        HashMap<Integer, String> data = new HashMap<>();
        try {
            while (step < settings.size()) {
                if (Reader.isReadingConsole()) System.out.print(settings.get(step).getComment() + ": ");
                String line = Reader.Read();
                if (validatorList.get(step).validate(line)) {
                    line = line.replaceAll(">", "&gt;");
                    line = line.replaceAll("<", "&lt;");

                    if (line.equals("")) line = null;
                    data.put(step, line);
                    step++;
                } else {
                    System.out.println("Неверный формат ввода.");
                    if (!Reader.isReadingConsole()) {
                        System.out.println(settings.get(step).getComment());
                        System.out.println("Дальнейшее выполнение скрипта невозможно");
                        Reader.finishFile();
                        break;
                    }
                }
            }
        } catch (NumberFormatException e) {
            if (!Reader.isReadingConsole()) {
                System.out.println("Неверный формат значения, дальнейшее выполнение скрипта невозможно");
                Reader.finishFile();
            } else {
                System.out.println("Введите значение в правильном формате: " + settings.get(step).getValueType());
            }
        } catch (IllegalArgumentException e) {
            if (!Reader.isReadingConsole()) {
                System.out.println("Значение не из списка допустимых значений, дальнейшее выполнение скрипта невозможно.");
                Reader.finishFile();
            } else {
                System.out.println("Введите значение из списка допустимых значений.");
            }
        } catch (NoSuchElementException e) {
            System.out.println("В файле не хватает информации об элементе коллекции, дальнейшее выполнение скрипта невозможно.");
            Reader.finishFile();
        }

        return data;
    }
}
