package uta.mav.appoint;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uta.mav.appoint.db.command.GetAdvisorsDetails;
import uta.mav.appoint.db.command.SQLCmd;
import uta.mav.appoint.login.LoginUser;
import uta.mav.appoint.team3.controller.DeleteAdvisorController;

/**
 * Servlet implementation class DeleteAdvisorServlet
 */
public class DeleteAdvisorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String header;
	String page;
	HttpSession session;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		session = request.getSession();
		LoginUser user = (LoginUser)session.getAttribute("user");
		
		if(user == null){
			header = "templates/header.jsp";
			page = "/WEB-INF/jsp/views/login.jsp";
		} else {
		
			HashMap<Integer, String> advisorDetails = new HashMap<Integer, String>();
			SQLCmd cmd = new GetAdvisorsDetails();
			cmd.execute();
			try {
				advisorDetails = (HashMap<Integer, String>) cmd.getResult().get(0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(!advisorDetails.isEmpty())
				session.setAttribute("advisorDetails", advisorDetails);
			header = "templates/" + user.getHeader() + ".jsp";
			page = "/WEB-INF/jsp/views/delete_advisor.jsp";
		}
		
		request.setAttribute("includeHeader", header);
		request.getRequestDispatcher(page).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] advisor = request.getParameterValues("advisor");
		
		boolean advisorDeleted = DeleteAdvisorController.deleteAdvisor(advisor);
		if(advisorDeleted){
			response.setHeader("Refresh","2; URL=delete_advisor");
			request.getRequestDispatcher("/WEB-INF/jsp/views/success.jsp").forward(request,response);
		} else {
			response.setHeader("Refresh","2; URL=delete_advisor");
			request.getRequestDispatcher("/WEB-INF/jsp/views/failure.jsp").forward(request,response);
		}
	}

}
