import java.net.*;
import java.io.*;

public class Client{
    public static void main(String[] args){
        
        try {
            
            String msg = "";

            Socket s = new Socket("localhost",1999);
            System.out.println("Connection Established With Server");
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            DataInputStream dis = new DataInputStream(s.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.print("Enter Message: ");
                msg = br.readLine();
                if (msg.equals("!quit")) break;
                dos.writeUTF(msg);
                
                System.out.println("Message From Server:" + dis.readUTF());
            }

            s.close();

        } catch (SocketException ie){
            System.out.println("Can't connect to the Server.");
        } catch (IOException ie){
            ie.printStackTrace();
        }
    }
}