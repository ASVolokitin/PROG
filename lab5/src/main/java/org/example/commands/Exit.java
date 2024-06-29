package org.example.commands;

import org.example.functionalClasses.CollectionManager;
import org.example.functionalClasses.CommandManager;
import org.example.functionalClasses.Reader;

public class Exit extends Command {

    /**
     * Команда exit. Завершает выполнение программы.
     */

    private CollectionManager collectionManager;

    /**
     * Конструктор объекта команды exit.
     * @param collectionManager
     */

    public Exit(CollectionManager collectionManager) {
        super("exit", "Завершить программу (без сохранения в файл).");
        this.collectionManager = collectionManager;
    }

    /**
     * Метод, запускающий выполнение команды.
     * @param arg
     */

    @Override
    public void execute(String arg) {
        while (true) {
            System.out.print("Вы действительно хотите завершить программу? (y/n): ");
            switch (Reader.Read()) {
                case "y" -> System.exit(0);
                case "n" -> {
                    return;
                }
            }
            TmpSave tmpSave = new TmpSave(collectionManager);
            tmpSave.removeFile();
        }
    }
}
