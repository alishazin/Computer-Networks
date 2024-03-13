import java.net.*;
import java.io.*;
public class Server{
public static void main(String[] args){
try{
ServerSocket ss =new ServerSocket(1999);
Socket s=ss.accept();
DataOutputStream dos=new DataOutputStream(s.getOutputStream());
DataInputStream dis= new DataInputStream(s.getInputStream());
BufferedReader br=new BufferedReader(new
InputStreamReader(System.in));
while (!br.readLine().equals("quit")){
dos.writeUTF(br.readLine());
System.out.println("she says:"+dis.readUTF());
dos.writeUTF(br.readLine());

}
ss.close();
}
catch (IOException ie){
ie.printStackTrace();
}
}
}