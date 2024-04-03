import java.io.*;
import java.net.*;

public class Server {
    
    private static DataInputStream dataInputStream=null;
    private static DataOutputStream dataOutputStream=null;

    public static void main(String[] args) throws IllegalArgumentException {

        if (args.length != 1) {
			throw new IllegalArgumentException("One argument is required. Path to store the recieving file (exclude format).");
		}

        try {

            ServerSocket serverSocket = new ServerSocket(900);

            Socket socket = serverSocket.accept();
            System.out.println("Connection Established");
            
            dataInputStream= new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            
            receiveFile(args[0]);
            
            dataInputStream.close();
            dataOutputStream.close(); 
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
        
    private static void receiveFile(String path) throws Exception {
        
        int bytesRead;
        
        String file_format = dataInputStream.readUTF();

        FileOutputStream fileOutputStream = new FileOutputStream(path + "." + file_format); 
        long sizeLeft = dataInputStream.readLong();
        
        byte[] buffer = new byte[4 * 1024];

        do {
            bytesRead = dataInputStream.read(buffer, 0, (int) Math.min(buffer.length, sizeLeft));
            fileOutputStream.write(buffer);
            sizeLeft -= bytesRead; // subtracting bytes read from size
        } while (sizeLeft > 0 && bytesRead != -1);
        
        System.out.println("File is Received and Stored at " + path + "." + file_format);
        fileOutputStream.close();
    }
}