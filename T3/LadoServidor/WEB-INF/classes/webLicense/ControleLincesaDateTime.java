/*
This class is used to manage the time used in license files and java methods

Methods:
    getDateFromString: return a date as Date format from a String format
    getActualDateString: return actual date as String format
    getActualDate: return actual date as Date format
    getActualDatePlusSeconds: return actual date summed with seconds passed as 
        parameter
    compareDates: compare two dates passed as parameters
*/

package webLicense;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LicenseDateTimeManagement {
    
    private final SimpleDateFormat format;
    
    public LicenseDateTimeManagement(){
        format = new SimpleDateFormat("yyyyMMddHHmmss");
    }
    
    public Date getDateFromString(String stringDate){
        try{
            return new Date(format.parse(stringDate).getTime());
        }catch(Exception e){
            System.err.println("Error occurred trying to parse string date to date: "+e.getMessage());
        }
        return null;
    }
    
    public String getActualDateString(){
        return format.format(new Date());
    }
    
    public Date getActualDate(){
        return new Date();
    }
    
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
    
    public boolean compareDates(Date biggerDate, Date smallerDate){
        if(biggerDate.after(smallerDate)){
            return true;
        }else{
            return false;
        }
    }
}