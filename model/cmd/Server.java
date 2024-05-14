
import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Server {

    public static void main(String[] args) {

        try {

            String msg;
            Scanner scanner = new Scanner(System.in);
    
            ServerSocket ss = new ServerSocket(1999);
    
            System.out.println("Waiting for client");
            Socket s = ss.accept();
            System.out.println("Connected to the client");
    
            DataInputStream dis = new DataInputStream(s.getInputStream());

            String command = dis.readUTF();
            System.out.println(command);
    
            Runtime r = Runtime.getRuntime();
            Process p = r.exec(command);
    
            ss.close();

        } catch(Exception ie) {
            System.out.println("Connection lost with the client");
        }



    }
    
}
