import java.text.SimpleDateFormat;
import java.util.Date;

public class LicenseDateTimeManagement {
    
    private final SimpleDateFormat format;
    
    // Constructor
    public LicenseDateTimeManagement(){
        format = new SimpleDateFormat("yyyyMMddHHmmss");
    }
    
    // Return date from a string date
    public Date getDateFromString(String stringDate){
        try{
            return new Date(format.parse(stringDate).getTime());
        }catch(Exception e){
            System.err.println("Error occurred trying to parse string date to date: "+e.getMessage());
        }
        return null;
    }
    
    // Return actual datetime as String
    public String getActualDateString(){
        return format.format(new Date());
    }
    
    // Return actual datetime as Date
    public Date getActualDate(){
        return new Date();
    }
    
    // return actual date plus seconds passed as parameter
    public Date getActualDatePlusSeconds(int seconds){
        try{
            return new Date(
                    format.parse(
                            getActualDateString()).getTime()+(1000*seconds)
            );
        }catch(Exception e){
            System.err.println("Error occurred trying to get actual plus time: "+e.getMessage());
        }
        return null;
    }
    
    // compare if date one is higher then d2
    public boolean compareDates(Date biggerDate, Date smallerDate){
        if(biggerDate.after(smallerDate)){
            return true;
        }else{
            return false;
        }
    }
}