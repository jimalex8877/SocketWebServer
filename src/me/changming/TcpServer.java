package me.changming;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import me.changming.httpThread.HttpRootAcceptor;

public class TcpServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            Socket socket = serverSocket.accept();

            while (socket != null) {
                new HttpRootAcceptor(socket).start();
                socket = serverSocket.accept();
            }

            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
