import java.util.ArrayList;
import java.util.List;

public class Department {
    private List<Teacher> allTeachers = new ArrayList<>();


    public void addTeacher(Teacher teacher) {
        allTeachers.add(teacher);
    }

    public Teacher getTeacher() {
    int num =(int)(Math.random()*allTeachers.size());
    return allTeachers.get(num);
    }
}
