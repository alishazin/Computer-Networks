
import java.io.*;
import java.net.*;

public class Server {
    
    public static void main(String[] args) {

        try {

            ServerSocket ss = new ServerSocket(6666);
    
            System.out.println("Waiting for client..");
            Socket s = ss.accept();
            System.out.println("Established connection with client");
    
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
    
            Runtime r = Runtime.getRuntime();
            Process p = r.exec(dis.readUTF());

            BufferedReader stdOut = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;

            while ((line = stdOut.readLine()) != null) {
                dos.writeUTF(line);
            }

            dis.close();
            dos.close();
            s.close();
            ss.close();
            
        } catch(SocketException e) {
            System.out.println("Lost connection with the client");
        } catch(IOException e) {
            System.out.println("Error");
            System.out.println(e);
        }

    }

}
