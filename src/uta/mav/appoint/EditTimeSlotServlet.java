package uta.mav.appoint;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uta.mav.appoint.beans.AllocateTime;
import uta.mav.appoint.beans.Appointment;
import uta.mav.appoint.db.DatabaseManager;
import uta.mav.appoint.db.command.GetAppointmentById;
import uta.mav.appoint.db.command.SQLCmd;
import uta.mav.appoint.email.Email;
import uta.mav.appoint.login.LoginUser;
import uta.mav.appoint.team3.controller.AllocateTimeController;
import uta.mav.appoint.visitor.EditTimeSlotVisitor;
import uta.mav.appoint.visitor.Visitor;

public class EditTimeSlotServlet extends HttpServlet {
	HttpSession session;
	String header;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		LoginUser user = (LoginUser)session.getAttribute("user");
		if (user != null){
			header = "templates/" + user.getHeader() + ".jsp";
			try{
				String scheduleId = request.getParameter("scheduleid2");
				AllocateTime at = new AllocateTime();
				AllocateTime at1 = new AllocateTime();
				at.setDate(request.getParameter("Date2"));
				at.setStartTime(request.getParameter("StartTime2"));
				at.setEndTime(request.getParameter("EndTime2"));
				at.setEmail(request.getParameter("pname2")); //using pname to find correct advisor instead of email
				at.setScheduleID(scheduleId);
				at1=at;
				DatabaseManager dbm = new DatabaseManager();
				ArrayList<Object> res = new ArrayList<Object>(); 
				res = dbm.appId(scheduleId);
				Appointment app = new Appointment();
				for(int i = 0; i<res.size();i++){
					app = (Appointment) res.get(i);
					int id = app.getAppointmentId();
					String email = app.getStudentEmail();
					String date = app.getAdvisingDate();
					String start = app.getAdvisingStartTime();
					String end = app.getAdvisingEndTime();
					Boolean result = dbm.cancelAppointment(id);
					if(result){
					Email userEmail = new Email("MavAppoint Appointment Cancled",
							"Your appointment on "+date+" from "+start+" to "+end+" has been cancled as the advisor schedule is changed.\n",email);
					userEmail.sendMail();
					}
				}
				
				String lastDate = dbm.deleteTimeSlot(at1);
				
				
				Calendar cal1 = new GregorianCalendar();
			     Calendar cal2 = new GregorianCalendar();

			     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			     Date date1,date2;
				try {
					date1 = sdf.parse(at1.getDate());
					 cal1.setTime(date1);
				     date2 = sdf.parse(lastDate);
				     cal2.setTime(date2);

				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    
			   int weeklyRepeate = daysBetween(cal1.getTime(),cal2.getTime());
			String msg = AllocateTimeController.allocateTimeToAdvisor(at1.getDate(), at1.getStartTime(), at1.getEndTime(), user, String.valueOf(weeklyRepeate));
				
			
			  
			  
				
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
public  int daysBetween(Date d1, Date d2){
    return (int)( ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24))/7);
}
}
