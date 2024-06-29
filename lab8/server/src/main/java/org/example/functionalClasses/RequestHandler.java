//package org.example.functionalClasses;
//
//import org.apache.commons.lang3.SerializationUtils;
//import org.example.requests.Request;
//
//import java.io.IOException;
//import java.nio.ByteBuffer;
//import java.nio.channels.SocketChannel;
//
//public class RequestHandler implements Runnable {
//    private final SocketChannel client;
//    private final ResultHandler<Request> resultHandler;
//    private ByteBuffer intBuffer;
//
//    public RequestHandler(SocketChannel client, ResultHandler<Request> resultHandler) {
//        this.client = client;
//        this.resultHandler = resultHandler;
//    }
//
//    @Override
//    public void run() {
//        int requestSize = readInt(client);
//        if (requestSize == -1) {
//            disconnect(client);
////            logger.info("Клиент %s отключился.".formatted(client.getRemoteAddress()));
//            return;
//        }
//        byte[] requestBytes = this.readRequest(client, requestSize);
//        Request request = (Request) SerializationUtils.deserialize(requestBytes);
//        resultHandler.handleResult(request);
//    }
//
//    private int readInt(SocketChannel client) {
//        intBuffer.clear();
//        try {
//            int size = client.read(intBuffer);
//            if (size < 0) return -1;
//        } catch (IOException e) {
//            disconnect(client);
//            return -1;
//        }
//        return intBuffer.flip().getInt();
//    }
//
//    private byte[] readRequest(SocketChannel client, int responseSize) {
//        ByteBuffer buffer = ByteBuffer.allocate(responseSize);
//        int curRead = 0;
//        try {
//            while (curRead < responseSize) {
//                curRead = client.read(buffer);
//                if (curRead == 0) continue;
//                if (curRead == -1) break;
//                responseSize -= curRead;
//            }
//        } catch (IOException e) {
//            System.out.println("Ошибка при чтении запроса: " + e.getMessage());
//        }
//
//        return buffer.array();
//    }
//
//    private void disconnect(SocketChannel client) {
//        try {
//            client.close();
////            logger.info("Клиент %s отключился.".formatted(client.getRemoteAddress()));
//        } catch (IOException e) {}
//    }
//}
