package org.example.functionalClasses;

import org.example.commands.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class CommandManager {

    /**
     * Класс, управляющий обработкой команд.
     */

    private HashMap<String, Command> commandHashMap = new HashMap<>();
    private static boolean instantexit = false;
    private TCPClient client;


    /**
     * Конструктор менеджера команд, инициализирующий экземпляры всех команд.
     */

    public boolean isInstantexit() {
        return instantexit;
    }

    /**
     * Метод, добавляющий в список новую команду.
     * @param command
     */

    public void addCommand(Command command) {
        commandHashMap.put(command.getName(), command);
    }

    /**
     * Метод, возвращающий словарь всех команд.
     * @return
     */

    public HashMap<String, Command> getCommandMap() {
        return commandHashMap;
    }

    /**
     * Метод, возвращающий список всех команд.
     * @return
     */

    public List<Command> getCommandHashMap() {
        return new ArrayList<>(commandHashMap.values());
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
     * Метод, возвращающий коллекцию, которая обрабатывается командами.
     * @return
     */

    /**
     * Метод, запускающий команду по названию (и переданным аргументам).
     */

    public void receiveCommand() {
        try {
            System.out.print("\nВведите команду (help, чтобы увидеть полный список команд): ");
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine().trim() + " void";
            String cmd = line.split(" ")[0];
            commandHashMap.get(cmd).execute((line.split(" ")[1]));

        } catch (FileNotFoundException e) {
            System.out.println("Не удалось найти файл скрипта, проверьте правильность названия.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Введите аргумент команды");
        } catch (NullPointerException e) {
            System.out.println("Такая команда не определена.");
        } catch (IOException e) {
            System.out.println("Ошибка выполнения команды.");
        }
    }

    /**
     * Метод, открывающий канал взаимодействия пользователя с коллекцией через консоль.
     */

    public void interacting() {
        System.out.print("Здравствуйте! ");
        while (true) {
            System.out.println("Введите /reg для регистрации или /login для входа в систему.");
            String cmd = Reader.Read();
            try {
                if (cmd.equals("/reg")){
                    commandHashMap.get("registration").execute("");
                    break;
                }
                else if (cmd.equals("/login")) {
                    commandHashMap.get("authorization").execute("");
                    break;
                }
            } catch (IOException e) {
                System.out.println("Не удалось выполнить авторизацию: " + e.getMessage());
            }
        }
        while (true) {
            try {
                receiveCommand();
            } catch (NoSuchElementException e) {
                System.out.println("Ожидаю команду.");
            } catch (ArrayIndexOutOfBoundsException ignored) {
            } catch (NullPointerException e) {
                System.out.println("Такая команда не определена.");
            }

        }
    }

}

