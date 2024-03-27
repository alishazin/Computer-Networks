import java.io.*;
import java.net.*;

class Server {
    public static void main(String args[]) throws IOException {
        try {
            String str;

            ServerSocket server=new ServerSocket(6555);
            System.out.println("Waiting For Client..");
            Socket socket=server.accept();
            System.out.println("Connection Established..");
            
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            str=dis.readUTF();
            
            Runtime r=Runtime.getRuntime();
            Process p=r.exec(str);
            
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String fullstring = "";
            String string = null;
            while ((string = stdInput.readLine()) != null) {
                fullstring = fullstring.concat("\n");
                fullstring = fullstring.concat(string);
            }
            dos.writeUTF(fullstring);

        }
        catch(IOException e) {
            System.out.println("Error");
            System.out.println(e);
        }
    }
}