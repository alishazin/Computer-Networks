import java.io.*;
import java.net.*;
import java.util.Scanner;

class Client {
    public static void main(String args[]) throws IOException {
        try {
            Scanner s = new Scanner(System.in);
            String str;

            Socket socket=new Socket("127.0.0.1",6555);

            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream din = new DataInputStream(socket.getInputStream());
            
            System.out.print("Enter The Command: ");
            str=s.nextLine();
            
            dos.writeUTF(str);

            System.out.println(din.readUTF());
            socket.close();
            s.close();
        } catch(IOException e) {
            System.out.println("Error");
            System.out.println(e);
        }
    }
}