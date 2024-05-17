
import java.io.*;
import java.net.*;

public class Main {

    static void sendPing(String host, int timeout) throws UnknownHostException, IOException {

        InetAddress ip = InetAddress.getByName(host);

        System.out.println("Sending ping request to " + host);
        if (ip.isReachable(timeout)) {

            System.out.println("Host is reachable");
            System.out.println("\nDetails");
            System.out.println("Host address: " + ip.getHostAddress());
            System.out.println("Host name: " + ip.getHostName());
            System.out.println("Is link local address: " + ip.isLinkLocalAddress());
            System.out.println("Is any local address: " + ip.isAnyLocalAddress());
            System.out.println("Is loopback address: " + ip.isLoopbackAddress());

        } else {
			System.out.println("Can't reach the host!");
		}

    }
 
    public static void main(String[] args) throws IllegalArgumentException, NumberFormatException {

        String host;
        int timeout;

        if (args.length != 2) {
            throw new IllegalArgumentException("Two parameters are required. Host and Timeout(in ms)");
        }

        host = args[0];
        try {
            timeout = Integer.parseInt(args[1]);
        } catch(NumberFormatException e) {
            throw new NumberFormatException("Timeout must be a number");
        }

        try {
            sendPing(host, timeout);
        } catch(UnknownHostException e) {
            System.out.println("Ip Address of the host could not be determined");
        } catch(IOException e) {
            System.out.println("Something went wrong!");
        }

    }

}