import java.net.*;
import java.util.*;

public class RARPClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("localhost");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter MAC address to resolve: ");
        String macAddress = scanner.nextLine();

        byte[] sendData = macAddress.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 9999);
        socket.send(sendPacket);

        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        socket.receive(receivePacket);

        String ipAddress = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println("Resolved IP Address: " + ipAddress);

        socket.close();
        scanner.close();
    }
}
