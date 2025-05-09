import java.net.*;

public class GoBackNServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(1234);
        byte[] buffer = new byte[1024];
        int expected = 0;

        while (true) {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            String data = new String(packet.getData(), 0, packet.getLength());
            int seq = Integer.parseInt(data.trim());

            if (seq == expected) {
                System.out.println("Received packet: " + seq);
                if (seq != 2) { // Simulate ACK loss for packet 2
                    String ack = "ACK" + seq;
                    byte[] ackBytes = ack.getBytes();
                    DatagramPacket ackPacket = new DatagramPacket(ackBytes, ackBytes.length,
                            packet.getAddress(), packet.getPort());
                    socket.send(ackPacket);
                    System.out.println("Sent: " + ack);
                } else {
                    System.out.println("Simulated ACK loss for: " + seq);
                }
                expected++;
            } else {
                System.out.println("Out of order packet: " + seq + " (Expected: " + expected + ")");
            }

            if (seq == 4) break; // stop after 5 packets
        }
        socket.close();
    }
}
