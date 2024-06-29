package org.example.commands;

import org.example.functionalClasses.CollectionManager;
import org.example.requests.Request;
import org.example.responses.DefaultResponse;
import org.example.responses.Response;

import java.util.HashMap;
import java.util.Map;

public class Help extends Command {

    /**
     * Команда help. Выводит список доступных команд и их описание.
     */

    private CollectionManager collectionManager;

    /**
     * Конструктор объекта команды help.
     * @param collectionManager
     */

    public Help(CollectionManager collectionManager) {
        super("help", "Вывести справку по доступным командам.");
    }

    /**
     * Метод, запускающий выполнение команды.
     * @param request
     */

    @Override
    public Response execute(Request request) {
        HashMap<String, String> commandList = new HashMap<>(); {
            commandList.put("help", "Вывести справку по доступным командам.");
            commandList.put("info", "Вывести в стандартный поток вывода информацию о коллекции.");
            commandList.put("show", "Вывести в стандартный поток вывода все элементы коллекции в строковом представлении.");
            commandList.put("add {element}", "Добавить новый элемент в коллекцию.");
            commandList.put("update id {element}", "Обновить значение элемента коллекции, id которого равен заданному.");
            commandList.put("remove_by_id id", "Удалить элемент из коллекции по его id.");
            commandList.put("clear", "Очистить коллекцию.");
            commandList.put("execute_script file_name", "Считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
            commandList.put("exit", "Завершить программу (без сохранения в файл).");
            commandList.put("head", "Вывести первый элемент коллекции.");
            commandList.put("remove_head", "Вывести первый элемент коллекции и удалить его.");
            commandList.put("add_if_max {element}", "Добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции.");
            commandList.put("average_of_oscars_count", "Вывести среднее значение поля oscarsCount для всех элементов коллекции.");
            commandList.put("min_by_id", "Вывести любой объект из коллекции, значение поля id которого является минимальным.");commandList.put("count_less_than_mpaa_rating mpaaRating", "Вывести количество элементов, значение поля mpaaRating которых меньше заданного.");
        }
        String[] data = new String[commandList.size()];
        int i = 0;
        for (Map.Entry<String, String> x : commandList.entrySet()) {
            data[i] = (x.getKey() + " - " + x.getValue());
            i++;
        }
        return new DefaultResponse("help", data);
    }
}
