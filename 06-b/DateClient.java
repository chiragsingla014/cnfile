import java.net.*;
import java.io.*;

public class DateClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8080);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println("Server says: " + in.readLine());
        socket.close();
    }
}
