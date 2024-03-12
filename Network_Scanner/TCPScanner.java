import java.util.ArrayList;
import java.util.List;

public class TCPScanner {
    public static List<Integer> tcpScan(String ip, List<Integer> ports) {
        
        List<Integer> openPorts = new ArrayList<>();

        System.out.println("Simulating scan results for IP address " + ip + "...");
        for (int port : ports) {
            if (isPortOpen(port, ip)) { 
                openPorts.add(port);
            }
        }

        return openPorts;
    }

    private static boolean isPortOpen(int port, String ipAddress) {
        
        String[] octets = ipAddress.split("\\.");
        int lastOctet = Integer.parseInt(octets[3]);

        return port <= lastOctet;
    }
}
