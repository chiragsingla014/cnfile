import java.net.*;
import java.util.Scanner;

public class UDPChatServer {
    public static void main(String[] args) {
        byte[] buffer = new byte[1024];

        try (DatagramSocket socket = new DatagramSocket(8080); Scanner scanner = new Scanner(System.in)) {
            System.out.println("Server started. Waiting for messages...");

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String msg = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Client: " + msg);

                System.out.print("You: ");
                String reply = scanner.nextLine();
                byte[] replyBytes = reply.getBytes();

                DatagramPacket replyPacket = new DatagramPacket(
                    replyBytes, replyBytes.length, packet.getAddress(), packet.getPort()
                );
                socket.send(replyPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
