import java.util.Calendar;

public class Student extends Person {
     private int _StudentNumber;
     private Subject _subject;
     public Student(String firstname, String surname, Calendar Birthdate, Nationality Country,int StudentNumber,Subject subject){
          super(firstname,surname,Birthdate,Country);
          _StudentNumber=StudentNumber;
          _subject=subject;
     }

     public int get_StudentNumber() {
          return _StudentNumber;
     }

     @Override
     public int compareTo(Person here){
          Student comp=(Student)here;
          if (this._StudentNumber==comp.get_StudentNumber()){
               return 0;
          }else {
               return -1;
          }
     }
}
