package org.example;

import org.example.functionalClasses.CommandManager;
import org.example.functionalClasses.Reader;
import org.example.functionalClasses.RuntimeManager;
import org.example.functionalClasses.TCPClient;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TCPClient client = new TCPClient("localhost", 11046);
        Reader.addStream(new Scanner(System.in));
        RuntimeManager runtimeManager = new RuntimeManager(client);
        runtimeManager.run();

    }
}