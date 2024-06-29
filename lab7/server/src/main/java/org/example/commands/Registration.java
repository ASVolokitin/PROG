package org.example.commands;

import org.example.functionalClasses.CollectionManager;
import org.example.functionalClasses.DBUtils;
import org.example.requests.RegistrationRequest;
import org.example.requests.Request;
import org.example.responses.DefaultResponse;
import org.hibernate.annotations.processing.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Registration extends Command {

    private CollectionManager collectionManager;

    public Registration(CollectionManager collectionManager) {
        super("registration", "Зарегистрировать пользователя.");
        this.collectionManager = collectionManager;
    }

    public DefaultResponse execute(Request request) {
        RegistrationRequest registrationRequest = (RegistrationRequest) request;
        try (Connection connection = DBUtils.getConnection()) {
            String query = "SELECT COUNT(*) AS amount FROM visitor WHERE login='%s'".formatted(registrationRequest.getLogin());
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("amount");
                if (count == 0) {
                    query = "INSERT INTO visitor VALUES ('%s', '%s')".formatted(registrationRequest.getLogin(), registrationRequest.getPassword());
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.executeUpdate();
                    return new DefaultResponse("registration", new String[]{"true"});
                }
                else return new DefaultResponse("registration",  new String[]{"false"});
            }
        } catch (SQLException e) {
            System.out.println("Не удалось подключиться к базе данных: " + e.getMessage());
        }
        return new DefaultResponse("reg");
    }
}
