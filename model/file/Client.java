
import java.net.*;
import java.util.Scanner;
import java.io.*;

class Client {

    public static void main(String[] args) throws Exception {

        if (args.length != 1) {
			throw new Exception("One argument is required. Path of the file to be sent.");
		}

        try {
            
            Socket s = new Socket("localhost", 1999);
            System.out.println("Connected to server");
    
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            
            File file = new File(args[0]);
            FileInputStream fin = new FileInputStream(file); 
            long bytesLeft = (int) file.length();
    
            dos.writeLong(bytesLeft);
            
            int bytesRead;
            byte[] buffer = new byte[4 * 1024];
    
            do {

                bytesRead = fin.read(buffer, 0, (int) Math.min(buffer.length, bytesLeft));
                dos.write(buffer, 0, bytesRead);
                dos.flush();
                bytesLeft -= bytesRead;
    
            } while (bytesLeft > 0 && bytesRead != -1);

            System.out.println("File at " + args[0] + " sent successfully");
            fin.close();
            dos.close();

        } catch(Exception e) {
            e.printStackTrace();
        }

    }

}