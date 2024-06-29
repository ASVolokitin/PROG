package org.example.commands;

import org.apache.commons.codec.digest.DigestUtils;
import org.example.functionalClasses.Reader;
import org.example.functionalClasses.TCPClient;
import org.example.requests.RegistrationRequest;
import org.example.responses.DefaultResponse;

import java.io.IOException;

public class Registration extends Command {

    private TCPClient client;

    public Registration(TCPClient client) {
        super("registration", "Регистрация нового пользователя.");
        this.client = client;
    }

    public void execute(String arg) throws IOException {
        System.out.println("Запущен процесс регистрации ...");
        System.out.print("Укажите ваш логин: ");
        String login = receiveNotBlank();
        System.out.print("Укажите ваш пароль: ");
        String password1 = receiveNotBlank();
        System.out.print("Введите ваш пароль ещё раз: ");
        String password2 = receiveNotBlank();

        while (!(password1.equals(password2))) {
            System.out.println("Пароли не совпадают.");
            System.out.print("Введите ваш пароль ещё раз: ");
            password2 = receiveNotBlank();
        }

        DefaultResponse response = (DefaultResponse) client.send(new RegistrationRequest(login, DigestUtils.md5Hex(password1)));
        if (response.getData()[0].equals("true")) {
            System.out.println("\nРегистрация прошла успешно.");
            System.out.println("Вход в систему произведён автоматически.");
            client.setLogin(login);
            client.setPassword(password1);
            return;
        } else {
            System.out.println("Такой логин уже занят, попробуйте ещё раз.");
            System.exit(0);
        }

    }

    public String receiveNotBlank() {
        String field = Reader.Read().strip();
        while (field.isBlank()) {
            System.out.print("Поле не может быть пустым! Введите значение ещё раз: ");
            field = Reader.Read().strip();
        }
        return field;
    }
}
