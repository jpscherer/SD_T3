/*
	Class used to call the request new license implementation
*/

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class RequestNewLicenseHTML extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// get paramenter data
		String st = request.getParameter("user");
		
		RequestLicense rl = new RequestLicense();
		st = rl.request(st);
		
		// display the html screen
		try {
			out.println("<html>");
			out.println("<head><title>Solicita Licenca</title></head>");
			out.println("<body>");
			out.println("<h1>"+st+"</h1><br><br>");
			out.println("<form action = \".\">");
			out.println("<input type = \"submit\" value = \"Voltar\" />");
			out.println("</form>");
			out.println("</body></html>");
		} finally {
			out.close();
		}
	
	}
}