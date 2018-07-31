import modes.Client;
import modes.Server;

public class NetChat {
    public static void main(String[] args) {
        int portNumber;
        switch (args[0]) {
            case "server":
                portNumber = Integer.parseInt(args[1]);
                Server server = new Server(portNumber);
                server.run();
            case "client":
                String hostName = args[1];
                portNumber = Integer.parseInt(args[2]);
                Client client = new Client(hostName, portNumber);
        }
    }
}