package org.example.controllers;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;
import org.example.Main;
import org.example.functionalClasses.Reader;
import org.example.functionalClasses.RuntimeManager;
import org.example.functionalClasses.TCPClient;
import org.example.requests.AuthorizationRequest;
import org.example.responses.DefaultResponse;
import org.example.responses.Response;

public class AuthController {

    private RuntimeManager runtimeManager;
    private TCPClient client;
    private ResourceBundle bundle;

    public AuthController(RuntimeManager runtimeManager) {
        this.runtimeManager = runtimeManager;
        this.client = runtimeManager.getClient();
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox languageSelector;

    @FXML
    private Text header;

    @FXML
    private Button loginBtn;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registerBtn;

    @FXML
    private Text noAccountLabel;

    @FXML
    private Label wrongPasswordLabel;

    public void initialize() {

        this.bundle = ResourceBundle.getBundle("AUTH_Bundle", Locale.getDefault());
        languageSelector.getItems().addAll("RU", "CZ", "IT", "EN");
        languageSelector.getSelectionModel().select(bundle.getLocale().getCountry());
        setLabels();
    }

    public void setLabels() {
        header.setText(bundle.getString("header"));
        loginField.setPromptText(bundle.getString("loginField"));
        passwordField.setPromptText(bundle.getString("passwordField"));
        loginBtn.setText(bundle.getString("loginBtn"));
        noAccountLabel.setText(bundle.getString("noAccountLabel"));
        registerBtn.setText(bundle.getString("registerBtn"));
        wrongPasswordLabel.setText(bundle.getString("wrongPasswordLabel"));
    }
    @FXML
    void loginBtnOnAction(ActionEvent event) {
        String login = loginField.getText();
        String password = passwordField.getText();
        DefaultResponse response = (DefaultResponse) runtimeManager.executeCommand("auth", new String[]{login, password});
        if (response.getData()[0].equals("true")) {
            client.setLogin(login);
            client.setPassword(password);
            System.out.println("Авторизация прошла успешно");
            Stage stage = (Stage) loginBtn.getScene().getWindow();
            stage.close();
            FXMLLoader loader = null;
            try {
                loader = new FXMLLoader(Class.forName("org.example.Main").getClassLoader().getResource("main.fxml"));
            } catch (ClassNotFoundException e) {
                System.out.println("Ошибка при установке FXML загрузчика: " + e.getMessage());
            }
            Stage primaryStage = new Stage();
            loader.setControllerFactory(param -> new MainController(runtimeManager, primaryStage));
            Parent root = null;
            try {
                root = loader.load();
                primaryStage.setScene(new Scene(root));
                primaryStage.setTitle(bundle.getString("main_header"));
                primaryStage.setResizable(false);
                primaryStage.show();

            } catch (IOException e) {
                System.out.println("Ошибка при попытке отображения главного окна: " + e.getMessage());
            }

        } else {
            wrongPasswordLabel.setOpacity(1);
        }
    }

    @FXML
    void registerBtnOnAction(ActionEvent event) {
        Stage stage = (Stage) loginBtn.getScene().getWindow();
        stage.close();
        FXMLLoader loader = null;
        try {
            loader = new FXMLLoader(Class.forName("org.example.Main").getClassLoader().getResource("registration.fxml"));
        } catch (ClassNotFoundException e) {
            System.out.println("Ошибка при установке FXML загрузчика: " + e.getMessage());
        }
        Stage primaryStage = new Stage();
        loader.setControllerFactory(param -> new RegistrationController(runtimeManager, primaryStage));
        Parent root = null;
        try {
            root = loader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle(bundle.getString("register_header"));
            primaryStage.setResizable(false);
            primaryStage.show();

        } catch (IOException e) {
            System.out.println("Ошибка при попытке отображения окна регистрации: " + e.getMessage());
        }
    }

    @FXML
    void languageSelectorOnAction(ActionEvent event) {
        String s = (String) languageSelector.getValue();
        if (s.equals("RU")) switchLanguage(new Locale("ru", "RU"));
        if (s.equals("IT")) switchLanguage(new Locale("it", "IT"));
        if (s.equals("EN")) switchLanguage(new Locale("en", "EN"));
        if (s.equals("CZ")) switchLanguage(new Locale("cz", "CZ"));
    }

    public void switchLanguage(Locale newLocale) {
        Locale.setDefault(newLocale);
        this.bundle = ResourceBundle.getBundle("AUTH_Bundle", newLocale);
        setLabels();
    }
}
