import java.net.*;
import java.io.*;

public class ARPClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 9999);

        BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        System.out.print("Enter IP address to resolve: ");
        String ip = userIn.readLine();

        out.println(ip);
        String mac = in.readLine();

        System.out.println("MAC Address: " + mac);

        in.close();
        out.close();
        socket.close();
    }
}
