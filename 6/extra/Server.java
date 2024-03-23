import java.io.*;
import java.net.*;
import java.util.concurrent.TimeUnit;
import java.awt.*;

class Server {
    public static void main(String args[]) throws IOException {
        try {
            String str;

            ServerSocket server=new ServerSocket(6555);
            Socket socket=server.accept();
            
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            // Robot bot = new Robot();
            // bot.mouseMove(100, 100);  
            
            while (true) {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) b.getX();
                int y = (int) b.getY();
                // System.out.println(y + ", " + x);
                dos.writeUTF(x + "," + y);
                TimeUnit.MILLISECONDS.sleep(500);
            }

        }
        // catch(AWTException e) {
        //     System.out.println("AWTException");
        //     System.out.println(e);
        // }
        catch(InterruptedException e) {
            System.out.println("InterruptedException");
            System.out.println(e);
        }
        catch(IOException e) {
            System.out.println("IOException");
            System.out.println(e);
        }
    }
}