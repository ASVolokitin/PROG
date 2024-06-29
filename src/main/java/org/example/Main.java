package org.example;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.functionalClasses.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.EventHandler;
import java.io.IOException;
import java.util.Scanner;

public class Main extends Application {
    public static void main(String[] args) {
//        TCPClient client = new TCPClient("localhost", 11046);
//        Reader.addStream(new Scanner(System.in));
//        RuntimeManager runtimeManager = new RuntimeManager(client);
//        runtimeManager.run();
        FXMLLoader loader = new FXMLloader();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Button btn = new Button("Hiiii!");
        btn.setActionCommand(new EventHandler() {
            public void handle(ActionEvent event) {
                System.out.println("Hiiiii!");
            }
        });
    }
}