import java.net.*;
import java.io.*;
public class Server{
    public static void main(String[] args){
        
        try {

            String msg;

            ServerSocket ss = new ServerSocket(1999);
            System.out.println("Before");
            Socket s = ss.accept();
            System.out.println("After");
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            DataInputStream dis = new DataInputStream(s.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            msg = br.readLine();

            while (!msg.equals("quit")){
                dos.writeUTF(msg);
                System.out.println("she says: " + dis.readUTF());
                msg = br.readLine();
            }

            ss.close();

        }
        catch (IOException ie){
            ie.printStackTrace();
        }
    }
}