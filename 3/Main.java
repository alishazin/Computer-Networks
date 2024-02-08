import java.io.*;
import java.net.*;
java.lang.IllegalArgumentException;
import java.util.Arrays;
import java.util.Integer;

public class Main {

	public static void sendPingReq(String ipAdrr, int timeout) throws UnknownHostException, IOException {

		InetAddress ip = InetAddress.getByName(ipAdrr);
		
		System.out.println("Sending ping request to " + ipAdrr);
		if (ip.isReachable(timeout)) {
			System.out.println("Host is reachable");
			System.out.println("Details: ");
			System.out.println("Address (+ 256. since it is unsigned bytes): " + Arrays.toString(ip.getAddress()));
			System.out.println("Host Address: " + ip.getHostAddress());
			System.out.println("isAnyLocalAddress : " +ip.isAnyLocalAddress());
			System.out.println("HashCode of the ip addr (can be negative): " + ip.hashCode());
		} else {
			System.out.println("Can't reach the host!");
		}

	}

	public static void main(String[] args) throws IllegalArgumentException {

		if (args.length != 2) {
			throw IllegalArgumentException();
		}
		
		for(int i = 0; i< args.length; i++) {
			System.out.println(String.format("Command Line Argument %d is %s", i, args[i]));
		}
		
		String ipAddress = "205.251.242.103";
		
		// example timeout = 5000
		try {
			sendPingReq(ipAddress, 5000);
		} catch(UnknownHostException e) {
			System.out.println("IP address of the host could not be determined");
		} catch(IOException e) {
			System.out.println("Something went wrong!");
		}

	}


};