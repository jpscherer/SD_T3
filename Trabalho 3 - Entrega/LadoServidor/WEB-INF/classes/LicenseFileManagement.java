import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;

public class LicenseFileManagement {
    
    String path;
    
    public LicenseFileManagement(){
        path = "C:\\apache-tomcat-9.0.1\\webapps\\validatelicense\\WEB-INF\\classes\\licenses\\license.lic";
    }
    
    public String getLicensesFromFileToString(){
        // variables
        String[][] licenses = new String[5][2];
        licenses = getLicensesFromFileToArray();
        String fileData = "";
        
        // convert array data to string
        int cont = 0;
        for(int i = 0; i < 5; i++){
            cont++;
            fileData += "Licenca " + cont + " = " + licenses[i][0] + "\n";
        }
        return fileData;
    }
    
    public String[][] getLicensesFromFileToArray(){
                
        String licenses[][] = new String[5][2];
        String user = "";
        String time = "";
        
        try{
            // File
            File f = new File(path);
            // File Reader
            FileReader fr = new FileReader(f);
            // Buffer Reader
            BufferedReader br = new BufferedReader(fr);
            // Read File Rows
            for(int i = 0; i < 5; i++){
                // user row
                user = br.readLine();
                if(user == null){
                    user = "DISPONIVEL";
                }
                // set user
                licenses[i][0] = user;
                
                // date row
                time = br.readLine();
                if(time == null){
                    time = "00000000000000";
                }
                // set user time
                licenses[i][1] = time;
            }
            
        }catch(Exception e){
            System.err.println("Erro ao ler arquivo: "+e.getMessage());
        }
        return licenses;
    }
    
    public void writeFile(String data){
        try{
            // File
            File f = new File(path);
            // File Writer
            FileWriter fw = new FileWriter(f);
            // Buffered Writer
            fw.write(data);
            fw.flush();
            fw.close();
        }catch(Exception e){
            System.err.println("Erro ao escrever no arquivo: "+e.getMessage());
        }
    }
    
    public void insertUser(String user){
        // set up the LDTM
LicenseDateTimeManagement ldtm = new LicenseDateTimeManagement();
        // get licenses from file
        String[][] licenses = new String[5][2];
        licenses = getLicensesFromFileToArray();
        // for each license, verify which one is free to use this to write 
        // the user 
        for(int i = 0; i < 5; i++){
            if(licenses[i][0].equals("DISPONIVEL")){
                licenses[i][0] = user;
                licenses[i][1] = ldtm.getActualDateString();
                break;
            }
        }
        // call ATM to update the file formated
        ControleTempoAcesso atm = new ControleTempoAcesso();
        atm.updateLicenseFile(licenses);
    }
}