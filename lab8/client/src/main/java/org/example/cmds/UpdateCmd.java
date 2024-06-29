package org.example.cmds;

import org.example.functionalClasses.TCPClient;
import org.example.requests.UpdateByIDRequest;
import org.example.responses.DefaultResponse;
import org.example.responses.Response;

import java.io.IOException;
import java.util.HashMap;

public class UpdateCmd extends Cmd {

    private TCPClient client;

    public UpdateCmd (TCPClient client) {
        super("update_by_id");
        this.client = client;
    }

    public Response execute(String[] args) {
        HashMap<Integer, String> data = new HashMap<>();
        for (int i = 0; i < args.length - 1; ++i) {
            data.put(i, args[i]);
        }
        data.put(12, client.getLogin());
        try {
            DefaultResponse response = (DefaultResponse) client.send(new UpdateByIDRequest(client.getLogin(), client.getPassword(), Long.parseLong(args[12]), data));
            System.out.println(response.getStringData());
        } catch (NumberFormatException e) {
            System.out.println("ID должен быть задан целым числом.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new Response("blank");
    }
}
