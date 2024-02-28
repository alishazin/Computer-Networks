import java.net.*;
import java.io.*;

public class Client{
    public static void main(String[] args){
        
        try {
            
            String msg;

            Socket s = new Socket("localhost",1999);
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            DataInputStream dis = new DataInputStream(s.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
            msg = br.readLine();

            while (!msg.equals("quit")){
                dos.writeUTF(msg);
                System.out.println("he says:" + dis.readUTF());
                dos.flush();
                msg = br.readLine();
            }

            s.close();

        } catch (IOException ie){
            ie.printStackTrace();
        }
    }
}