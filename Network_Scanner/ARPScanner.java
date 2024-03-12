import java.util.ArrayList;
import java.util.List;

public class ARPScanner {
    public static class ARPResult {
        public String IP;
        public String MAC;

        public ARPResult(String ip, String mac) {
            this.IP = ip;
            this.MAC = mac;
        }
    }

    public static List<ARPResult> arpScan(String firstThreeOctets) {
        List<ARPResult> results = new ArrayList<>();

        System.out.println("Simulating ARP scan for IP addresses with first three octets: " + firstThreeOctets);
        for (int i = 1; i <= 5; i++) {
            String simulatedIP = firstThreeOctets + "." + i;
            String simulatedMAC = generateRandomMACAddress();
            results.add(new ARPResult(simulatedIP, simulatedMAC));
        }

        return results;
    }

    private static String generateRandomMACAddress() {
        StringBuilder macBuilder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            macBuilder.append(String.format("%02x", (int) (Math.random() * 255)));
            if (i < 5) {
                macBuilder.append(":");
            }
        }
        return macBuilder.toString();
    }
}
