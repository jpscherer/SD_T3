package webLicense;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

public class Cliente {

	public String validate(String user) throws Exception {
		// create communication
		URL url = new URL("http://192.168.000.000:1234/webLicense?wsdl");
		QName qname = new QName("http://webLicense/", "ServidorLicencaImplementacaoService");
		Service ws = Service.create(url, qname);
		webLicense.ComunicationInterface cs = ws.getPort(webLicense.ComunicationInterface.class);
		// return webservice response
		return cs.getLicenseStatus(user);
	}

}
