package org.example.commands;

import org.example.interfaces.Executable;

import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class Command implements Executable {

    /**
     * Абстрактный класс команды. Определяет конструктор всех команд и исполнительный метод execute().
     */

    private String name;
    private String description;

    /**
     * Базовый конструктор объекта команды.
     * @param name
     * @param description
     */

    public Command(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Метод, запускающий выполнение команды.
     * @param arg
     */
    public void execute(String arg) throws FileNotFoundException, IOException{}

    /**
     * Метод, возвращающий название команды.
     * @return
     */

    public String getName() {
        return name;
    }

    /**
     * Метод, возвращающий описание команды.
     * @return
     */

    public String getDescription() {
        return description;
    }

    /**
     * Метод, возвращающий строковое представление команды.
     * @return
     */

    @Override
    public String toString() {
        return "Command(name=%s, description=%s)".formatted(name, description);
    }
}
