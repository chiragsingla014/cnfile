import java.net.*;
import java.io.*;
import java.util.*;

public class ARPServer {
    public static void main(String[] args) throws IOException {
        Map<String, String> arpTable = new HashMap<>();
        arpTable.put("192.168.1.1", "AA:BB:CC:DD:EE:01");
        arpTable.put("192.168.1.2", "AA:BB:CC:DD:EE:02");

        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("ARP Server started...");

        Socket clientSocket = serverSocket.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        String ip = in.readLine();
        System.out.println("Received IP: " + ip);

        String mac = arpTable.getOrDefault(ip, "MAC address not found");
        out.println(mac);

        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }
}
