package uta.mav.appoint;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uta.mav.appoint.db.DatabaseManager;
import uta.mav.appoint.team3.security.HashPassword;

public class SetNewPasswordServlet extends HttpServlet {
	HttpSession session;
	String header;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		String pass;
		String confpass;
		
			header = "templates/header.jsp";
			try{
				pass = request.getParameter("password");
				confpass = request.getParameter("confpassword");
				if(pass.equals(confpass)){
				
					String hashpass = HashPassword.hashpass(pass);
					String email = (String) session.getAttribute("email");
					DatabaseManager db = new DatabaseManager();
					Boolean resetPassSuccess = db.resetPass(email,hashpass);
					
					if(resetPassSuccess){
				response.setHeader("Refresh","2; URL=login");
				request.getRequestDispatcher("/WEB-INF/jsp/views/success.jsp").forward(request,response);
				return;
					}
					else{
						response.setHeader("Refresh","2; URL=login");
						request.getRequestDispatcher("/WEB-INF/jsp/views/failure.jsp").forward(request,response);
						return;
							
					}
				}
				else{
					response.setHeader("Refresh","2; URL=login");
					request.getRequestDispatcher("/WEB-INF/jsp/views/failure.jsp").forward(request,response);
					return;
						
				}
			}
			catch(Exception e){
				response.setHeader("Refresh","2; URL=login");
				request.getRequestDispatcher("/WEB-INF/jsp/views/failure.jsp").forward(request,response);
				return;
			}
	}
}
