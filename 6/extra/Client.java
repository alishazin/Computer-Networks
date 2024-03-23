import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.awt.*;

class Client {
    public static void main(String args[]) throws IOException {
        try {
            Scanner s = new Scanner(System.in);
            String str;
            String[] splittedStr;

            Socket socket=new Socket("127.0.0.1",6555);

            DataInputStream din = new DataInputStream(socket.getInputStream());
            
            Robot bot = new Robot();
            while (true) {
                str = din.readUTF();
                splittedStr = str.split(",", 2);
                System.out.println(splittedStr[0] + "," + splittedStr[1]);
                bot.mouseMove(Integer.parseInt(splittedStr[0]) + 100, Integer.parseInt(splittedStr[1]) + 100);
            }

        }
        catch(AWTException e) {
            System.out.println("AWTException");
            System.out.println(e);
        } 
        catch(IOException e) {
            System.out.println("Error");
            System.out.println(e);
        }
    }
}