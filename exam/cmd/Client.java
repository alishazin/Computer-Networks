
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
 
    public static void main(String[] args) {

        try {

            Scanner scanner = new Scanner(System.in);
            String command;

            Socket s = new Socket("localhost", 6666);
            System.out.println("Established connection with server");
    
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            DataInputStream dis = new DataInputStream(s.getInputStream());
    
            System.out.print("Enter the command: ");
            command = scanner.nextLine();

            dos.writeUTF(command);

            while (true) {
                try {
                    System.out.println(dis.readUTF());
                } catch(EOFException e) {
                    break;
                }             
            }

            dos.close();
            dis.close();
            s.close();
            
        } catch(SocketException e) {
            System.out.println("Lost connection with the client");
        } catch(IOException e) {
            System.out.println("Error");
            System.out.println(e);
        }

    }

}
