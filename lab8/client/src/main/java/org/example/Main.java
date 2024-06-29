package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;

public class Main extends Application {
    public void start(Stage primaryStage) {
        AppHandler appHandler = new AppHandler(primaryStage);
        appHandler.runApp();
    }

    public static void main(String[] args) {
        launch(args);
    }
}