import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8080);
        System.out.println("Connected to server!");

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        String msgFromServer, msgToServer;

        while (true) {
            System.out.print("You: ");
            msgToServer = console.readLine();
            out.println(msgToServer);

            msgFromServer = in.readLine();
            if (msgFromServer == null) break;
            System.out.println("Server: " + msgFromServer);
        }

        socket.close();
    }
}
