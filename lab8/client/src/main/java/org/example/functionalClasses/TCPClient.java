package org.example.functionalClasses;


import org.apache.commons.lang3.SerializationUtils;
import org.example.requests.Request;
import org.example.responses.Response;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

public class TCPClient {
    private final int RESPONSE_BUFFER_SIZE = 16;
    private Socket socket;
    private OutputStream os;
    private InputStream is;
    private String host;
    private int port;
    final private int MAX_RETRIES = 5;
    private int retries;
    private final ByteBuffer intBuffer;
    private String login;
    private String password;

    public TCPClient(String host, int port) {
        this.intBuffer = ByteBuffer.allocate(Integer.BYTES);
        this.host = host;
        this.port = port;
//        this.login = login;
        try {
            connect();
        } catch (IOException e) {
            System.out.println("Не удалось создать клиента.");
            System.exit(0);
        }
        System.out.println("Подключен к " + this.socket.toString());
    }

    private void connect() throws IOException, UnknownHostException {
        try {
            this.socket = new Socket(host, port);
        } catch (IOException e) {
            this.retries++;
            if (retries > MAX_RETRIES) {
                throw new IOException("Превышено количество попыток переподключения.");
            }
            System.out.println("Попытка переподключения ... (%d из %d)".formatted(this.retries, MAX_RETRIES));
            connect();
        }
        this.os = socket.getOutputStream();
        this.is = socket.getInputStream();
        this.retries = 0;
    }

    public Socket getSocket() {
        return socket;
    }

    public void disconnect() {
        try {
            System.out.println("Отключаюсь от " + socket.toString());
            socket.close();
            is.close();
            os.close();
        } catch (IOException e) {
            System.out.println("Не удалось отключиться от сервера.");
        }
    }

    public Response send(Request request) throws IOException {
        try {
            byte[] bytes = SerializationUtils.serialize(request);
            writeInt(bytes.length);
            os.write(bytes);
            os.flush();

            int responseSize = readInt();
            byte[] responseBytes = this.readResponse(responseSize);
            Response response = (Response) SerializationUtils.deserialize(responseBytes);
            return response;
        } catch (IOException e) {
            System.out.println("Ошибка во время отправки: " + e.getMessage());
            System.out.println("Попытка переподключения ...");
            this.retries = 0;
            connect();
            return send(request);
        }
    }

    public void writeInt(int x) throws IOException {
        intBuffer.clear();
        intBuffer.putInt(x);
        intBuffer.flip();
        this.os.write(intBuffer.array());
    }

    public int readInt() throws IOException {
        intBuffer.clear();
        is.read(intBuffer.array());
        return intBuffer.getInt();
    }

    private byte[] readResponse(int responseSize) throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        byte[] chunk = new byte[this.RESPONSE_BUFFER_SIZE];
        int totalRead = 0;
        while (totalRead < responseSize) {
            totalRead += is.read(chunk);
            bytes.write(chunk);
        }
        return bytes.toByteArray();
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


