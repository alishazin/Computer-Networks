
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Main {
 
    public static void main(String[] args) {

        try {
            String msg;
            Scanner scanner = new Scanner(System.in);

            Socket s = new Socket("localhost", 6666);
            DataOutputStream outputStream = new DataOutputStream(s.getOutputStream());

            System.out.print("Enter Message: ");
            msg = scanner.nextLine();
            
            outputStream.writeUTF(msg);
            outputStream.flush();
            outputStream.close();
            
        } catch(Exception e) {
            System.out.println("Error: " + e);
        }

    }

}