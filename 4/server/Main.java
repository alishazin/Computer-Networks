
import java.io.*;
import java.net.*;

public class Main {
    
    public static void main(String[] args) {

        try {
            ServerSocket ss = new ServerSocket(6666);
            Socket s = ss.accept();
    
            DataInputStream inputStream = new DataInputStream(s.getInputStream());
    
            String msg = (String) inputStream.readUTF();
    
            System.out.println("Message From Client: " + msg);
    
            ss.close();
        } catch(Exception e) {
            System.out.println("Error: " + e);
        }
    
    }

}