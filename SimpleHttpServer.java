import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SimpleHttpServer {
    public static void main(String[] args) {
        int port = 6969; // Choose a port number

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server running on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                handleRequest(clientSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleRequest(Socket clientSocket) throws IOException {
        OutputStream outputStream = clientSocket.getOutputStream();
        String response = "HTTP/1.1 200 OK\r\n\r\n";
        outputStream.write(response.getBytes());
        File index = new File("index.html");
        Scanner sc = new Scanner(index);
        while (sc.hasNext()) {
            outputStream.write(sc.nextLine().getBytes());
        }

        outputStream.flush();
        outputStream.close();
        clientSocket.close();
    }
}
