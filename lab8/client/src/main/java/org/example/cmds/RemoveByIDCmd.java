package org.example.cmds;

import org.example.functionalClasses.TCPClient;
import org.example.requests.RemoveByIDRequest;
import org.example.responses.DefaultResponse;
import org.example.responses.Response;

import java.io.IOException;

public class RemoveByIDCmd extends Cmd {

    private TCPClient client;

    public RemoveByIDCmd(TCPClient client) {
        super("remove_by_id");
        this.client = client;
    }

    @Override
    public Response execute(String[] args) {
        try {
            long id = Long.parseLong(args[0]);
            DefaultResponse response = (DefaultResponse) client.send(new RemoveByIDRequest(client.getLogin(), client.getPassword(), id));
//            System.out.println(response.getStringData());
            return response;
        } catch (NumberFormatException e) {
            System.out.println("ID должен быть целым числом.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new Response("blank");
    }
}
