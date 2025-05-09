import java.net.*;
import java.io.*;
import java.util.Date;

public class DateServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8080);
        while (true) {
            Socket client = server.accept();
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            out.println(new Date().toString());
            client.close();
            server.close();
        }
    }
}
