package uta.mav.appoint;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uta.mav.appoint.beans.AllocateTime;
import uta.mav.appoint.db.DatabaseManager;
import uta.mav.appoint.helpers.TimeSlotHelpers;
import uta.mav.appoint.login.AdvisorUser;
import uta.mav.appoint.login.LoginUser;
import uta.mav.appoint.team3.controller.AllocateTimeController;
import uta.mav.appoint.visitor.AllocateTimeVisitor;
import uta.mav.appoint.visitor.AppointmentVisitor;
import uta.mav.appoint.visitor.Visitor;

public class AllocateTimeServlet extends HttpServlet {
	HttpSession session;
	String header;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		try{
			AdvisorUser user = (AdvisorUser)session.getAttribute("user");
			if (user != null && user instanceof AdvisorUser){
					header = "templates/" + user.getHeader() + ".jsp";
					DatabaseManager dbm = new DatabaseManager();
					ArrayList<TimeSlotComponent> schedules = dbm.getAdvisorSchedule(user.getPname());
					if (schedules.size() != 0){
						session.setAttribute("schedules", schedules);
					}
					Visitor v = new AppointmentVisitor();
					ArrayList<Object> appointments = user.accept(v,null);
					if (appointments.size() != 0){
						session.setAttribute("appointments", appointments);
					}
				}
			else{
				header = "templates/header.jsp";
				request.setAttribute("includeHeader", header);
				request.getRequestDispatcher("/WEB-INF/jsp/views/login.jsp").forward(request, response);
			}
		}
		catch(Exception e){
			header = "templates/header.jsp";
			//System.out.println(e);
		}
		request.setAttribute("includeHeader", header);
		request.getRequestDispatcher("/WEB-INF/jsp/views/allocate_time.jsp").forward(request, response);

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		session = request.getSession();
		String date = request.getParameter("opendate");
		String startTime = request.getParameter("starttime");
		String endTime = request.getParameter("endtime");
		LoginUser user = (LoginUser)session.getAttribute("user");
		String repeat = request.getParameter("repeat");
		
		try{
			String msg = AllocateTimeController.allocateTimeToAdvisor(date, startTime, endTime, user, repeat);
			response.setHeader("Refresh","2; URL=availability");
			request.getRequestDispatcher("/WEB-INF/jsp/views/success.jsp").forward(request,response);
		}
		catch(Exception e){
			user.setMsg("Unable to set advising hours");
			response.setHeader("Refresh","2; URL=availability");
			request.getRequestDispatcher("/WEB-INF/jsp/views/failure.jsp").forward(request,response);
		}
	}
}
