
import java.net.*;
import java.util.Scanner;
import java.io.*;

class Server {

    public static void main(String[] args) throws Exception {

        if (args.length != 1) {
			throw new Exception("One argument is required. Path to store the recieving file.");
		}

        try {
    
            System.out.println("Waiting for client");
            ServerSocket ss = new ServerSocket(1999);
            
            Socket s = ss.accept();
            System.out.println("Connected to client");
    
            DataInputStream din = new DataInputStream(s.getInputStream());
    
            long bytesLeft = din.readLong();
    
            FileOutputStream fos = new FileOutputStream(args[0]); 
            
            int bytesRead;
            byte[] buffer = new byte[4 * 1024];
    
            do {
    
                bytesRead = din.read(buffer, 0, (int) Math.min(buffer.length, bytesLeft));
                fos.write(buffer);
                bytesLeft -= bytesRead;
    
            } while (bytesLeft > 0 && bytesRead != -1);

            System.out.println("File recieved successfully at " + args[0]);
            fos.close();

        } catch(Exception e) {
            e.printStackTrace();
        }

    }

}