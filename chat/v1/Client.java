import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;

class RecieveMsgClientThread extends Thread {

    List<String> messages = new ArrayList<>();
    DataInputStream dis; 

    public RecieveMsgClientThread(DataInputStream din) {
        this.dis = din;
    }

    public void run() {

        try {

            while (true) {
    
                String msg = this.dis.readUTF();
                this.messages.add(msg);
    
            }

        } catch (SocketException ie){
            System.out.println("\nServer Down!");
            System.exit(0);
        } catch (IOException ie){
            System.out.println("IO Error");
        }

    }

    public void close() {
        try {
            this.dis.close();
        } catch(Exception e) {
            System.out.println("Error: " + e);
        }
    }

}

public class Client {
    public static void main(String[] args){
        
        String msg, username;
        Scanner scanner = new Scanner(System.in);
        int choice;
        RecieveMsgClientThread rmThread;
        DataOutputStream dos;
        DataInputStream dis;

        try {    

            while (true) {
                System.out.print("Enter your username: ");
                username = scanner.nextLine();
    
                if (username.indexOf("|") != -1) {
                    System.out.println("Username must not contain '|'");
                    continue;
                } else if (username.toLowerCase().equals("you")) {
                    System.out.println("Username must not be you");
                    continue;
                } else {
                    break;
                }

            }

            Socket s = new Socket("localhost",1999);
            
            dos = new DataOutputStream(s.getOutputStream());
            dis = new DataInputStream(s.getInputStream());

            rmThread = new RecieveMsgClientThread(dis);
            rmThread.start();

            while (true) {

                System.out.print("Enter 1 to view messages recieved, 2 to send message, 3 to end: ");
                
                choice = scanner.nextInt();

                if (choice == 1) {
                    
                    for (String message: rmThread.messages) {

                        String[] splittedStr = message.split("\\|", 2);
                        System.out.println(splittedStr[0] + ": " + splittedStr[1]);
                        
                    }
                    
                } else if (choice == 2) {

                    System.out.print("Enter message: ");
                    scanner = new Scanner(System.in);
                    
                    msg = scanner.nextLine();
                    
                    dos.writeUTF(username + "|" + msg);
                    rmThread.messages.add("You|" + msg);

                } else if (choice == 3) {

                    System.out.println("Exiting..");
                    System.exit(0);
                   
                }

            }

        } catch (SocketException ie){
            System.out.println("\nServer down!");
            System.exit(0);
        } catch (IOException ie){
            ie.printStackTrace();
        }
    }
}