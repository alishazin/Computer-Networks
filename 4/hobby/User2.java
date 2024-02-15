
import java.io.*;
import java.net.*;

// PORT: 6666
public class User2 {
    
    public static void main(String[] args) {

        try {

            Socket s = new Socket("localhost", 8000);
            DataOutputStream outputStream = new DataOutputStream(s.getOutputStream());
            outputStream.writeUTF("I am User2 Msg 1");
            outputStream.writeUTF("I am User2 Msg 2");
            outputStream.flush();
            outputStream.close();

            ServerSocket ss = new ServerSocket(6666);
            Socket recieverSocket = ss.accept();
    
            DataInputStream inputStream = new DataInputStream(recieverSocket.getInputStream());
            String msg = (String) inputStream.readUTF();
            System.out.println("Message: " + msg);
            ss.close();
    
        } catch(Exception e) {
            System.out.println("Error: " + e);
        }
    
    }

}