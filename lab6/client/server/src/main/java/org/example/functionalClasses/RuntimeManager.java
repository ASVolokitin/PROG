package org.example.functionalClasses;

import org.checkerframework.checker.units.qual.C;
import org.example.commands.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class RuntimeManager {
    private RequestManager requestManager;
    private TCPServer tcpServer;
    private CollectionManager collectionManager;
    private CommandManager commandManager;

    public RuntimeManager() throws IOException {
        this.collectionManager = new CollectionManager();
        this.commandManager = new CommandManager(this.collectionManager);
        this.requestManager = new RequestManager();
        this.tcpServer = new TCPServer(11046, requestManager, commandManager);
        loadCommands();
        new ReadFromFile(collectionManager).execute("");
    }

    private void loadCommands() {
        Command[] commands = new Command[] {
                new Add(this.collectionManager),
                new Show(this.collectionManager),
                new Info(this.collectionManager),
                new Help(this.collectionManager),
                new Clear(this.collectionManager),
                new CountLessThanMpaaRating(this.collectionManager),
                new AddIfMax(collectionManager)
        };
        for (Command cmd : commands) {
            this.requestManager.addCommand(cmd);
        }
    }


    public void run() throws IOException {
        this.tcpServer.listen();
    }


}
