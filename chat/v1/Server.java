import java.net.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.*;

class UserDetails {

    static List<UserDetails> all = new ArrayList<>();

    DataInputStream dis;
    DataOutputStream dos;
    String id;

    public UserDetails(DataInputStream dis, DataOutputStream dos) {

        this.dis = dis;
        this.dos = dos;
        this.id = this.generateId();

        UserDetails.all.add(this);

    }

    public String generateId() {
        byte[] array = new byte[7];
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
    
        return generatedString;
    }

    static void removeFromAllById(String id) {
        int i = 0;

        for (UserDetails usrDetails: UserDetails.all) {
            if (usrDetails.id == id) break;
            i++;
        }

        UserDetails.all.remove(i);

    }

}

class RecieveMsgThread extends Thread {

    UserDetails usr_details; 

    public RecieveMsgThread(UserDetails usr_details) {
        this.usr_details = usr_details;
    }

    public void run() {

        try {

            while (true) {
    
                String msg = this.usr_details.dis.readUTF();
                System.out.println("Message From " + this.usr_details.id + " : " + msg);
                for (UserDetails usrDetails: UserDetails.all) {
                    if (usrDetails.id.equals(this.usr_details.id)) continue;
                    usrDetails.dos.writeUTF(msg);
                }
    
            }

        } catch (SocketException ie) {
            System.out.println("Lost connection with a user.");
            UserDetails.removeFromAllById(this.usr_details.id);
        } catch (IOException ie) {
            System.out.println("IO Exception");
        }

    }

}

class Server {  

    static int userCount = 0;
    static int offset = 0;

    public static void main(String[] args) {  
        try {
            ServerSocket ss = new ServerSocket(1999);
            
            while (true) {    
                System.out.println("Waiting..");
                Socket recieverSocket = ss.accept();
                System.out.println("Connected");

                DataInputStream dis = new DataInputStream(recieverSocket.getInputStream());
                DataOutputStream dos = new DataOutputStream(recieverSocket.getOutputStream());
                
                UserDetails usr_details = new UserDetails(dis, dos);
                
                RecieveMsgThread rmThread = new RecieveMsgThread(usr_details);
                rmThread.start();

                Server.userCount++;
            }
            // this.ss.close();
        } catch(java.net.SocketException e) {
            e.printStackTrace();
            System.out.println("----------------Chat Ended----------------");
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e);
        } 
    }  

}