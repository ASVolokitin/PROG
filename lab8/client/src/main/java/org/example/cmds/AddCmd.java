package org.example.cmds;

import org.example.functionalClasses.TCPClient;
import org.example.requests.AddRequest;
import org.example.responses.AddResponse;
import org.example.responses.Response;

import java.io.IOException;
import java.util.HashMap;

public class AddCmd extends Cmd {

    private String name;
    private TCPClient client;

    public AddCmd(TCPClient client) {
        super("add");
        this.client = client;
    }

    @Override
    public Response execute(String[] args) {
        HashMap<Integer, String> data = new HashMap<>();
        if (args[args.length - 1].equals("script")) {
             {
                data.put(0, "Мама");
                data.put(1, "395");
                data.put(2, "929");
                data.put(3, "1");
                data.put(4, "DRAMA");
                data.put(5, "R");
                data.put(6, "Darren Arfonovsky");
                data.put(7, "1973-04-07");
                data.put(8, "91.52");
                data.put(9, "495");
                data.put(10, "60");
                data.put(11, "USA");
                data.put(12, client.getLogin());
            }
        } else {
            data = new HashMap<>(); {
                data.put(0, args[0]);
                data.put(1, args[1]);
                data.put(2, args[2]);
                data.put(3, args[3]);
                data.put(4, args[4]);
                data.put(5, args[5]);
                data.put(6, args[6]);
                data.put(7, args[7]);
                data.put(8, args[8]);
                data.put(9, args[9]);
                data.put(10, args[10]);
                data.put(11, args[11]);
                data.put(12, client.getLogin());
            }
        }
        AddResponse response = null;

        try {
            response = (AddResponse) client.send(new AddRequest(client.getLogin(), client.getPassword(), data));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response;
    }
}
