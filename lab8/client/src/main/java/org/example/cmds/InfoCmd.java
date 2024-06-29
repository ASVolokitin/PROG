package org.example.cmds;

import org.apache.commons.codec.digest.DigestUtils;
import org.example.functionalClasses.TCPClient;
import org.example.requests.BlankRequest;
import org.example.responses.DefaultResponse;
import org.example.responses.Response;

import java.io.IOException;

public class InfoCmd extends Cmd {

    private TCPClient client;

    public  InfoCmd(TCPClient client) {
        super("info");
        this.client = client;
    }

    public Response execute(String[] args) {
        DefaultResponse response;
        try {
            response = (DefaultResponse) client.send(new BlankRequest(client.getLogin(), client.getPassword(), "info"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response;
    }
}
