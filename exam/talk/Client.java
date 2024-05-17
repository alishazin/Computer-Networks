import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    
    public static void main(String[] args) {

        try {

            String msg;
            Scanner scanner = new Scanner(System.in);

            Socket s = new Socket("localhost", 6666);
            System.out.println("Established Connecttion with the server");

            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            System.out.print("Enter Message: ");
            msg = scanner.nextLine();

            dos.writeUTF(msg);

            dos.flush();
            dos.close();
            s.close();

        } catch(Exception e) {
            System.out.println("Error: " + e);
        }

    }

}