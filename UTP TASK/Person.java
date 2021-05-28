import java.util.Calendar;
import java.util.Date;

public abstract class Person implements Comparable<Person>{
    private String _pesel;
    private String _firstname;
    private String _surname;
    private Calendar _Birthdate;
    private Nationality _country;
    public Person(String firstname,String surname,Calendar Birthdate,Nationality country){
        _firstname=firstname;
        _surname=surname;
        _Birthdate=Birthdate;
        _country=country;
        PeselGenerator forPesel = new PeselGenerator(Birthdate);
        _pesel=forPesel.getPESEL();
        // remember that when you add a new person you shouldnt forget to add this Person to the extention class of Person
    }

    public boolean equals(Person otherPerson) {
       return this._pesel==otherPerson._pesel;
    }

    @Override
    public int hashCode() {
        return _pesel.hashCode()+_firstname.hashCode()+_surname.hashCode()+_Birthdate.hashCode()+_country.hashCode();
    }

    public String getPesel(){
        return _pesel;
    }

    public Date getBdate(){
        return _Birthdate.getTime();
    }
}
