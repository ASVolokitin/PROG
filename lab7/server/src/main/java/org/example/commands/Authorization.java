package org.example.commands;

import org.example.functionalClasses.CollectionManager;
import org.example.functionalClasses.DBUtils;
import org.example.functionalClasses.TCPServer;
import org.example.requests.AuthorizationRequest;
import org.example.requests.Request;
import org.example.responses.DefaultResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Authorization extends Command {

    private CollectionManager collectionManager;

    public Authorization(CollectionManager collectionManager) {
        super("authorization", "Авторизовать пользователя.");
        this.collectionManager = collectionManager;
    }

    public DefaultResponse execute(Request request) {
        System.out.println("Проверка данных пользователя ...");
        AuthorizationRequest auth = (AuthorizationRequest) request;
        try (Connection connection = DBUtils.getConnection()) {
            String query = "SELECT COUNT(*) AS amount FROM visitor WHERE login='%s' AND password='%s'".formatted(auth.getLogin(), auth.getPassword());
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("amount");
                if (count > 0) return new DefaultResponse("authorization",  new String[]{"true"});
                else return new DefaultResponse("authorization",  new String[]{"false"});
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к базе данных: " + e.getMessage());
        }
        return new DefaultResponse("auth");
    }
}
