import java.net.*;
import java.util.Scanner;

public class UDPChatClient {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(); Scanner scanner = new Scanner(System.in)) {
            InetAddress serverAddress = InetAddress.getByName("localhost");
            byte[] buffer = new byte[1024];

            while (true) {
                System.out.print("You: ");
                String msg = scanner.nextLine();
                byte[] msgBytes = msg.getBytes();

                DatagramPacket packet = new DatagramPacket(msgBytes, msgBytes.length, serverAddress, 8080);
                socket.send(packet);

                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
                socket.receive(reply);
                String replyMsg = new String(reply.getData(), 0, reply.getLength());
                System.out.println("Server: " + replyMsg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
