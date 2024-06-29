package org.example;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.example.controllers.AuthController;
import org.example.functionalClasses.RuntimeManager;
import org.example.functionalClasses.TCPClient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class AppHandler {

    private TCPClient client;
    private Stage primaryStage;
    private RuntimeManager runtimeManager;
    private ResourceBundle bundle;

    public AppHandler(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.bundle = ResourceBundle.getBundle("AUTH_Bundle", Locale.getDefault());
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("./resources/config.properties"));
            this.client = new TCPClient("localhost", Integer.valueOf(properties.getProperty("port")));
            this.runtimeManager = new RuntimeManager(client);
        } catch (IOException e) {
            System.out.println("Ошибка при чтении properties " + e.getMessage());
        }


    }

    public void runApp() {
        showAuthorization();
    }

    public void showAuthorization() {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("authorization.fxml"));
        loader.setControllerFactory(param -> new AuthController(runtimeManager));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle(bundle.getString("header"));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void showRegistration() {

    }

    public void showMain() {

    }
}
