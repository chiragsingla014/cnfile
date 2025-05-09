import java.net.*;

public class UDPServer {
    public static void main(String[] args) throws Exception{
        int port = 12345;
        DatagramSocket socket = new DatagramSocket(port);
        System.out.println("UDP server is ready.......");

        byte[] buffer = new byte[1024];

        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);

        String message = new String(packet.getData(), 0, packet.getLength());
        System.out.println("Received from client: " + message);

        String reply = "Hello from UDP Server!";
        byte[] replyData = reply.getBytes();
        DatagramPacket response = new DatagramPacket(
            replyData, replyData.length, packet.getAddress(), packet.getPort()
        );
        socket.send(response);

        socket.close();
    }
}
