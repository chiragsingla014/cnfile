// File: DNSServer.java
import java.net.*;
import java.util.HashMap;

public class DNSServer {
    public static void main(String[] args) {
        HashMap<String, String> records = new HashMap<>();
        records.put("example.com", "93.184.216.34");
        records.put("google.com", "142.250.190.78");

        byte[] buffer = new byte[1024];

        try (DatagramSocket socket = new DatagramSocket(12345)) {
            System.out.println("DNS Server started on port 12345...");

            while (true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);

                String domain = new String(request.getData(), 0, request.getLength());
                System.out.println("Received query for: " + domain);

                String ip = records.getOrDefault(domain, "NOT_FOUND");
                byte[] responseBytes = ip.getBytes();

                DatagramPacket response = new DatagramPacket(
                    responseBytes, responseBytes.length,
                    request.getAddress(), request.getPort()
                );
                socket.send(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
