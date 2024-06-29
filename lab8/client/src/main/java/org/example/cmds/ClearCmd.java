package org.example.cmds;

import org.example.functionalClasses.TCPClient;
import org.example.requests.BlankRequest;
import org.example.responses.DefaultResponse;
import org.example.responses.Response;

import java.io.IOException;

public class ClearCmd extends Cmd {

    private TCPClient client;

    public ClearCmd(TCPClient client) {
        super("clear");
        this.client = client;
    }

    @Override
    public Response execute(String[] args) {
        try {
            Response response = client.send(new BlankRequest(client.getLogin(), client.getPassword(), "clear"));
            System.out.println("Коллекция очищена.");
            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
