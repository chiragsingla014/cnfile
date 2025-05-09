import java.io.*;
import java.net.*;

public class BubbleSortClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 6000);
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        System.out.print("Enter numbers separated by space: ");
        String input = userInput.readLine();
        out.println(input);

        String sorted = in.readLine();
        System.out.println("Sorted array: " + sorted);

        socket.close();
    }
}
