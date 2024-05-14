
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
    
            System.out.print("Enter the command: ");
            msg = scanner.nextLine();
            dos.writeUTF(msg);    
    
            s.close();
            
        } catch(Exception ie) {
            System.out.println("Connection lost with the server");
        }



    }
    
}
