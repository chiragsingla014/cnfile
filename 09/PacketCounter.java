import org.jnetpcap.Pcap;
import org.jnetpcap.packet.JPacket;
import org.jnetpcap.packet.JPacketHandler;

public class PacketCounter {

    public static void main(String[] args) {
        String filename = "/home/chiragsingla014/capture.pcap";
        StringBuilder errbuf = new StringBuilder();

        Pcap pcap = Pcap.openOffline(filename, errbuf);
        if (pcap == null) {
            System.err.println("Error opening file: " + errbuf.toString());
            return;
        }

        final int[] count = {0};
        pcap.loop(-1, new JPacketHandler<StringBuilder>() {
            public void nextPacket(JPacket packet, StringBuilder user) {
                count[0]++;
            }
        }, errbuf);

        System.out.println("Total packets in file: " + count[0]);
        pcap.close();
    }
}
