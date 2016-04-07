package uta.mav.appoint;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uta.mav.appoint.beans.Appointment;
import uta.mav.appoint.db.command.GetAppointmentById;
import uta.mav.appoint.db.command.SQLCmd;

public class ViewGuestAppointmentServlet extends HttpServlet {
		private static final long serialVersionUID = 2L;
		HttpSession session;
		String header;
		
		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			session = request.getSession();
			try{
				header = "templates/header.jsp";
				Appointment a = new Appointment();
				int id = Integer.parseInt(request.getParameter("viewApt"));
				a.setAppointmentId(id);
				SQLCmd cmd = new GetAppointmentById(a);
				cmd.execute();
				a = (Appointment) cmd.getResult().get(0);
				session.setAttribute("appointment", a);
				System.out.println(((Appointment)session.getAttribute("appointment")).getDescription());
				request.setAttribute("includeHeader", header);
				request.getRequestDispatcher("/WEB-INF/jsp/views/guestViewAppointment.jsp").forward(request,response);
			}
			catch(Exception e){
				//System.out.printf(e.toString());
			}
		}
}
