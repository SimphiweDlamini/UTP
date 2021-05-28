import java.util.ArrayList;
import java.util.List;

public class AllDepartments {
    private List<Department> allDeps= new ArrayList<>();


    public void addDepartment(Department department) {
        allDeps.add(department);
    }

    public Department getDepartment() {
        int num =(int)(Math.random()*allDeps.size());
        return allDeps.get(num);
    }

    public int getSize(){
        return allDeps.size();
    }
}
