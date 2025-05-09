import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) throws Exception{
        int port = 12345;
        String url = "localhost";
        Socket socket = new Socket(url, port);
        System.out.println("Connection established to server.");

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String reply = "Hello Server";
        out.println(reply);

        String response = in.readLine();
        System.out.println("Server says: " + response);

        socket.close();
    }
}
