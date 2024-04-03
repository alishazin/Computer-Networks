import java.io.*;
import java.net.*;

public class Server {
    
    private static DataInputStream dataInputStream=null;
    private static DataOutputStream dataOutputStream=null;

    public static void main(String[] args) {

        // Here we define Server Socket running on port 900
        try {

            ServerSocket serverSocket = new ServerSocket(900);

            Socket clientSocket = serverSocket.accept();
            System.out.println("Connected");
            
            dataInputStream= new DataInputStream(clientSocket.getInputStream());
            dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
            
            receiveFile("./img-copy.jpeg");
            
            dataInputStream.close();
            dataOutputStream.close(); 
            clientSocket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
        
    // receive file function
    private static void receiveFile(String fileName) throws Exception {
        
        int bytes = 0;
        
        FileOutputStream fileOutputStream = new FileOutputStream(fileName); 
        long size = dataInputStream.readLong();
        
        // read file size
        byte[] buffer = new byte[4 * 1024];

        bytes = dataInputStream.read(
            buffer,
            0,
            (int) Math.min(buffer.length, size)
        );
        System.out.println(bytes + " " + size);
        
        while (size > 0 && bytes != -1) {
            fileOutputStream.write(buffer, 0, bytes);
            size -= bytes; // read upto file size
            bytes = dataInputStream.read(
                buffer,
                0,
                (int)Math.min(buffer.length, size)
            );
            System.out.println(bytes + " " + size);
        }
        
        System.out.println("File is Received");
        fileOutputStream.close();
    }
}