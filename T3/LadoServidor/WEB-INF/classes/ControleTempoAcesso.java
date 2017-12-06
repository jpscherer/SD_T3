public class AccessTimeManagement {
    
    private final LicenseFileManagement lfm;
    
    public AccessTimeManagement(){
        lfm = new LicenseFileManagement();
    }
    
    public void verifyLicensesTime(){
        LicenseDateTimeManagement ltm = new LicenseDateTimeManagement();
        
        // get licenses in the license file
        String[][] licenses = new String[5][2];
        licenses = lfm.getLicensesFromFileToArray();
        
        for(int i = 0; i < 5; i++){
            if(!licenses[i][0].equals("DISPONIVEL")){
                if(ltm.compareDates(ltm.getActualDatePlusSeconds(-120), ltm.getDateFromString(licenses[i][1]))){
                    licenses[i][0] = "DISPONIVEL";
                    licenses[i][1] = "00000000000000";
                }else if(ltm.compareDates(ltm.getActualDatePlusSeconds(-60), ltm.getDateFromString(licenses[i][1]))
                         && !licenses[i][0].endsWith(" (inativo)")){
                    licenses[i][0] = licenses[i][0]+" (inativo)";
                }
            }
        }
        updateLicenseFile(licenses);
    }
    
    public void updateLicenseFile(String[][] licenses){
        String fileData = "";
        // convert array data to string
        for(int i = 0; i < 5; i++){
            fileData += licenses[i][0]+"\n";
            fileData += licenses[i][1]+"\n";
        }
        // write data in the license file
        lfm.writeFile(fileData);
    }
}