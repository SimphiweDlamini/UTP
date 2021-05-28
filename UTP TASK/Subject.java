import java.util.Set;

public class Subject {
    private Department forSub;
    private Teacher _lecturer;
    public Subject(Department Supervisor,Teacher Lecturer){
        forSub=Supervisor;
        _lecturer=Lecturer;
    }

    public Department getDepatment() {
        return forSub;
    }

    public Teacher get_lecturer(){
        return _lecturer;
    }

}
