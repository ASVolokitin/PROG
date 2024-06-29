package org.example.commands;

import org.example.functionalClasses.TCPClient;
import org.example.requests.UpdateByIDRequest;
import org.example.responses.DefaultResponse;

import java.io.IOException;
import java.util.HashMap;

public class UpdateByID extends Command {

    private TCPClient client;

    public UpdateByID(TCPClient client) {
        super("update_by_id", "Обновить поля элемента с заданным ID.");
        this.client = client;
    }

    public void execute(String arg) throws IOException {
        try {
            long id = Long.parseLong(arg);
            HashMap<Integer, String> data = new HashMap<>(); {
                data.put(0, "Макодзеба");
                data.put(1, "994");
                data.put(2, "495");
                data.put(3, "2");
                data.put(4, "ACTION");
                data.put(5, "PG_13");
                data.put(6, "Евгений Меркушев");
                data.put(7, "1969-07-01");
                data.put(8, "72.45");
                data.put(9, "3945");
                data.put(10, "944");
                data.put(11, "Россия");
                data.put(12, client.getLogin());
            }

            DefaultResponse response = (DefaultResponse) client.send(new UpdateByIDRequest(client.getLogin(), client.getPassword(), id, data));
            System.out.println(response.getStringData());
        } catch (NumberFormatException e) {
            System.out.println("ID должен быть задан целым числом.");
        }

    }

}
