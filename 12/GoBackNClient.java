import java.net.*;

public class GoBackNClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress serverAddr = InetAddress.getByName("localhost");
        socket.setSoTimeout(1000);

        int base = 0;
        int nextSeq = 0;
        int windowSize = 3;

        while (base < 5) {
            while (nextSeq < base + windowSize && nextSeq < 5) {
                String msg = Integer.toString(nextSeq);
                byte[] data = msg.getBytes();
                DatagramPacket packet = new DatagramPacket(data, data.length, serverAddr, 1234);
                socket.send(packet);
                System.out.println("Sent packet: " + nextSeq);
                nextSeq++;
            }

            try {
                byte[] ackData = new byte[1024];
                DatagramPacket ackPacket = new DatagramPacket(ackData, ackData.length);
                socket.receive(ackPacket);
                String ack = new String(ackPacket.getData(), 0, ackPacket.getLength());
                int ackNum = Integer.parseInt(ack.replace("ACK", "").trim());
                System.out.println("Received: " + ack);
                base = ackNum + 1;
            } catch (SocketTimeoutException e) {
                System.out.println("Timeout: Resending from packet " + base);
                nextSeq = base;
            }
        }

        socket.close();
    }
}
