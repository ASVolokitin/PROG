package org.example.controllers;

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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.functionalClasses.RuntimeManager;
import org.example.functionalClasses.TCPClient;
import org.example.responses.DefaultResponse;

public class RegistrationController {

    private RuntimeManager runtimeManager;
    private TCPClient client;
    private ResourceBundle bundle;
    private Stage primaryStage;

    public RegistrationController(RuntimeManager runtimeManager, Stage primaryStage) {
        this.runtimeManager = runtimeManager;
        this.client = runtimeManager.getClient();
        this.primaryStage = primaryStage;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text header;

    @FXML
    private ComboBox languageSelector;

    @FXML
    private Button loginBtn;

    @FXML
    private TextField loginField;

    @FXML
    private Text haveAccountLabel;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField passwordField2;

    @FXML
    private Button registerBtn;

    @FXML
    private Label wrongPasswordLabel;

    @FXML
    private Label invalidLoginLabel;

    @FXML
    void languageSelectorOnAction(ActionEvent event) {
        String s = (String) languageSelector.getValue();
        if (s.equals("RU")) switchLanguage(new Locale("ru", "RU"));
        if (s.equals("IT")) switchLanguage(new Locale("it", "IT"));
        if (s.equals("EN")) switchLanguage(new Locale("en", "EN"));
        if (s.equals("CZ")) switchLanguage(new Locale("cz", "CZ"));
    }

    @FXML
    void loginBtnOnAction(ActionEvent event) {
        Stage stage = (Stage) loginBtn.getScene().getWindow();
        stage.close();
        FXMLLoader loader = null;
        try {
            loader = new FXMLLoader(Class.forName("org.example.Main").getClassLoader().getResource("authorization.fxml"));
        } catch (ClassNotFoundException e) {
            System.out.println("Ошибка при установке FXML загрузчика: " + e.getMessage());
        }
        Stage primaryStage = new Stage();
        loader.setControllerFactory(param -> new AuthController(runtimeManager));
        Parent root = null;
        try {
            root = loader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle(bundle.getString("auth_header"));
            primaryStage.setResizable(false);
            primaryStage.show();

        } catch (IOException e) {
            System.out.println("Ошибка при попытке отображения окна авторизации: " + e.getMessage());
        }
    }

    @FXML
    void loginFieldOnAction(ActionEvent event) {

    }

    @FXML
    void passwordField2OnAction(ActionEvent event) {

    }

    @FXML
    void passwordFieldOnAction(ActionEvent event) {

    }

    @FXML
    void registerBtnOnAction(ActionEvent event) {
        String login = loginField.getText();
        String password = passwordField.getText();
        String password2 = passwordField2.getText();

        if (password.equals(password2)) {
            if (password.equals("")) {
                invalidLoginLabel.setOpacity(0);
                wrongPasswordLabel.setText(bundle.getString("not_null_password"));
                wrongPasswordLabel.setOpacity(1);
            } else if (login.equals("")){
                wrongPasswordLabel.setOpacity(0);
                invalidLoginLabel.setText(bundle.getString("not_null_login"));
                invalidLoginLabel.setOpacity(1);
            }
            else {
                wrongPasswordLabel.setOpacity(0);
                invalidLoginLabel.setOpacity(0);
                DefaultResponse response = (DefaultResponse) runtimeManager.executeCommand("register", new String[]{login, password});
                if (response.getData()[0].equals("false")) {
                    invalidLoginLabel.setText(bundle.getString("invalid_username"));
                    invalidLoginLabel.setOpacity(1);
                } else {
                    invalidLoginLabel.setOpacity(0);
                    client.setLogin(login);
                    client.setPassword(password);
                    System.out.println("Регистрация прошла успешно, авторизация автоматически.");
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
                }
            }

        } else {
            wrongPasswordLabel.setText(bundle.getString("password_mismatch"));
            wrongPasswordLabel.setOpacity(1);
        }
    }

    @FXML
    void initialize() {
        this.bundle = ResourceBundle.getBundle("REG_Bundle", Locale.getDefault());
        languageSelector.getItems().addAll("RU", "CZ", "IT", "EN");
        languageSelector.getSelectionModel().select(bundle.getLocale().getCountry());
        setLabels();
    }

    public void switchLanguage(Locale newLocale) {
        Locale.setDefault(newLocale);
        this.bundle = ResourceBundle.getBundle("REG_Bundle", newLocale);
        setLabels();
    }

    public void setLabels() {
        header.setText(bundle.getString("header"));
        loginField.setPromptText(bundle.getString("login_prompt"));
        passwordField.setPromptText(bundle.getString("password_prompt"));
        passwordField2.setPromptText(bundle.getString("repeat_password_prompt"));
        registerBtn.setText(bundle.getString("register_btn"));
        haveAccountLabel.setText(bundle.getString("have_an_account"));
        loginBtn.setText(bundle.getString("login_btn"));
        wrongPasswordLabel.setText(bundle.getString("password_mismatch"));
        invalidLoginLabel.setText(bundle.getString("invalid_username"));

    }

}
