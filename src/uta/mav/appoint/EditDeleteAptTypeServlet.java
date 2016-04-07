package uta.mav.appoint;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uta.mav.appoint.beans.AppointmentType;
import uta.mav.appoint.db.DatabaseManager;
import uta.mav.appoint.login.AdminUser;
import uta.mav.appoint.login.AdvisorUser;
import uta.mav.appoint.login.LoginUser;
import uta.mav.appoint.visitor.AddAppointmentTypeVisitor;
import uta.mav.appoint.visitor.Visitor;

public class EditDeleteAptTypeServlet extends HttpServlet {
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
				response.sendRedirect("/WEB-INF/jsp/views/login.jsp");
				return;
			}
			try{
				header = "templates/" + user.getHeader() + ".jsp";
				AppointmentType at = new AppointmentType();
				
			
				String status = request.getParameter("status");
				DatabaseManager dbm = new DatabaseManager();
				AdvisorUser advisorUser = new AdvisorUser();
				advisorUser = dbm.getAdvisor(user.getEmail());
				if(status.equalsIgnoreCase("cancel")){
					at.setType(request.getParameter("type"));
					String res = dbm.cancelAppointmentType(advisorUser, at);
					if("1".equalsIgnoreCase(res)){
						response.setHeader("Refresh","2; URL=customize");
						request.getRequestDispatcher("/WEB-INF/jsp/views/success.jsp").forward(request,response);
						
					}
					else{
						response.setHeader("Refresh","2; URL=customize");
						request.getRequestDispatcher("/WEB-INF/jsp/views/failure.jsp").forward(request,response);
						
					}
				}
				else if(status.equalsIgnoreCase("edit")){

					at.setType(request.getParameter("apptypes"));
					at.setDuration(Integer.valueOf((request.getParameter("minutes"))));
					String res = dbm.editAppointmentType(advisorUser, at);
					if("1".equalsIgnoreCase(res)){
						response.setHeader("Refresh","2; URL=customize");
						request.getRequestDispatcher("/WEB-INF/jsp/views/success.jsp").forward(request,response);
						
					}
					else{
						response.setHeader("Refresh","2; URL=customize");
						request.getRequestDispatcher("/WEB-INF/jsp/views/failure.jsp").forward(request,response);
						
					}
				}
			}
			catch(Exception e){
				//System.out.printf(e.toString());
			}
		}
}
