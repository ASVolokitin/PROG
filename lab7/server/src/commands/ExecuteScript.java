package org.example.commands;

import org.example.functionalClasses.CollectionManager;
import org.example.functionalClasses.CommandList;
import org.example.functionalClasses.CommandManager;
import org.example.functionalClasses.Reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.NotLinkException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

public class ExecuteScript extends Command {

    /**
     * Команда execute_script. Исполняет программу, переданную в виде текстового файла.
     */

    private CollectionManager collectionManager;
    private HashMap<String, Command> commandList = new HashMap<>();

    /**
     * Конструктор объекта команды execute_script.
     *
     * @param collectionManager
     */

    public ExecuteScript(CollectionManager collectionManager) {
        super("execute_script file_name", "Считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        this.collectionManager = collectionManager;
    }

    /**
     * Метод, запускающий выполнение команды.
     *
     * @param arg
     */

    @Override
    public void execute(String arg) {
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

        try {
            Scanner f = new Scanner(Paths.get("./src/main/java/org/example/sources/" + arg.split(" ")[0]));
            Reader.addStream(f);
            while (Reader.fileHasNextLine()) {
                String line = Reader.Read();
                if (Reader.getRecursionDepth() > 100) {
                    System.out.println("В скрипте обнаружена рекурсия, уберите.");
                    Reader.backToConsole();
                    CommandManager commandManager = new CommandManager();
                    commandManager.interacting();
                    return;
                }
                else {
                    receiveCommand(line.strip());
                }
            }
            Reader.popStream();
        } catch (IOException e) {
            System.out.println("Не удалось открыть указанный файл.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Не указано название файла скрипта (по умолчанию script.txt).\n");
            Reader.backToConsole();
            System.out.print("\nВведите команду (help, чтобы увидеть полный список команд): ");
        } catch (NoSuchElementException e) {
            System.out.println("Скрипт некорректный, выполнение приостановлено.\n");
            Reader.backToConsole();
        }

    }

    public void receiveCommand(String line) {
        try {
            String cmd = line.split(" ")[0].strip();
            commandList.get(cmd).execute((line + " AHAHA").split(" ")[1]);
        } catch (FileNotFoundException e) {
            System.out.println("Не удалось найти файл скрипта.");
        }
    }
}
