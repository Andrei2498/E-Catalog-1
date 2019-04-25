import java.net.SocketAddress;
import java.util.ArrayList;
import  java.net.Socket;
import java.util.Objects;

public class Person {
    private String name;
    private Socket socket;
    private String message;
    private ArrayList<Person> listOfFriends=new ArrayList<Person>();

    public Person(String name,Socket socket) {
        this.name=name;
        this.socket=socket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return name.equals(person.name) &&
                socket.equals(person.socket) &&
                Objects.equals(message, person.message) &&
                Objects.equals(listOfFriends, person.listOfFriends);
    }

    public void addFriend(Person friend) {
        this.listOfFriends.add(friend);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Person> getListOfFriends() {
        return listOfFriends;
    }

    public void setListOfFriends(ArrayList<Person> listOfFriends) {
        this.listOfFriends = listOfFriends;
    }
}
