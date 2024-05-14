
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
    
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            DataInputStream dis = new DataInputStream(s.getInputStream());
    
            while (true) {
    
                System.out.println("Client: " + dis.readUTF());
                
                System.out.print("Enter the message: ");
                msg = scanner.nextLine();
                if (msg.equals("!quit")) {
                    break;
                }
                dos.writeUTF(msg);
    
            }
    
            ss.close();

        } catch(Exception ie) {
            System.out.println("Connection lost with the client");
        }



    }
    
}
