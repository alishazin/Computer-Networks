import java.io.*;
import java.net.*;
import java.lang.*;
import java.util.Arrays;

public class Main {

	public static void sendPingReq(String ipAdrr, int timeout) throws UnknownHostException, IOException {

		InetAddress ip = InetAddress.getByName(ipAdrr);
		
		System.out.println("Sending ping request to " + ipAdrr);
		if (ip.isReachable(timeout)) {
			System.out.println("Host is reachable");
			System.out.println("\nDetails: ");
			System.out.println("Address (+ 256. since it is unsigned bytes): " + Arrays.toString(ip.getAddress()));
			System.out.println("Host Address: " + ip.getHostAddress());
			System.out.println("isAnyLocalAddress: " +ip.isAnyLocalAddress());
			System.out.println("HashCode of the ip addr (can be negative): " + ip.hashCode());
		} else {
			System.out.println("Can't reach the host!");
		}

	}

	public static void main(String[] args) throws IllegalArgumentException, NumberFormatException {

		String ipAddress;
		int timeout;

		if (args.length != 2) {
			throw new IllegalArgumentException("Two arguments are required. IP Address and Timeout");
		}
		
		ipAddress = args[0];
		try {
			timeout = Integer.parseInt(args[1]);
		} catch(NumberFormatException e) {
			throw new NumberFormatException("Timeout should be a number");
		}
		
		try {
			sendPingReq(ipAddress, timeout);
		} catch(UnknownHostException e) {
			System.out.println("IP address of the host could not be determined");
		} catch(IOException e) {
			System.out.println("Something went wrong!");
		}

	}

};