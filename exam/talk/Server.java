import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
 
    public static void main(String[] args) {

        try {

            ServerSocket ss = new ServerSocket(6666);

            System.out.println("Waiting for client..");
            Socket s = ss.accept();
            System.out.println("Established connection with the client");

            DataInputStream dis = new DataInputStream(s.getInputStream());

            String msg = dis.readUTF();
            System.out.println("Message from client: " + msg);

            dis.close();
            s.close();
            ss.close();

        } catch(Exception e) {
            System.out.println("Error: " + e);
        }

    }

}