public class RequestLicense {
    
    String[][] licenses;
    LicenseFileManagement lfm;
    
    public RequestLicense(){
        lfm = new LicenseFileManagement();
        licenses = new String[5][2];
    }
    
    public String request(String user){
        
        if(user.equals("") || user == "" || user == null || user.equals(null)){
            return "Usuario nao pode ser vazio!";
        }
        
        // variables
        boolean insertUser = true;
        boolean freeLicense = false;
        licenses = lfm.getLicensesFromFileToArray();
        
        // refresh license file
        AccessTimeManagement atm = new AccessTimeManagement();
        atm.verifyLicensesTime();
        
        // go through the licenses
        for(int i = 0; i < 5; i++){
            // verify if user is already licensed
            if(licenses[i][0].startsWith(user)){
                insertUser = false;
                break;
            }
            // verify if there is a free license
            if(licenses[i][0].equals("DISPONIVEL")){
                freeLicense = true;
            }
        }
        
        // return when its not possible to license this user
        if(!insertUser){
            return "Usuario " + user + " ja tem licenca";
        }else if(!freeLicense){
            return "Nao ha licencas disponiveis no momento";
        }
        
        // if user is not licensied and there is free space than license user
        if(insertUser && freeLicense){
            lfm.insertUser(user);
        }
        return "Licenca foi ativada com sucesso!";
    }
    
    public String renew(String user){
        
        if(user.equals("") || user == "" || user == null || user.equals(null)){
            return "Usuario nao pode ser vazio!";
        }
        
        // variables
        boolean userExists = false;
        
        // refresh license file
        AccessTimeManagement atm = new AccessTimeManagement();
        atm.verifyLicensesTime();
        ControleTempoAcesso ldtm = new ControleTempoAcesso();

        // get licenses
        licenses = lfm.getLicensesFromFileToArray();
        
        // go through the licenses
        for(int i = 0; i < 5; i++){
            // verify if user is already licensed
            if(licenses[i][0].startsWith(user)){
                userExists = true;
                licenses[i][0] = user;
                licenses[i][1] = ldtm.getActualDateString();
                atm.updateLicenseFile(licenses);
                break;
            }
        }
        if(!userExists){
            return "Usuario " + user + " nao possui licenca para renovar!";
        }
        return "Licenca renovada com sucesso!";
    }
    
    public String releaseLicense(String user){
        
        if(user.equals("") || user == "" || user == null || user.equals(null)){
            return "Usuario nao pode ser vazio!";
        }
        
        // variables
        boolean userExists = false;
        
        // refresh license file
        AccessTimeManagement atm = new AccessTimeManagement();
        atm.verifyLicensesTime();
        ControleTempoAcesso ldtm = new ControleTempoAcesso();

        // get licenses
        licenses = lfm.getLicensesFromFileToArray();
        
        // go through the licenses
        for(int i = 0; i < 5; i++){
            // verify if user is already licensed
            if(licenses[i][0].startsWith(user)){
                userExists = true;
                licenses[i][0] = "DISPONIVEL";
                licenses[i][1] = "000000000000";
                atm.updateLicenseFile(licenses);
                break;
            }
        }
        if(!userExists){
            return "Usuario " + user + " nao possui licenca para liberar!";
        }
        return "Licenca liberada com sucesso!";
    }
}