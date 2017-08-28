package me.changming.httpThread;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class HttpRootAcceptor extends Thread {
    Socket socket;

    public HttpRootAcceptor(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String str = null;
            while ((str = bufferedReader.readLine()) != null && !str.equals(""))
                System.out.println(str);

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedWriter.write("HTTP/1.1 200 OK\n");
            bufferedWriter.write("Content-Type: application/json; charset=UTF-8\n\n");
            bufferedWriter.write("{\"a\": \"Hello\", \"b\": \"World\"}");
            bufferedWriter.flush();
            bufferedWriter.close();

            bufferedReader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
