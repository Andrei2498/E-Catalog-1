import com.sun.deploy.util.StringUtils;
import java.io.IOException;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class ClientThread   extends Thread {
    private Socket socket = null;
    private SocialNetwork network=new SocialNetwork();
    private Person currentPerson=new Person("",socket);
    private Boolean isConnected=false;
    //private final GameServer server;
     // Create the constructor that receives a reference to the server and to the client socket
    public ClientThread (Socket socket,SocialNetwork network) { this.socket = socket ;
    this.network=network;}

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
        String[] arrOfStr=StringUtils.splitString(request," ");
        if(arrOfStr[0].compareTo("register")==0) {
            if(arrOfStr.length==1)
                return "You need to give a name";
            return register(arrOfStr[1]);
        }
        else if(arrOfStr[0].compareTo("login")==0) {
            if(arrOfStr.length==1)
                return "You need to give a name";
            return login(arrOfStr[1]);
        }
        else if(arrOfStr[0].compareTo("friend")==0) {
            if(arrOfStr.length==1)
                return "You need to give at least a name";
            return friend(arrOfStr);
        }
        else if(arrOfStr[0].compareTo("send")==0) {
            if(arrOfStr.length==1)
                return "You need to send a message.";
            return send(arrOfStr);
        }
        else if(arrOfStr[0].compareTo("read")==0){
            return read(currentPerson.getName());
        }
        else if(arrOfStr[0].compareTo("stop")==0){
            return "Not implemented.";
        }
        return "Unknown command. Please retry!";
    }

    private String register(String name){
        Person member=new Person(name,socket);
        if(network.getMember(name).getName().compareTo("")!=0)
            return "Account already exists.";
        else if(currentPerson.getName().compareTo("")!=0) {
            return "You already have an account registered on your connection!";
        }
        else{
            network.listOfMembers.add(member);
            currentPerson.setName(member.getName());
            currentPerson.setSocket(member.getSocket());
            return "Succesfull registration";
        }
    }

    private String login(String name){
        Person member=new Person(name,socket);
        if(network.getMember(name).getSocket()!=socket)
            return "You are trying to connect to an account that doesn't belong to you!";
        if(network.listOfMembers.contains(member)){
            if(network.listOfActiveMembers.contains(member))
                return "You are already connected!";
            else{
                network.listOfActiveMembers.add(member);
                isConnected=true;
                return "You are now connected!";
            }
        }
        else
            return "The account with this username does not exist or you are already connected!";
    }

    private String friend(String[] arrOfStr){
        StringBuilder output=new StringBuilder();
        Person aux;
        if(isConnected){
            for(int i=1;i<arrOfStr.length;i++) {
                aux=network.getMember(arrOfStr[i]);
                if(aux.getName().compareTo("")==0)
                {   output.append("The user ");
                    output.append(arrOfStr[i]);
                    output.append(" does not exists.  ");
                }
                else if(aux.getName().compareTo(currentPerson.getName())==0) {
                    output.append(" You can't be friend with yourself. ");
                }
                else{
                    if(currentPerson.getListOfFriends().contains(aux))
                        output.append(currentPerson.getName()+" is already friend with "+aux.getName());
                    else {
                        currentPerson.addFriend(aux);
                        network.listOfMembers.get(network.listOfMembers.indexOf(aux)).addFriend(currentPerson);
                        }
                }
            }
        }
        else return "You must be connected to use this command!";
        output.append("Operation completed");
        return output.toString();
    }

    private String send(String[] arrOfStr){
        if(isConnected){
            StringBuilder message=new StringBuilder();
            for(int i=1;i<arrOfStr.length;i++)
                message.append(arrOfStr[i]);
            for(int j=0;j<currentPerson.getListOfFriends().size();j++) {
                currentPerson.getListOfFriends().get(j).setMessage(message.toString());
            }
            return "Message transmited!";
        }
        else return "You must be connected to use this command!";
    }

    private String read(String name) {
        if (isConnected) {
            return network.getMember(name).getMessage();
        } else return "You must be connected to use this command!";
    }
}



