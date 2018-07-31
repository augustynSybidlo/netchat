package modes;

import java.net.*;
import java.io.*;

public class Server implements Runnable
{  private ServerSocket     server = null;
    private Thread           thread = null;
    private ServerThread client = null;

    public Server(int port) {
        try {
            System.out.println("Binding to port " + port + ", please wait  ...");
            server = new ServerSocket(port);
            System.out.println("Server started: " + server);
            start();
        } catch(IOException ioe) {
            System.out.println(ioe); }
    }

    public void run() {
        while (thread != null) {
            try {
                System.out.println("Waiting for a client ...");
                addThread(server.accept());
            } catch(IOException ie) {
                System.out.println("Acceptance Error: " + ie);
            }
        }
    }

    private void addThread(Socket socket) {
        System.out.println("Client accepted: " + socket);
        client = new ServerThread(this, socket);
        try {
            client.open();
            client.start();
        } catch(IOException ioe) {
            System.out.println("Error opening thread: " + ioe); }
    }

    private void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    private void stop() {
        if (thread != null) {
            thread.stop();
            thread = null;
        }
    }

}
