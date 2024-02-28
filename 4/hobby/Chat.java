
import java.io.*;
import java.net.*;
import java.util.Scanner;

class RecieveMsgThread extends Thread {  

    String[] msgsRecieved = new String[10];
    int msgCount = 0;
    ServerSocket ss;

    public void run() {  
        try {
            ss = new ServerSocket(Chat.HOST_PORT);
            
            while (true) {    
                Socket recieverSocket = ss.accept();

                DataInputStream inputStream = new DataInputStream(recieverSocket.getInputStream());
                String msg = (String) inputStream.readUTF();
    
                this.msgsRecieved[this.msgCount] = msg;
                this.msgCount++;
            }
            // this.ss.close();
        } catch(java.net.SocketException e) {
            System.out.println("----------------Chat Ended----------------");
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

// PORT: 8080
public class Chat {

    static int HOST_PORT;
    static String RECIVERS_IP;
    static int RECIVERS_PORT;
    
    public static void main(String[] args) throws NumberFormatException {

        if (args.length != 3) {
			throw new IllegalArgumentException("Three arguments are required. Host PORT, Recievers IP and Recievers PORT");
		}

		try {
			HOST_PORT = Integer.parseInt(args[0]);
			RECIVERS_IP = args[1];
			RECIVERS_PORT = Integer.parseInt(args[2]);
		} catch(NumberFormatException e) {
			throw new NumberFormatException("Host PORT and Recievers PORT must be numbers");
		}

        int choice, i;
        String msg;
        Scanner scanner;
        Socket s;
        DataOutputStream outputStream;

        RecieveMsgThread rmThread = new RecieveMsgThread();
        rmThread.start();
        
        try {    

            while (true) {

                System.out.print("Enter 1 to view messages recieved, 2 to send message, 3 to end: ");
                scanner = new Scanner(System.in);
                choice = scanner.nextInt();

                if (choice == 1) {
                    
                    for (i=0; i<rmThread.msgCount; i++) {
                        System.out.println(rmThread.msgsRecieved[i]);
                    }
                    
                } else if (choice == 2) {
                    
                    s = new Socket(RECIVERS_IP, RECIVERS_PORT);
                    outputStream = new DataOutputStream(s.getOutputStream());

                    System.out.print("Enter message: ");
                    scanner = new Scanner(System.in);
                    msg = scanner.nextLine();
                    outputStream.writeUTF(msg);
                    
                    outputStream.flush();
                    outputStream.close();

                } else if (choice == 3) {
                   
                    rmThread.close();
                    break;
                   
                }

            }
    
        } catch(java.net.ConnectException e) {
            System.out.println("Can't reach to the other side");
            rmThread.close();
        } catch(Exception e) {
            System.out.println("Error: " + e);
        }
    
    }

}