//package org.example;
//
//import org.apache.commons.lang3.SerializationUtils;
//import org.example.functionalClasses.RequestManager;
//import org.example.requests.Request;
//import org.example.responses.Response;
//
//import java.io.*;
//import java.net.*;
//
//public class Connect {
//    public static void main(String[] args) {
//        InetAddress host;
//        int port = 1104;
//        // SocketAddress addr = new InetSocketAddress(port);
//        Socket sock;
//        ServerSocket serv;
//        OutputStream os;
//        InputStream is;
//
//        try {
//            while (true) {
//                serv = new ServerSocket(port);
//                Socket clientSocket = serv.accept();
//                is = clientSocket.getInputStream();
//                byte[] buffer = new byte[1024];
//                int bytesRead = is.read(buffer);
//                Request request = (Request) SerializationUtils.deserialize(buffer);
//                RequestManager.handleRequest(request);
//                os = clientSocket.getOutputStream();
//                Response response = new Response("Добривечер");
//                os.write(SerializationUtils.serialize(response));
//                clientSocket.close();
//            }
//        } catch (IOException e) {
//            System.out.println("ИО-эксепшн(((.");
//        }
//    }
//}