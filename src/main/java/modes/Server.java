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
}
