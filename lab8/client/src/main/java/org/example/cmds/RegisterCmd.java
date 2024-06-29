package org.example.cmds;

import org.apache.commons.codec.digest.DigestUtils;
import org.example.functionalClasses.TCPClient;
import org.example.requests.AuthorizationRequest;
import org.example.requests.RegistrationRequest;
import org.example.responses.DefaultResponse;
import org.example.responses.Response;

import java.io.IOException;

public class RegisterCmd extends Cmd {

    private TCPClient client;

    public RegisterCmd(TCPClient client) {
        super("register");
        this.client = client;
    }

    public Response execute(String[] args) {
        DefaultResponse response;
        String login = args[0];
        String password = DigestUtils.md5Hex(args[1].getBytes());
        try {
            response = (DefaultResponse) client.send(new RegistrationRequest(login, password));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response;
    }
}
