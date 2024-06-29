package org.example.functionalClasses;

import org.example.commands.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class CommandManager {

    /**
     * Класс, управляющий обработкой команд.
     */

    private HashMap<String, Command> commandList = new HashMap<>();
    private static CollectionManager collectionManager = new CollectionManager();
    private static boolean instantexit = false;

    /**
     * Конструктор менеджера команд, инициализирующий экземпляры всех команд.
     */
    public CommandManager() {
        commandList.put("help", new Help(collectionManager));
        commandList.put("info", new Info(collectionManager));
        commandList.put("show", new Show(collectionManager));
        commandList.put("add", new Add(collectionManager));
        commandList.put("update_id", new UpdateId(collectionManager));
        commandList.put("remove_by_id", new RemoveById(collectionManager));
        commandList.put("clear", new Clear(collectionManager));
        commandList.put("save", new Save(collectionManager));
        commandList.put("execute_script", new ExecuteScript(collectionManager));
        commandList.put("exit", new Exit(collectionManager));
        commandList.put("head", new Head(collectionManager));
        commandList.put("remove_head", new RemoveHead(collectionManager));
        commandList.put("add_if_max", new AddIfMax(collectionManager));
        commandList.put("average_of_oscars_count", new AverageOfOscarsCount(collectionManager));
        commandList.put("min_by_id", new MinById(collectionManager));
        commandList.put("count_less_than_mpaa_rating", new CountLessThanMpaaRating(collectionManager));
        commandList.put("read_from_file", new ReadFromFile(collectionManager));
    }

    public boolean isInstantexit() {
        return instantexit;
    }

    /**
     * Метод, добавляющий в список новую команду.
     * @param command
     */

    public void addCommand(Command command) {
        commandList.put(command.getName(), command);
    }

    /**
     * Метод, возвращающий словарь всех команд.
     * @return
     */

    public HashMap<String, Command> getCommandMap() {
        return commandList;
    }

    /**
     * Метод, возвращающий список всех команд.
     * @return
     */

    public List<Command> getCommandList() {
        return new ArrayList<>(commandList.values());
    }

    /**
     * Метод, возвращающий команду из списка по её названию.
     * @param name
     * @return
     */

    public Command getCommand(String name) {
        return commandList.get(name);
    }

    /**
     * Метод, возвращающий коллекцию, которая обрабатывается командами.
     * @return
     */

    public CollectionManager getCollectionManager() {
        return collectionManager;
    }

    /**
     * Метод, запускающий команду по названию (и переданным аргументам).
     */

    public void receiveCommand() {
        TmpSave tmpsave = new TmpSave(collectionManager);
        try {
            System.out.print("\nВведите команду (help, чтобы увидеть полный список команд): ");
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            String cmd = line.split(" ")[0].strip();
            if (cmd.equals("save") || cmd.equals("exit")) {
                tmpsave.removeFile();
            } else {
                commandList.get(cmd).execute((line + " AHAHA").split(" ")[1]);
                tmpsave.execute("");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Не удалось найти файл скрипта, проверьте правильность названия.");
        }
    }

    /**
     * Метод, открывающий канал взаимодействия пользователя с коллекцией через консоль.
     */

    public void interacting() {
        while (true) {
            try {
                receiveCommand();
            } catch (NullPointerException e) {
                System.out.println("Такая команда не определена.");
            } catch (NoSuchElementException e) {
                System.out.println("Ожидаю команду.");
            }

        }
    }

}

