
import java.io.*;
import java.net.*;
import java.util.Scanner;

class RecieveMsgThread extends Thread {  

    String[] msgsRecieved = new String[10];
    int msgCount = 0;
    ServerSocket ss;

    public void run() {  
        try {
            while (true) {
                ss = new ServerSocket(8000);
                Socket recieverSocket = ss.accept();
        
                DataInputStream inputStream = new DataInputStream(recieverSocket.getInputStream());
                String msg = (String) inputStream.readUTF();
    
                this.msgsRecieved[this.msgCount] = msg;
                this.msgCount++;
            }
        } catch(Exception e) {
            System.out.println("Error: " + e);
        } 
    }  

    public void close() {
        try {
            this.ss.close();
        } catch(Exception e) {
            System.out.println("Error: " + e);
        }
    }

}  

// PORT: 8000
public class User1 {
    
    public static void main(String[] args) {

        try {

            Scanner scanner = new Scanner(System.in);
            int choice, i;
            
            RecieveMsgThread rmThread = new RecieveMsgThread();
            rmThread.start();

            // Socket s = new Socket("localhost", 6666);
            // DataOutputStream outputStream = new DataOutputStream(s.getOutputStream());
            // outputStream.writeUTF("I am User1");
            // outputStream.flush();
            // outputStream.close();

            while (true) {

                System.out.print("Enter 1 to view messages recieved, 2 to send message, 3 to end: ");
                choice = scanner.nextInt();

                if (choice == 1) {
                    for (i=0; i<rmThread.msgCount; i++) {
                        System.out.println(rmThread.msgsRecieved[i]);
                    }
                } else if (choice == 3) {
                    rmThread.close();
                    break;
                }

            }
    
        } catch(Exception e) {
            System.out.println("Error: " + e);
        }
    
    }

}