import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("Server started. Waiting for client...");

        Socket socket = serverSocket.accept();
        System.out.println("Client connected.");

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        String input;
        while ((input = in.readLine()) != null) {
            String[] parts = input.split(" ");
            if (parts.length != 3) {
                out.println("Invalid format. Use: number1 operator number2");
                continue;
            }

            double a = Double.parseDouble(parts[0]);
            String op = parts[1];
            double b = Double.parseDouble(parts[2]);
            double result;

            switch (op) {
                case "+": result = a + b; break;
                case "-": result = a - b; break;
                case "*": result = a * b; break;
                case "/":
                    if (b == 0) {
                        out.println("Error: Division by zero");
                        continue;
                    }
                    result = a / b;
                    break;
                default:
                    out.println("Unsupported operator.");
                    continue;
            }

            out.println("Result: " + result);
        }

        socket.close();
        serverSocket.close();
    }
}
