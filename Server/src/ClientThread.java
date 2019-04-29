import com.sun.deploy.util.StringUtils;
import java.io.IOException;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class ClientThread   extends Thread {
    private Socket socket = null;

    //private final GameServer server;
     // Create the constructor that receives a reference to the server and to the client socket
    public ClientThread (Socket socket) { this.socket = socket ;
    }

    public void run() {
        try {
            while(true){
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); //client -> server stream
            String request = in.readLine();
            String response = execute(request);
            PrintWriter out = new PrintWriter(socket.getOutputStream()); //server -> client stream
            out.println(response);
            out.flush();
            }
        }catch (IOException e) {
            System.out.println("Communication error.."+e);}
        try {
            socket.close();
        }catch (IOException e) {
            System.out.println(e);}
        //... usse try-catch-finally to handle the exceptions!
    }
    private String execute(String request) {
         // display the message: "Server received the request ... "
        System.out.println("request received: "+request);
        //String[] arrOfStr=StringUtils.splitString(request," ");
        return  request;
    }

}



