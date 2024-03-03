package naveen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer implements Runnable {
    private final ServerSocket serverSocket;
    private Socket clientSocket;

    private PrintWriter printWriter;
    private BufferedReader reader;

    public TCPServer(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);

    }

    public void start() throws IOException {
        this.clientSocket = serverSocket.accept();
        System.out.println("Server started");
        this.printWriter = new PrintWriter(this.clientSocket.getOutputStream(), true);
        this.reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        while (true) {
            String line = this.reader.readLine();
            if (line.equals(".")) {
                this.printWriter.println();
                stop();
                break;
            }
            this.printWriter.println("From server: " + line);
        }
    }

    public void stop() throws IOException {
        this.printWriter.close();
        this.reader.close();
        if (!this.clientSocket.isClosed()) {
            this.clientSocket.close();
        }
        if (!this.serverSocket.isClosed()) {
            this.serverSocket.close();
        }
    }

    @Override
    public void run() {
        try {
            start();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
