// File: DNSClient.java
import java.net.*;
import java.util.Scanner;

public class DNSClient {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(); Scanner scanner = new Scanner(System.in)) {
            InetAddress serverAddress = InetAddress.getByName("localhost");

            System.out.print("Enter domain to resolve: ");
            String domain = scanner.nextLine();

            byte[] queryBytes = domain.getBytes();
            DatagramPacket query = new DatagramPacket(queryBytes, queryBytes.length, serverAddress, 12345);
            socket.send(query);

            byte[] buffer = new byte[1024];
            DatagramPacket response = new DatagramPacket(buffer, buffer.length);
            socket.receive(response);

            String ip = new String(response.getData(), 0, response.getLength());
            System.out.println("Resolved IP: " + ip);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
