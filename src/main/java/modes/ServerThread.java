package modes;

import java.net.*;
import java.io.*;

public class ServerThread extends Thread {
    private Socket socket = null;
    private Server server = null;
    private int id = -1;
    private DataInputStream streamIn = null;

    ServerThread(Server server, Socket socket) {
        this.server = server;
        this.socket = socket;
        this.id = socket.getPort();
    }

    public void run() {
        System.out.println(String.format("Server Thread %s running.", id));
        while(true) {
            try {
                System.out.println(streamIn.readUTF());
            } catch(IOException ioe) {
                System.out.println(String.format("Exception occured in ServerThread %s", id));
            }
        }
    }

    public void open() throws IOException {
        streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
    }

    public void close() throws IOException {
        if(socket != null) socket.close();
        if(streamIn != null) streamIn.close();
    }
}
