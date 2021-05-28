import java.util.Calendar;
import java.util.Date;

public class PolishPesel {
    private static String _pesel;

    public PolishPesel(String pesel){
     _pesel=pesel;
    }

    private static boolean peselCheck(){
        return _pesel.length()==12;
    }

    private static Date getDate(){
        Calendar utility = Calendar.getInstance();
        String bdate = _pesel.substring(0,6);
        int year=Integer.parseInt(bdate.substring(0,2));
        int centurycheck=Integer.parseInt(bdate.substring(2,3));
        int month=Integer.parseInt(bdate.substring(2,4));
        int day=Integer.parseInt(bdate.substring(4,6));
        if(centurycheck == 2){
            month-=20;
            year+=2000;
        }else if(centurycheck == 4){
            month-=40;
            year+=2100;
        }else if (centurycheck == 8){
            month-=80;
            year+=1800;
        }else{
            year+=1900;
        }
        utility.clear();
        utility.set(year,month,day);
        return utility.getTime();
    }

    private static String getSex(){
        int sex=Integer.parseInt(_pesel.substring(9,10));
        if (sex/2==0){
            return "male";
        }else{
            return "female";
        }
    }
}
