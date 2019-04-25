import java.io.IOException;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SocialNetworkClient {
    private final static String SERVER_ADDRESS = "127.0.0.1";
    private final static int PORT = 8100;
    public static void main(String[] args) throws IOException {
        SocialNetworkClient client = new SocialNetworkClient();
        Socket socket=new Socket(SERVER_ADDRESS,PORT);

        while (true) {
            String request = client.readFromKeyboard();
            if (request.equalsIgnoreCase("exit")) {
                break;
            } else {
                client.sendRequestToServer(request,socket);
            }
        }
    }
     //Implement the sendRequestToServer method

    private void sendRequestToServer(String request,Socket socket) throws IOException {
        PrintWriter out=new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader (
                new InputStreamReader(socket.getInputStream()));
       try{

        out.println(request);
        String response = in.readLine ();

        System.out.println(response);
       }catch (UnknownHostException e) {

           System.err.println("No server listening..."+e);

       }

    }

    private String readFromKeyboard() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
