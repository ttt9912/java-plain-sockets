package ch.ttt.plain.socket.server;

import java.io.IOException;
import java.net.ServerSocket;

/*
 * Terminal client:
 * $ nc localhost 9876
 */
public class Server {
    private static final int PORT = 9876;

    public static void main(String[] args) throws IOException {
        final ServerSocket server = new ServerSocket(PORT);
        System.out.println("Server started on port " + PORT);
        while (true) {
            new ServerThread(server.accept()).start();
        }
    }
}
