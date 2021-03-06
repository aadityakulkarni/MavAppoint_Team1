package uta.mav.appoint;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uta.mav.appoint.beans.AllocateTime;
import uta.mav.appoint.login.LoginUser;
import uta.mav.appoint.visitor.ManageTimeSlotVisitor;
import uta.mav.appoint.visitor.Visitor;

public class ManageTimeSlotServlet extends HttpServlet {
	HttpSession session;
	String header;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		LoginUser user = (LoginUser)session.getAttribute("user");
		if (user != null){
			header = "templates/" + user.getHeader() + ".jsp";
			try{
				AllocateTime at = new AllocateTime();
				at.setDate(request.getParameter("Date3"));
				at.setScheduleID(request.getParameter("scheduleid3"));
				at.setEmail(request.getParameter("pname3")); //using pname to find correct advisor instead of email
				Visitor v = new ManageTimeSlotVisitor();
				user.accept(v,at);
				response.setHeader("Refresh","2; URL=availability");
				request.getRequestDispatcher("/WEB-INF/jsp/views/success.jsp").forward(request,response);
				return;
			}
			catch(Exception e){
				response.setHeader("Refresh","2; URL=availability");
				request.getRequestDispatcher("/WEB-INF/jsp/views/failure.jsp").forward(request,response);
				return;
			}
		} else{
				header = "templates/header.jsp";
				request.getRequestDispatcher("/WEB-INF/jsp/views/login.jsp").forward(request, response);
				return;
		}
	}
}
