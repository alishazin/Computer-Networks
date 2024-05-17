
import java.io.*;
import java.net.*;

public class Client {
    
    private static DataOutputStream dos;
    private static DataInputStream dis;

    public static void main(String[] args) throws IllegalArgumentException {

        if (args.length != 1) {
			throw new IllegalArgumentException("One argument is required. Path of the file to be sent.");
		}
        
        try {

            Socket s = new Socket("localhost",6666);
            
            dis = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());
            
            System.out.println("Sending the File to the Server");
            sendFile(args[0]);
            
            dis.close();
            dos.close();
            s.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void sendFile(String path) throws Exception {

        File file = new File(path);
        int bytesRead;
        int sizeLeft;
        byte[] buffer = new byte[4 * 1024];
        FileInputStream fis = new FileInputStream(file);

        sizeLeft = (int) file.length();
        dos.writeLong(sizeLeft);

        do {
            bytesRead = fis.read(buffer, 0, (int) Math.min(buffer.length, sizeLeft));
            dos.write(buffer, 0, bytesRead);
            dos.flush();
            sizeLeft -= bytesRead;
        } while (sizeLeft > 0 && bytesRead != -1);

        fis.close();
        System.out.println("File sent successfully");

    }

}
