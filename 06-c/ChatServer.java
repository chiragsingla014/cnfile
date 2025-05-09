import java.io.*;
import java.net.*;

public class ChatServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8080);
        System.out.println("Server started. Waiting for client...");

        Socket client = server.accept();
        System.out.println("Client connected!");

        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        String msgFromClient, msgToClient;

        while ((msgFromClient = in.readLine()) != null) {
            System.out.println("Client: " + msgFromClient);
            System.out.print("You: ");
            msgToClient = console.readLine();
            out.println(msgToClient);
        }

        client.close();
        server.close();
    }
}
