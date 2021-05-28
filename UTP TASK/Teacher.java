import java.util.Calendar;

public class Teacher extends Person {
    private Degree _qualification;
    private Calendar _hiredate;
    public Teacher(String firstname,String surname,Calendar Birthdate,Nationality Country,Degree qualification,Calendar hiredate){
        super(firstname,surname,Birthdate,Country);
        _qualification=qualification;
        _hiredate=hiredate;
        // remember here to add the the Teacher to the extention class of Teachers and because it is also a person it should automatically be added to the Person extention whe you call Super

    }
    @Override
    public int compareTo(Person here){
        Person comp=this;
        if(comp.equals(here)){
            return 0;
        }else {
            return -1;
        }
    }
}
