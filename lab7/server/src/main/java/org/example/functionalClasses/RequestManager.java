package org.example.functionalClasses;

import org.checkerframework.checker.units.qual.C;
import org.example.commands.Add;
import org.example.commands.Command;
import org.example.commands.Help;
import org.example.commands.Save;
import org.example.requests.Request;
import org.example.responses.Response;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class RequestManager {
    private HashMap<String, Command> commandList = new HashMap<>();

    public Response handleRequest(Request request) throws FileNotFoundException {
        Command command = commandList.get(request.getName());
        return command.execute(request);
    }

    public void addCommand(Command command) {
        commandList.put(command.getName(), command);
    }

}
