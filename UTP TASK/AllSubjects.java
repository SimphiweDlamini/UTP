import java.util.ArrayList;
import java.util.List;

public class AllSubjects {
    private List<Subject> allSubjects= new ArrayList<>();


    public void addSubject(Subject subject) {
        allSubjects.add(subject);
    }

    public Subject getSubject() {
        int num =(int)(Math.random()*allSubjects.size());
        return allSubjects.get(num);
    }

    public int getSize(){
        return allSubjects.size();
    }
}
