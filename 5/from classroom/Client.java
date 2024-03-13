import java.net.*;
import java.io.*;
public class Client{
public static void main(String[] args){
try{
Socket s=new Socket("localhost",1999);
DataOutputStream dos=new DataOutputStream(s.getOutputStream());
DataInputStream dis= new DataInputStream(s.getInputStream());
BufferedReader br=new BufferedReader(new
InputStreamReader(System.in));
while (!br.readLine().equals("quit")){
dos.writeUTF(br.readLine());
System.out.println("he says:"+dis.readUTF());
dos.flush();
}
s.close();
}
catch (IOException ie){
ie.printStackTrace();
}
}
}