package org.example.functionalClasses;

import org.example.cmds.*;
import org.example.commands.*;
import org.example.responses.Response;

import java.io.IOException;
import java.util.HashMap;

public class RuntimeManager {
    private CommandManager commandManager;
    private TCPClient client;
    private HashMap<String, Cmd> commands = new HashMap<>();

    public RuntimeManager(TCPClient client) {
        this.commandManager = new CommandManager();
        this.client = client;
        loadCommands();
    }

//    private void loadCommands() {
//        Command[] commands = new Command[] {
//                new Info(client),
//                new Show(client),
//                new Add(client),
//                new Help(client),
//                new Clear(client),
//                new ExecuteScript(client),
//                new CountLessThanMpaaRating(client),
//                new Exit(client),
//                new AddIfMax(client),
//                new Authorization(client),
//                new Registration(client),
//                new RemoveByID(client),
//                new UpdateByID(client)
//        };
//        for (Command command : commands) commandManager.addCommand(command);
//    }

    private void loadCommands() {
        this.commands.put("auth", new AuthCmd(client));
        this.commands.put("register", new RegisterCmd(client));
        this.commands.put("add", new AddCmd(client));
        this.commands.put("show", new ShowCmd(client));
        this.commands.put("clear", new ClearCmd(client));
        this.commands.put("remove_by_id", new RemoveByIDCmd(client));
        this.commands.put("info", new InfoCmd(client));
        this.commands.put("update_by_id", new UpdateCmd(client));

    }

    public TCPClient getClient() {
        return client;
    }

    public Response executeCommand(String name, String[] args) {
        Response response = commands.get(name).execute(args);
        return response;
    }

    public void run() {
        commandManager.interacting();
    }


}
