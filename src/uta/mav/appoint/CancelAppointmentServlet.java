package uta.mav.appoint;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uta.mav.appoint.beans.Appointment;
import uta.mav.appoint.db.DatabaseManager;
import uta.mav.appoint.db.command.GetAppointmentById;
import uta.mav.appoint.db.command.SQLCmd;
import uta.mav.appoint.email.Email;

public class CancelAppointmentServlet extends HttpServlet {
	private static final long serialVersionUID = 2L;
	HttpSession session;
	String header;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * session = request.getSession(); LoginUser user =
		 * (LoginUser)session.getAttribute("user");
		 *//*
			 * if (user == null){ user = new LoginUser();
			 * session.setAttribute("user", user);
			 * response.sendRedirect("/WEB-INF/jsp/views/login.jsp"); return; }
			 */
		try {
			header = "templates/header.jsp";
			DatabaseManager dbm = new DatabaseManager();
			Appointment a = new Appointment();
			int id = Integer.parseInt(request.getParameter("id"));
			a.setAppointmentId(id);
			SQLCmd cmd = new GetAppointmentById(a);
			cmd.execute();
			a = (Appointment) cmd.getResult().get(0);

			Boolean result = dbm.cancelAppointment(id);
			if (result == true) {
				response.setHeader("Refresh", "2; URL=index");
				request.getRequestDispatcher("/WEB-INF/jsp/views/success.jsp").forward(request, response);
			} else {
				response.setHeader("Refresh", "2; URL=/");
				request.getRequestDispatcher("/WEB-INF/jsp/views/failure.jsp").forward(request, response);
			}

		} catch (Exception e) {
			// System.out.printf(e.toString());
		}
		/*header = "templates/header.jsp";
		request.setAttribute("includeHeader", header);
		request.getRequestDispatcher("/WEB-INF/jsp/views/index.jsp").forward(request, response);*/
	}

}
