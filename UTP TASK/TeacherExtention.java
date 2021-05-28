import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TeacherExtention {
    private List<Teacher> Teacher=new ArrayList<>();

    public void addPerson(Teacher steve){
        Teacher.add(steve);
    }

    public Teacher getTeacher(int one){
        return Teacher.get(one);
    }

    public int getSize() {
        return Teacher.size();
    }
}
