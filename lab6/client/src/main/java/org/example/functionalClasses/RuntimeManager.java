package org.example.functionalClasses;

import org.example.commands.*;

public class RuntimeManager {
    private CommandManager commandManager;
    private TCPClient client;

    public RuntimeManager(TCPClient client) {
        this.commandManager = new CommandManager();
        this.client = client;
        loadCommands();
    }

    private void loadCommands() {
        Command[] commands = new Command[] {
                new Info(client),
                new Show(client),
                new Add(client),
                new Help(client),
                new Clear(client),
                new ExecuteScript(client),
                new CountLessThanMpaaRating(client),
                new Exit(client),
                new AddIfMax(client)
        };
        for (Command command : commands) commandManager.addCommand(command);
    }

    public void run() {
        commandManager.interacting();
    }


}
