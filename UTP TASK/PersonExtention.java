import java.util.ArrayList;
import java.util.List;

public class PersonExtention {
    private List<Person> People=new ArrayList<>();

    public void addPerson(Person steve){
        People.add(steve);
    }

    public Person getPerson(int one){
        return People.get(one);
    }
}
