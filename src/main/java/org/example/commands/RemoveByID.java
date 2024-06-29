package org.example.commands;

import org.checkerframework.checker.units.qual.N;
import org.example.functionalClasses.TCPClient;
import org.example.requests.RemoveByIDRequest;
import org.example.responses.DefaultResponse;

import java.io.IOException;

public class RemoveByID extends Command {

    private TCPClient client;

    public RemoveByID(TCPClient client) {
        super("remove_by_id", "Удалить ");
        this.client = client;
    }

    public void execute(String arg) throws IOException {
        try {
            long id = Long.parseLong(arg);
            DefaultResponse response = (DefaultResponse) client.send(new RemoveByIDRequest(client.getLogin(), client.getPassword(), id));
            System.out.println(response.getStringData());
        } catch (NumberFormatException e) {
            System.out.println("ID должен быть целым числом.");
        }
    }

}
