import java.util.ArrayList;
import java.util.List;

public class StudentGroupExtention {
    private List<StudentGroups> Students=new ArrayList<>();

    public void addGroup(StudentGroups group){
        Students.add(group);
    }

    public StudentGroups getPerson(int one){
        return Students.get(one-1);
    }

    public int getSize() {
        return Students.size();
    }
}
