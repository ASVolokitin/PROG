package org.example.commands;

import org.example.functionalClasses.CollectionManager;
import org.example.functionalClasses.CommandList;

import java.util.HashMap;

import static org.example.functionalClasses.CommandList.*;

public class Help extends Command {

    /**
     * Команда help. Выводит список доступных команд и их описание.
     */

    private CollectionManager collectionManager;
    private HashMap<String, String> commandList = new HashMap<String, String>(CommandList.getCommandList());

    /**
     * Конструктор объекта команды help.
     * @param collectionManager
     */

    public Help(CollectionManager collectionManager) {
        super("help", "Вывести справку по доступным командам.");
    }

    /**
     * Метод, запускающий выполнение команды.
     * @param arg
     */

    @Override
    public void execute(String arg) {
        for (var line : commandList.entrySet()) {
            System.out.println(line.getKey() + " - " + line.getValue());
        }
    }
}
