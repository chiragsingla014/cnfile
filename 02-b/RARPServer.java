import java.net.*;
import java.util.*;

public class RARPServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(9999);
        System.out.println("RARP Server started...");

        Map<String, String> rarpTable = new HashMap<>();
        rarpTable.put("AA:BB:CC:DD:EE:01", "192.168.1.1");
        rarpTable.put("AA:BB:CC:DD:EE:02", "192.168.1.2");

        byte[] receiveData = new byte[1024];
        
        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            
            String macAddress = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Received MAC Address: " + macAddress);

            String ipAddress = rarpTable.getOrDefault(macAddress, "IP address not found");

            byte[] sendData = ipAddress.getBytes();
            InetAddress clientAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
            socket.send(sendPacket);
        }
    }
}
