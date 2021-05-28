import java.util.ArrayList;
import java.util.List;

public class StudentExtention {
    private List<Student> Students=new ArrayList<>();

    public void addPerson(Student steve){
        Students.add(steve);
    }

    public Student getPerson(int one){
       return Students.get(one-1);
    }

public int getSize() {
    return Students.size();
}
}
