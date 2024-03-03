package naveen;

import java.io.IOException;

public class Main {
    private static final int serverPort = 8088;
    private static final String serverIp = "localhost";

    public static void main(String[] args) throws IOException, InterruptedException {
        Thread thread = new Thread(new TCPServer(serverPort));
        thread.start();

        TCPClient client = new TCPClient();
        client.startConnection(serverIp, serverPort);
        int i = 0;
        while (i++ < 10) {
            System.out.println(client.sendMessage("Hello server " + Thread.currentThread().threadId()));
            Thread.sleep(500);
        }
        System.out.println(client.sendMessage("."));
        client.stopConnection();
    }
}
