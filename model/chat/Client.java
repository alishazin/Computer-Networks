
import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Client {

    public static void main(String[] args) {

        try {

            String msg;
            Scanner scanner = new Scanner(System.in);
    
            Socket s = new Socket("localhost", 1999);
            System.out.println("Connected to the server");
    
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            DataInputStream dis = new DataInputStream(s.getInputStream());
    
            while (true) {
    
                System.out.print("Enter the message: ");
                msg = scanner.nextLine();
                if (msg.equals("!quit")) {
                    break;
                }
                dos.writeUTF(msg);

                System.out.println("Server: " + dis.readUTF());
    
            }
    
            s.close();
            
        } catch(Exception ie) {
            System.out.println("Connection lost with the server");
        }



    }
    
}
