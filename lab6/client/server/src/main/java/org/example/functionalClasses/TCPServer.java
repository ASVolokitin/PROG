package org.example.functionalClasses;


import org.apache.commons.lang3.SerializationUtils;
import org.checkerframework.checker.lock.qual.LockHeld;
import org.checkerframework.checker.units.qual.C;
import org.checkerframework.checker.units.qual.Force;
import org.example.logFormatter;
import org.example.requests.BlankRequest;
import org.example.requests.Request;
import org.example.responses.Response;

import java.io.IOException;
import java.io.Serial;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;
import java.util.logging.*;

public class TCPServer {
    private final RequestManager requestManager;
    private final CommandManager commandManager;
    private final Selector selector;
    private Map<SocketChannel, Response> channelDataMap;
    private final ByteBuffer intBuffer;
    private final static Logger logger = Logger.getLogger(TCPServer.class.getName());

    public TCPServer(int port, RequestManager requestManager, CommandManager commandManager) throws IOException {
        this.requestManager = requestManager;
        this.commandManager = commandManager;
        this.channelDataMap = new HashMap<>();
        this.intBuffer = ByteBuffer.allocate(Integer.BYTES);
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        if (isPortAvailable(port)) {
            logger.info("Порт свободен, забираю.");
            serverSocketChannel.bind(new InetSocketAddress(port));
        }
        else {
            logger.severe("Порт занят, возьмите другой.");
            System.exit(0);
        }
        serverSocketChannel.configureBlocking(false);
        this.selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        LogManager.getLogManager().reset();
        logger.setLevel(Level.ALL);

        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.INFO);
        ch.setFormatter(new logFormatter());
        logger.addHandler(ch);
        logger.info("Сервер запущен на порту %d.".formatted(port));
    }

    public static boolean isPortAvailable(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            return true;
        } catch (IOException e) {
            return false; // Порт занят или произошла ошибка
        }
    }

    public void listen() {
        while (true) {
            try {
                selector.select();
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();
                    keyIterator.remove();
                    if (key.isValid()) {
                        if (key.isAcceptable()) handleConnectable(key);
                        else if (key.isReadable()) handleReadable(key);
                        else if (key.isWritable()) handleWritable(key);
                    }
                }
            } catch (IOException e) {
//                System.out.println("Клиент отключился.");
            }
        }
    }

    private void disconnect(SocketChannel client) {
        try {
            client.close();
            logger.info("Клиент %s отключился.".formatted(client.getRemoteAddress()));
        } catch (IOException e) {}
    }

    private void handleConnectable(SelectionKey key) throws IOException {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
        SocketChannel client = serverSocketChannel.accept();
        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ + SelectionKey.OP_WRITE);
        logger.info("Клиент %s подключился.".formatted(client.getRemoteAddress()));
    }

    private void handleWritable(SelectionKey key) throws IOException {
        SocketChannel client = (SocketChannel) key.channel();
        Response response = channelDataMap.get(client);
        if (response == null) return;
        sendResponse(client, response);
        channelDataMap.remove(client);
     }

     private void handleReadable(SelectionKey key) throws IOException {
        SocketChannel client = (SocketChannel) key.channel();
        int requestSize = this.readInt(client);
         if (requestSize == -1) {
            disconnect(client);
            logger.info("Клиент %s отключился.".formatted(client.getRemoteAddress()));
            return;
        }

        byte[] requestBytes = this.readRequest(client, requestSize);
        Request request = (Request) SerializationUtils.deserialize(requestBytes);
        logger.info("Запрос типа %s принят от клиента %s.".formatted(request.getName(), client.getRemoteAddress()));
         try {
             Response response = requestManager.handleRequest(request);
             commandManager.getCommand("save").execute(new BlankRequest("blank"));
             this.channelDataMap.put(client, response);
        } catch (IOException e) {
            disconnect(client);
        }
     }

     private void writeInt(SocketChannel client, int x) throws IOException {
        intBuffer.clear();
        intBuffer.putInt(x);
        intBuffer.flip();
        client.write(intBuffer);
        logger.info("Клиенту %s был отправлен размер пакета данных (%d байт).".formatted(client.getRemoteAddress(), x));
     }

     private void sendResponse(SocketChannel client, Response response) throws IOException {
        byte[] responseBytes = SerializationUtils.serialize(response);
        this.writeInt(client, responseBytes.length);
        client.write(ByteBuffer.wrap(responseBytes));
        logger.info("Ответ типа %s отправлен клиенту %s.".formatted(response.getName(), client.getRemoteAddress()));
     }

     private int readInt(SocketChannel client) {
        intBuffer.clear();
        try {
            int size = client.read(intBuffer);
            if (size < 0) return -1;
        } catch (IOException e) {
            disconnect(client);
            return -1;
        }
        return intBuffer.flip().getInt();
     }

     private byte[] readRequest(SocketChannel client, int responseSize) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(responseSize);
        int curRead = 0;
        while (curRead < responseSize) {
            curRead = client.read(buffer);
            if (curRead == 0) continue;
            if (curRead == -1) break;
            responseSize -= curRead;
        }
        return buffer.array();
     }



}
