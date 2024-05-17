
import java.io.*;
import java.net.*;

public class Server {

    private static DataInputStream dis = null;
    private static DataOutputStream dos = null;

    public static void main(String[] args) throws IllegalArgumentException {

        if (args.length != 1) {
            throw new IllegalArgumentException("One parameter is required. Path of the file to recieve");
        }

        try {

            ServerSocket ss = new ServerSocket(6666);

            System.out.println("Waiting for client..");
            Socket s = ss.accept();
            System.out.println("Established connection with cient");

            dis = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());

            recieveFile(args[0]);

            dis.close();
            dos.close();
            s.close();
            ss.close();

        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    private static void recieveFile(String path) throws Exception {

        int bytesRead;
        long sizeLeft;
        byte[] buffer = new byte[4 * 1024];
        FileOutputStream fos = new FileOutputStream(path);

        sizeLeft = dis.readLong();

        do {
            bytesRead = dis.read(buffer, 0, (int) Math.min(buffer.length, sizeLeft));
            fos.write(buffer, 0, bytesRead);
            sizeLeft -= bytesRead;
        } while (sizeLeft > 0 && bytesRead != -1);

        System.out.println("File is recieved and stored at " + path);
        fos.close();

    }
    
}