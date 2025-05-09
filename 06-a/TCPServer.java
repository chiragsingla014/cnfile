import java.net.*;
import java.io.*;


public class TCPServer{
    public static void main (String[] args) throws Exception{
        int port = 12345;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server is listening.......");

        Socket socket = serverSocket.accept();
        System.out.println("Connection established top client");

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        String clientMessage = in.readLine();
        System.out.println("Client says: " + clientMessage);

        String reply = "Hello Client";
        out.println(reply);



        socket.close();
        serverSocket.close();
    }

}
