package org.example.cmds;

import org.example.functionalClasses.TCPClient;
import org.example.requests.BlankRequest;
import org.example.responses.DefaultResponse;
import org.example.responses.Response;
import org.example.responses.ShowResponse;

import java.io.IOException;

public class ShowCmd extends Cmd {

    private TCPClient client;

    public ShowCmd(TCPClient client) {
        super("show");
        this.client = client;
    }

    @Override
    public Response execute(String[] args) {
        ShowResponse response = null;
        try {
            response = (ShowResponse) client.send(new BlankRequest(client.getLogin(), client.getPassword(), "show"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response;
    }
}
