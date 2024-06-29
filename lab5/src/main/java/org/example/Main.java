package org.example;

import org.example.commands.Command;
import org.example.commands.ReadFromFile;
import org.example.functionalClasses.CommandManager;
import org.example.functionalClasses.Reader;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    /**
     * Исполняемый класс.
     * @param args
     */

    /**
     * Метод, запускаемый первым.
     * @param args
     */

    public static void main(String[] args) {
        CommandManager commandManager = new CommandManager();
        Command readFromFile = new ReadFromFile(commandManager.getCollectionManager());
        Reader.addStream(new Scanner(System.in));
        try {
            Path path = Paths.get("./src/main/java/org/example/sources/tmp.xml");
            if (Files.exists(path)) {
                while (true) {
                    System.out.print("Есть несохраненная версия коллекции, восставновить? (y/n): ");
                    String cmd = Reader.Read();
                    if (cmd.equals("y")) {
                        commandManager.getCommand("read_from_file").execute("tmp.xml");
                        break;
                    }
                    else if (cmd.equals("n")) {
                        commandManager.getCommand("read_from_file").execute(args[0]);
                        break;
                    }
                }
            } else {
                commandManager.getCommand("read_from_file").execute(args[0]);
            }
            // Scanner scanner = new Scanner(Paths.get("./src/main/java/org/example/sources/" + args[0]));

        } catch (FileNotFoundException ignored) {
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Укажите название файла, из которого необходимо импортировать коллекцию (по умолчанию input.xml).");
            System.exit(0);
        } catch (NoSuchElementException e) {
            System.out.println("Ожидаю команду.");
        }

        commandManager.interacting();
    }

}