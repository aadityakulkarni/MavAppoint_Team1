package uta.mav.appoint;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uta.mav.appoint.beans.AppointmentType;
import uta.mav.appoint.db.DatabaseManager;
import uta.mav.appoint.login.AdvisorUser;
import uta.mav.appoint.login.LoginUser;

/**
 * Servlet implementation class CustomizeServlet
 */
public class CustomizeServlet extends HttpServlet {
	private static final long serialVersionUID = 2L;
	HttpSession session;
	String header;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		LoginUser user = (LoginUser)session.getAttribute("user");
		if (user == null){
			user = new LoginUser();
			session.setAttribute("user", user);
		}
		try{
				header = "templates/" + user.getHeader() + ".jsp";
				DatabaseManager dbm = new DatabaseManager();
				if("advisor".equals(user.getRole())){
					ArrayList<AppointmentType> ats = dbm.getAppointmentTypes(user.getPname());
					session.setAttribute("appointmenttypes", ats);
				}
				session.setAttribute("notification", user.getNotification());
		}
		catch(Exception e){
			//System.out.printf(e.toString());
		}
		request.setAttribute("includeHeader", header);
		request.getRequestDispatcher("/WEB-INF/jsp/views/customize.jsp").forward(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		session = request.getSession();
		LoginUser user = (LoginUser)session.getAttribute("user");
		try {
			String notification = request.getParameter("notify");
			if(!notification.equals(user.getNotification())){
				user.setNotification(notification);
				DatabaseManager dbm = new DatabaseManager();
				String resp = dbm.updateUserNotification(user, notification);
				if(resp != null){
					session.setAttribute("user", user);
					response.setHeader("Refresh","2; URL=customize");
					request.getRequestDispatcher("/WEB-INF/jsp/views/success.jsp").forward(request,response);
				} else {
					response.setHeader("Refresh","2; URL=customize");
					request.getRequestDispatcher("/WEB-INF/jsp/views/failure.jsp").forward(request,response);
				}
			}
		} catch(Exception e){
			
		}
	}

}
