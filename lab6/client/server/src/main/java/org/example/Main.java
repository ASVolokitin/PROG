package org.example;

;
import org.example.functionalClasses.CommandManager;
import org.example.functionalClasses.Reader;
import org.example.functionalClasses.RuntimeManager;
import org.example.functionalClasses.TCPServer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import java.net.*;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

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