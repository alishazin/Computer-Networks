import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server {
    
    public static void main(String[] args) {

        try {

            Scanner scanner = new Scanner(System.in);
            String msg;

            ServerSocket ss = new ServerSocket(6666);

            System.out.println("Waiting for client..");
            Socket s = ss.accept();
            System.out.println("Connection established with client");

            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            while (true) {

                System.out.println("Msg: " + dis.readUTF());

                System.out.print("Enter Message: ");
                msg = scanner.nextLine();

                if (msg.equals("!quit")) break;

                dos.writeUTF(msg);

            }

            dis.close();
            dos.close();
            s.close();
            ss.close();


        } catch(SocketException | EOFException e) {
            System.out.println("Lost connection with the client");
        } catch(IOException e) {
            e.printStackTrace();
        }

    }

}