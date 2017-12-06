package webLicense;

import javax.jws.WebService;

@WebService(endpointInterface = "webLicense.ComunicationInterface")
public class LicenseServerImpl implements LicenseServer{
    public String getLicenseStatus(String user){
		String[][] licenses = new String[5][2];
		
		AccessTimeManagement atm = new AccessTimeManagement();
		atm.verifyLicensesTime();
		
		LicenseFileManagement lfm = new LicenseFileManagement();
		licenses = lfm.getLicensesFromFileToArray();
		
		for(int i = 0; i < 5; i++){
            if(licenses[i][0].equals(user)){
				return "ACTIVE";
            }else if(licenses[i][0].equals(user+" (inativo)")){
				return "INACTIVE";
			}
		}
		
        return "NOTFOUND";
    }
}
