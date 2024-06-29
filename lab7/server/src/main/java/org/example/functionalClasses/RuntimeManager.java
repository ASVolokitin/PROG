package org.example.functionalClasses;

import org.checkerframework.checker.units.qual.C;
import org.example.commands.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class RuntimeManager {
    private RequestManager requestManager;
    private TCPServer tcpServer;
    private CollectionManager collectionManager;

    public RuntimeManager() throws IOException {
        this.collectionManager = new CollectionManager();
        this.requestManager = new RequestManager();
        Properties properties = new Properties();
        properties.load(new FileInputStream("./resources/config.properties"));
        this.tcpServer = new TCPServer(Integer.valueOf(properties.getProperty("port")), requestManager);
        this.collectionManager.fillFromDB();
        loadCommands();
    }

    private void loadCommands() {
        Command[] commands = new Command[] {
                new Add(this.collectionManager),
                new Show(this.collectionManager),
                new Info(this.collectionManager),
                new Help(this.collectionManager),
                new Clear(this.collectionManager),
                new CountLessThanMpaaRating(this.collectionManager),
                new AddIfMax(collectionManager),
                new Authorization(collectionManager),
                new Registration(collectionManager),
                new RemoveByID(collectionManager),
                new UpdateByID(collectionManager)
        };
        for (Command cmd : commands) {
            this.requestManager.addCommand(cmd);
        }
    }


    public void run() throws IOException {
        this.tcpServer.listen();
    }


}
