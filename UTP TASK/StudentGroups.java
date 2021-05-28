import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StudentGroups {
    private Map<Integer,Student> theeGroup = new HashMap<>();
    private String _name;
    public StudentGroups(String name){
        _name=name;
    }

    public int getSize(){
        return theeGroup.size();
    }

    public void addStudent(Student student){
        theeGroup.put(student.get_StudentNumber(),student);
    }
}
