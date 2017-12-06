/*
	Class used to call the get licenses implementation to show the licenses
		situation on the screen
*/

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class VerifyLicensesHTML extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		// set up the classes to access the data
		LicenseFileManagement lfm = new LicenseFileManagement();
		AccessTimeManagement atm = new AccessTimeManagement();
		
		// refresh licenses
		atm.verifyLicensesTime();
		
		// display the html screen
		try {
			out.println("<html>");
			out.println("<head><title>Gerenciador de Licencas</title></head>");
			out.println("<body>");
			out.println("<h1>LICENCAS</h1>");
			out.println("<pre>"+lfm.getLicensesFromFileToString()+"</pre><br>");
			out.println("<form action = \".\">");
			out.println("<input type = \"submit\" value = \"Voltar\" />");
			out.println("</form>");
			out.println("</body></html>");
		} finally {
			out.close();
		}
	
	}
	
}