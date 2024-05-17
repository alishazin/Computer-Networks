
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    
    public static void main(String[] args) {

        try {

            Scanner scanner = new Scanner(System.in);
            String msg;

            Socket s = new Socket("localhost", 6666);
            System.out.println("Established connection with the server");

            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            while (true) {

                System.out.print("Enter Message: ");
                msg = scanner.nextLine();

                if (msg.equals("!quit")) break;
                dos.writeUTF(msg);

                System.out.println("Msg: " + dis.readUTF());

            }

            dis.close();
            dos.close();
            s.close();

        } catch(SocketException | EOFException e) {
            System.out.println("Lost connection with the server");
        } catch(IOException e) {
            e.printStackTrace();
        }

    }

}