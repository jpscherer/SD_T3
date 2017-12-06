package webLicense;

/*
	Interface
	SEI - Service Endpoint Interface
*/

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface ComunicationInterface {
    @WebMethod String getLicenseStatus(String user);
}
