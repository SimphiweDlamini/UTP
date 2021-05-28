import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DataGenrator {
    private PersonExtention allPeople=new PersonExtention();
    private StudentExtention allStudents =new StudentExtention();
    private TeacherExtention allTeachers = new TeacherExtention();
    private AllSubjects mySubjects = new AllSubjects();
    private AllDepartments allDeps=new AllDepartments();
    private StudentGroupExtention allGroups = new StudentGroupExtention();

    @Test
    public void Test(){

        try {
            Calendar utility = Calendar.getInstance();

            Calendar hire = Calendar.getInstance();
            for (int i = 1; i < 11; i++) {
                utility.clear();
                int year = 1800 + ((int) (Math.random() * 300));
                int month = ((int)( Math.random() * 12));
                int day = (int) (Math.random() * 29);
                utility.set(year, month, day);
                int hyear = 1800 + ((int)(Math.random() * 300));
                int hmonth = ((int) (Math.random() * 12));
                int hday = (int) (Math.random() * 29);
                hire.set(hyear,hmonth,hday);
                Teacher p = new Teacher("Teacher" + i, "Surname" + i, utility, Nationality.values()[(int) (Math.random() * 10)], Degree.values()[(int)(Math.random()*3)],hire);
                allPeople.addPerson(p);
                allTeachers.addPerson(p);
            }

            Department Dep1= new Department();
            Dep1.addTeacher(allTeachers.getTeacher(0));
            Dep1.addTeacher(allTeachers.getTeacher(1));
            Dep1.addTeacher(allTeachers.getTeacher(2));
            Dep1.addTeacher(allTeachers.getTeacher(3));

            Department Dep2= new Department();
            Dep2.addTeacher(allTeachers.getTeacher(4));
            Dep2.addTeacher(allTeachers.getTeacher(5));
            Dep2.addTeacher(allTeachers.getTeacher(6));
            Dep2.addTeacher(allTeachers.getTeacher(7));

            Department Dep3= new Department();
            Dep3.addTeacher(allTeachers.getTeacher(8));
            Dep3.addTeacher(allTeachers.getTeacher(9));

            allDeps.addDepartment(Dep1);
            allDeps.addDepartment(Dep2);
            allDeps.addDepartment(Dep3);



            Subject one= new Subject(Dep1, Dep1.getTeacher());
            mySubjects.addSubject(one);
            Subject two= new Subject(Dep2, Dep2.getTeacher());
            mySubjects.addSubject(two);
            Subject three= new Subject(Dep3, Dep3.getTeacher());
            mySubjects.addSubject(three);
            Subject four= new Subject(Dep1, Dep1.getTeacher());
            mySubjects.addSubject(four);
            Subject five= new Subject(Dep2, Dep2.getTeacher());
            mySubjects.addSubject(five);
            Subject six= new Subject(Dep3, Dep3.getTeacher());
            mySubjects.addSubject(six);
            Subject seven= new Subject(Dep1, Dep1.getTeacher());
            mySubjects.addSubject(seven);
            Subject eight= new Subject(Dep2, Dep2.getTeacher());
            mySubjects.addSubject(eight);
            Subject nine= new Subject(Dep3, Dep3.getTeacher());
            mySubjects.addSubject(nine);
            Subject ten= new Subject(Dep1, Dep1.getTeacher());
            mySubjects.addSubject(ten);


            for (int i = 1; i < 101; i++) {
                utility.clear();
                int year = 1800 + ((int)(Math.random() * 300));
                int month = ((int) (Math.random() * 12));
                int day = (int) (Math.random() * 29);
                utility.set(year, month, day);
                Student p = new Student("Student" + i, "StudentSurname" + i, utility, Nationality.values()[(int) (Math.random() * 10)], 2000 + i,mySubjects.getSubject());
                allPeople.addPerson(p);
                allStudents.addPerson(p);
            }
            int count = 100;
            for (int i=1;i<13;i++){
                StudentGroups ex = new StudentGroups("Group"+i);
                for(int j=1;i<101;i++) {
                    if (ex.getSize() < 9) {
                        ex.addStudent(allStudents.getPerson(count));
                        count--;
                    } else {
                       break;
                    }

                }
                allGroups.addGroup(ex);
            }
            Assert.assertEquals(allStudents.getSize(),100);
            Assert.assertEquals(allTeachers.getSize(),10);
            Assert.assertEquals(mySubjects.getSize(),10);
            Assert.assertEquals(allDeps.getSize(),3);
          //  Assert.assertEquals(allGroups.getSize(),12);

            //Reflection Testing
            Student stest=allStudents.getPerson(4);
            PolishPesel fortest=new PolishPesel(stest.getPesel());
            Method check = PolishPesel.class.getDeclaredMethod("peselCheck");
            Method retrieveDate = PolishPesel.class.getDeclaredMethod("getDate");
            Method gender = PolishPesel.class.getDeclaredMethod("getSex");
            check.setAccessible(true);
            Assert.assertEquals(check.invoke(fortest),true);
            retrieveDate.setAccessible(true);
            Assert.assertEquals(stest.getBdate(),retrieveDate.invoke(fortest));
            gender.setAccessible(true);
            System.out.println((String)gender.invoke(fortest));

        } catch (NullPointerException e){

        }catch (Exception e){
            e.printStackTrace();
        }

    }



}
