package webLicense;

/*
	Service Publisher
*/

import javax.xml.ws.Endpoint;

public class LicenseServerPublisher {
    
	// publish the service with local IP and port 9997
    public static void main(String[] args){
		System.out.println("[Starting License Server]");
        Endpoint.publish("http://192.168.000.000:1234/webLicense", new ServidorLicencaImplementacao());
		System.out.println("[License Server On]");
    }
}
