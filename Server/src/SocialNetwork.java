import java.util.ArrayList;

public class SocialNetwork {
    public ArrayList<Person> listOfMembers=new ArrayList<Person>();
    public ArrayList<Person> listOfActiveMembers=new ArrayList<>();

    public Person getMember(String name){
       for(Person p :listOfMembers)
           if(p.getName().compareTo(name)==0)
               return p;
       return new Person("",null);
    }
}
