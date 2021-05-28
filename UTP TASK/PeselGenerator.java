import java.util.Calendar;

public class PeselGenerator {
    private Calendar _Birthdate;
    private String finalReturn;

    public PeselGenerator(Calendar Birthdate){
        _Birthdate=Birthdate;
    }

    public String getPESEL(){
        int year=_Birthdate.get(Calendar.YEAR);
        int month=_Birthdate.get(Calendar.MONTH);
        int day=_Birthdate.get(Calendar.DAY_OF_MONTH);
        if(year/100==20){
            month+=20;
        }
        if(year/100==18){
            month+=80;
        }
        if(year/100==21){
            month+=40;
        }
        int random4=1000+(int)(Math.random()*8000);
        String pyear=Integer.toString(year);
        String ryear=pyear.substring(2);
        String rmonth=Integer.toString(month);
        String rday=Integer.toString(day);
        String rrandom4=Integer.toString(random4);
        String pesel=ryear+rmonth+rday+rrandom4;
        int control=0;
        for(int i=0;i<10;i++){
            if(i==0) {
                control += Integer.parseInt(pesel.substring(i, i + 1));
            }
            if(i==1) {
                control += Integer.parseInt(pesel.substring(i, i + 1))*3;
            }
            if(i==2) {
                control += Integer.parseInt(pesel.substring(i, i + 1))*5;
            }
            if(i==3) {
                control += Integer.parseInt(pesel.substring(i, i + 1))*7;
            }
            if(i==4) {
                control += Integer.parseInt(pesel.substring(i, i + 1))*9;
            }
            if(i==5) {
                control += Integer.parseInt(pesel.substring(i, i + 1));
            }
            if(i==6) {
                control += Integer.parseInt(pesel.substring(i, i + 1))*3;
            }
            if(i==7) {
                control += Integer.parseInt(pesel.substring(i, i + 1)) *5;
            }
            /*
            if(i==8) {
                control += Integer.parseInt(pesel.substring(i(, i + 1)) *7;
            }
            if(i==9){
                control += Integer.parseInt(pesel.substring(i)) *9;
            }
            */
        }

        finalReturn = ryear+rmonth+rday+rrandom4+control;
        String.format("d{12}",finalReturn);

        return finalReturn;


    }
}
