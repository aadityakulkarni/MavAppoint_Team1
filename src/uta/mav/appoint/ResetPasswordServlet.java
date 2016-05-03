package uta.mav.appoint;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uta.mav.appoint.beans.GetSet;
import uta.mav.appoint.beans.ResetPasswordBean;
import uta.mav.appoint.db.DatabaseManager;
import uta.mav.appoint.email.Email;
import uta.mav.appoint.login.LoginUser;
import uta.mav.appoint.team3.security.RandomPasswordGenerator;

/**
 * Servlet implementation class ResetPasswordServlet
 */
public class ResetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		session = request.getSession();
		session.setAttribute("message", "");

		String resetKey = request.getParameter("resetpasskey");
		session.setAttribute("resetKey", resetKey);

		DatabaseManager db = new DatabaseManager();

		ArrayList<Object> emailDate = db.getEmailResetPass(resetKey);

		ResetPasswordBean getEmailDate = (ResetPasswordBean) emailDate.get(0);
		String email = getEmailDate.getEmail();
		String date = getEmailDate.getDate();
		
		if(!(email == null) && !(date == null)){
		Date twoDaysAgo = new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24);
		Date parsedDate = null;
		try {
			parsedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean hasTimePasssed = twoDaysAgo.before(parsedDate);

		if (hasTimePasssed) {

			session.setAttribute("email", email);
			request.getRequestDispatcher("/WEB-INF/jsp/views/resetpass.jsp").forward(request, response);
		} else {
			response.setHeader("Refresh", "2; URL=index");
			request.getRequestDispatcher("/WEB-INF/jsp/views/failure.jsp").forward(request, response);

		}
		}else {
			response.setHeader("Refresh", "2; URL=index");
			request.getRequestDispatcher("/WEB-INF/jsp/views/failure.jsp").forward(request, response);

		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		session = request.getSession();
		String emailAddress = request.getParameter("emailAddress");
		// String password = request.getParameter("password");
		GetSet sets = new GetSet();
		sets.setEmailAddress(emailAddress);
		// sets.setPassword(password);

		try {
			// call db manager and authenticate user, return value will be 0 or
			// an integer indicating a role
			DatabaseManager dbm = new DatabaseManager();
			LoginUser user = dbm.checkUser(sets);
			/*
			 * if(user.verifyPassword(HashPassword.hashpass(password))){
			 * session.setAttribute("user", user);
			 */

			if (user.getValidated() == 1) {
				Calendar currentDate = Calendar.getInstance(); // Get the
																// current date
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); // format
																								// it
																								// as
																								// per
																								// your
																								// requirement
				String dateNow = formatter.format(currentDate.getTime());
				String resetKey = RandomPasswordGenerator.genpass();
				Boolean res = dbm.resetPassword(emailAddress, resetKey, dateNow);
				if (res) {
					Email userEmail = new Email("MavAppoint Reset Password",
							"Please click on the following link to reset password\n"
									+ "http://localhost:8080/MavAppoint/resetpass?resetpasskey=" + resetKey,
							emailAddress);
					userEmail.sendMail();
				}
				response.sendRedirect("index");
			} else {
				response.sendRedirect("index");
			}
			/*
			 * } else{ //redirect back to login if authentication fails //need
			 * to add a "invalid username or password" response
			 * session.setAttribute("message", "Username or Password Invalid");
			 * request.getRequestDispatcher("/WEB-INF/jsp/views/login.jsp").
			 * forward(request,response); }
			 */
		} catch (Exception e) {
			// System.out.println(e);
			request.getRequestDispatcher("/WEB-INF/jsp/views/login.jsp").forward(request, response);
		}
	}
}
