import java.net.*;
import java.io.*;
public class Server{
    public static void main(String[] args){
        
        try {

            String msg = "";

            ServerSocket ss = new ServerSocket(1999);

            System.out.println("Waiting for Client..");
            Socket s = ss.accept();
            System.out.println("Connection Established With Client");

            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            DataInputStream dis = new DataInputStream(s.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.println("Message From Client: " + dis.readUTF());
                
                System.out.print("Enter Message: ");
                msg = br.readLine();
                if (msg.equals("!quit")) break;
                dos.writeUTF(msg);
            }

            ss.close();

        }
        catch (EOFException ie){
            System.out.println("Can't connect to the Client.");
        } catch (IOException ie){
            ie.printStackTrace();
        }
    }
}