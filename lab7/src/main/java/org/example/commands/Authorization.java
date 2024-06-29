package org.example.commands;

import org.apache.commons.codec.cli.Digest;
import org.apache.commons.codec.digest.DigestUtils;
import org.example.functionalClasses.Reader;
import org.example.functionalClasses.TCPClient;
import org.example.requests.AuthorizationRequest;
import org.example.responses.DefaultResponse;

import java.io.IOException;

public class Authorization extends Command {
    private TCPClient client;

    public Authorization(TCPClient client) {
        super("authorization", "Авторизация пользователя.");
        this.client = client;
    }

    public void execute(String arg) throws IOException {
        int retries = 0;
        while (retries < 5) {
            System.out.print("Укажите ваш логин: ");
            String login = Reader.Read();
            System.out.print("Укажите ваш пароль: ");
            String password = DigestUtils.md5Hex(Reader.Read().getBytes());
            DefaultResponse response = (DefaultResponse) client.send(new AuthorizationRequest(login, password));
            if (response.getData()[0].equals("true")) {
                System.out.println("\nАвторизация прошла успешно.");
                client.setLogin(login);
                client.setPassword(password);
                return;
            } else {
                System.out.println("\nЛогин или пароль введён неверно, попробуйте ещё раз.");
                retries++;
            }
        }
        System.out.println("Превышено количество попыток авторизации, по вопросам восстановления данных для авторизации обращайтесь к администратору.");
        System.exit(0);
    }
}
