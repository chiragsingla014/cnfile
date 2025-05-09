import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 5000);
        System.out.println("Connected to server.");

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

        String input;
        while (true) {
            System.out.print("Enter calculation (e.g., 5 + 3): ");
            input = userInput.readLine();
            if (input == null || input.equalsIgnoreCase("exit")) break;

            out.println(input);
            String response = in.readLine();
            System.out.println(response);
        }

        socket.close();
    }
}
