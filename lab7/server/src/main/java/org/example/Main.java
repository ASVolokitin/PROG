package org.example;
import org.example.functionalClasses.Reader;
import org.example.functionalClasses.RuntimeManager;
import java.io.IOException;
import java.util.Scanner;

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
        try {
            RuntimeManager runtimeManager = new RuntimeManager();
            Reader.addStream(new Scanner(System.in));
            runtimeManager.run();
        } catch (IOException e) {
            System.out.println("Не удалось запустить сервер.");
        }
    }
}