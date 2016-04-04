package uta.mav.appoint.team3.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;

import uta.mav.appoint.beans.Appointment;
import uta.mav.appoint.db.DatabaseManager;
import uta.mav.appoint.team3.command.Email;
import uta.mav.appoint.team3.command.NotificationCommand;
import uta.mav.appoint.team3.command.OutlookNotification;
import uta.mav.appoint.team3fall.util.Util;

/**
 * Controller Pattern to schedule appointment
 * @author SDP Team 3
 *
 */
public class ScheduleAppointmentController {
	
	public static void scheduleAppointment(String phoneNumber, String appointmentId, String studentId, 
			String description, String appType, String pName, String duration, String start, String email) throws SQLException, ParseException{
		
		//Creator Pattern
		Appointment a = new Appointment();
		a.setStudentPhoneNumber(phoneNumber);
		a.setAppointmentId(Integer.parseInt(appointmentId));
		a.setStudentId(studentId);
		a.setDescription(description);
		a.setAppointmentType(appType);
		a.setPname(pName);
		a.setDescription(description);
		int d = Integer.parseInt(duration);
		String[] parts = (start).split(" ");
		a.setAdvisingDate(parts[3] + "-" + Util.convertDate(parts[1]) + "-" + parts[2]);
		parts = parts[4].split(":");
		a.setAdvisingStartTime(parts[0] + ":" + parts[1]);
		a.setAdvisingEndTime(Util.addTime(parts[0],parts[1],d));
		
		DatabaseManager dbm = new DatabaseManager();
		Email notifyEmail = new Email();
		HashMap<String, String> result = dbm.createAppointment(a, email);
		
		if("success".equalsIgnoreCase(result.get("response"))){
			if("yes".equalsIgnoreCase(result.get("student_notify"))){
				notifyEmail.setSubject("Advising appointment with: " + a.getPname());
				notifyEmail.setText("\nAn appointment has been set for " + a.getAdvisingDate()
			+ " at " + a.getAdvisingStartTime() + " - " + a.getAdvisingEndTime());
				notifyEmail.setTo_address(email);
				NotificationCommand command = notifyEmail;
				command.execute();
				command = new OutlookNotification(email, a.getAdvisingDate(), a.getAdvisingStartTime(), a.getAdvisingEndTime(), notifyEmail.getSubject(), a.getPname());
				command.execute();
			}
			if("yes".equalsIgnoreCase(result.get("advisor_notify"))){
				notifyEmail.setSubject("Advising appointment with: " + a.getStudentId());
				notifyEmail.setText("\nAn appointment has been set for " + a.getAdvisingDate()
			+ " at " + a.getAdvisingStartTime() + " - " + a.getAdvisingEndTime());
				notifyEmail.setTo_address(result.get("advisor_email"));
				NotificationCommand command = notifyEmail;
				command.execute();
				command = new OutlookNotification(notifyEmail.getTo_address(), a.getAdvisingDate(), a.getAdvisingStartTime(), a.getAdvisingEndTime(), notifyEmail.getSubject(), a.getPname());
				command.execute();
			}
		}
	}
}
