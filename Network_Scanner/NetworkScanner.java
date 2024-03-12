import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NetworkScanner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose the type of scan (ARP/TCP):");
        String scanType = scanner.nextLine();

        if (scanType.equalsIgnoreCase("ARP")) {
            System.out.println("Enter IP address or IP address range for ARP scan:");
            String ip = scanner.nextLine();
            performARPScan(ip);
        } else if (scanType.equalsIgnoreCase("TCP")) {
            System.out.println("Enter IP address for TCP scan:");
            String ip = scanner.nextLine();
            System.out.println("Enter ports for TCP scan:");
            String portInput = scanner.nextLine();
            performTCPScan(ip, portInput);
        } else {
            System.err.println("Invalid scan type.");
        }

        scanner.close();
    }

    private static void performARPScan(String ip) {
        System.out.println("Performing ARP scan...");
        List<ARPScanner.ARPResult> arpResults = ARPScanner.arpScan(ip);
        if (arpResults.isEmpty()) {
            System.out.println("ARP scan result for " + ip + ": No devices found.");
        } else {
            System.out.println("ARP scan result for " + ip + ":");
            for (ARPScanner.ARPResult result : arpResults) {
                System.out.println(result.IP + " ==> " + result.MAC);
            }
        }
    }

    private static void performTCPScan(String ip, String portInput) {
        String[] portStrings = portInput.split("\\s+");
        List<Integer> ports = new ArrayList<>();
        for (String portString : portStrings) {
            try {
                ports.add(Integer.parseInt(portString));
            } catch (NumberFormatException e) {
                System.err.println("Invalid port number: " + portString);
                return;
            }
        }

        System.out.println("Performing TCP scan...");
        List<Integer> openPorts = TCPScanner.tcpScan(ip, ports);
        if (openPorts.isEmpty()) {
            System.out.println("TCP scan result for " + ip + ": No open ports found.");
        } else {
            System.out.println("TCP scan result for " + ip + ":");
            for (int port : openPorts) {
                System.out.println("Port " + port + " is open.");
            }
        }
    }
}
