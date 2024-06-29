package org.example.functionalClasses;

import javafx.application.Application;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.EventHandler;

public class TestApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setLabel("Say something");
//        btn.setActionCommand(new EventHandler<ActionEvent>() {
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });
    }

    public static void main(String[] args) {
        launch(args);
    }

}
