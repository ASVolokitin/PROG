package org.example.functionalClasses;

import org.example.commands.*;
import org.example.requests.Request;

import java.io.IOException;
import java.util.*;

public class CommandManager {

    /**
     * Класс, управляющий обработкой команд.
     */

    private HashMap<String, Command> commandHashMap = new HashMap<>();
    private static boolean instantexit = false;
    private TCPServer server;
    private CollectionManager collectionManager;


    /**
     * Конструктор менеджера команд, инициализирующий экземпляры всех команд.
     */
    public CommandManager(CollectionManager collectionManager) {
        commandHashMap.put("help", new Help(collectionManager));
        commandHashMap.put("info", new Info(collectionManager));
        commandHashMap.put("show", new Show(collectionManager));
        commandHashMap.put("add", new Add(collectionManager));
//        commandList.put("update_id", new UpdateId(collectionManager));
//        commandList.put("remove_by_id", new RemoveById(collectionManager));
//        commandList.put("clear", new Clear(collectionManager));
        commandHashMap.put("save", new Save(collectionManager));
//        commandList.put("execute_script", new ExecuteScript(collectionManager));
//        commandList.put("exit", new Exit(collectionManager));
//        commandList.put("head", new Head(collectionManager));
//        commandList.put("remove_head", new RemoveHead(collectionManager));
        commandHashMap.put("add_if_max", new AddIfMax(collectionManager));
//        commandList.put("average_of_oscars_count", new AverageOfOscarsCount(collectionManager));
//        commandList.put("min_by_id", new MinById(collectionManager));
//        commandList.put("count_less_than_mpaa_rating", new CountLessThanMpaaRating(collectionManager));
//        commandList.put("read_from_file", new ReadFromFile(collectionManager));
        this.collectionManager = collectionManager;
    }

    public boolean isInstantexit() {
        return instantexit;
    }

    /**
     * Метод, возвращающий словарь всех команд.
     * @return
     */

    public HashMap<String, Command> getCommandMap() {
        return commandHashMap;
    }

    /**
     * Метод, возвращающий команду из списка по её названию.
     * @param name
     * @return
     */

    public Command getCommand(String name) {
        return commandHashMap.get(name);
    }

    /**
     * Метод, запускающий команду по названию (и переданным аргументам).
     */

    public void receiveCommand() {
        try {
            new ReadFromFile(collectionManager).execute(new Request("blank"));
        } catch (IOException e) {
            System.out.println("Не удалось считать содержимое коллекции из файла.");
        }
        this.server.listen();

    }

    /**
     * Метод, открывающий канал взаимодействия пользователя с коллекцией через консоль.
     */

    public void interacting() {
        while (true) {
            try {
                receiveCommand();

            } catch (NoSuchElementException e) {
                System.out.println("Ожидаю команду.");
            }

        }
    }

}

