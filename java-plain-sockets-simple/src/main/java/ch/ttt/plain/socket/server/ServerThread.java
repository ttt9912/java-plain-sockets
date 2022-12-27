package ch.ttt.plain.socket.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread {
    private final Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.log("Client connected");

            String message;
            while ((message = in.readLine()) != null) {
                this.log(message);
                out.println("> " + message);
            }

            this.log("Closing client connection");
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void log(final String message) {
        System.out.println("[" + Thread.currentThread().getName() + " " + socket.getInetAddress() + " " + socket.getPort() + "] "
                + message);
    }
}
