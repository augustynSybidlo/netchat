package modes;

import java.net.*;
import java.io.*;

public class ClientThread extends Thread {
    private Socket socket = null;
    private Client client = null;
    private DataInputStream streamIn = null;

    ClientThread(Client client, Socket socket) {
        this.client   = client;
        this.socket   = socket;
        open();
        start();
    }

    private void open() {
        try {
            streamIn  = new DataInputStream(socket.getInputStream());
        } catch(IOException ioe) {
            System.out.println("Error getting input stream: " + ioe);
            client.stop();
        }
    }

    public void close() {
        try {
            if (streamIn != null) streamIn.close();
        } catch(IOException ioe) {
            System.out.println("Error closing input stream: " + ioe);
        }
    }

    public void run() {
        while (true) {
            try {
                client.handle(streamIn.readUTF());
            } catch(IOException ioe) {
                System.out.println("Listening error: " + ioe.getMessage());
                client.stop();
            }
        }
    }
}