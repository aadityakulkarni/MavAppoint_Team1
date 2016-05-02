package uta.mav.appoint.team1.facade;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import uta.mav.appoint.beans.Appointment;
import uta.mav.appoint.db.DatabaseManager;
import uta.mav.appoint.db.command.GetAppointmentById;
import uta.mav.appoint.db.command.GetUserById;
import uta.mav.appoint.db.command.SQLCmd;
import uta.mav.appoint.login.LoginUser;
import uta.mav.appoint.team3.command.Email;
import uta.mav.appoint.team3.command.NotificationCommand;
import uta.mav.appoint.team3.command.OutlookNotification;
import uta.mav.appoint.team3fall.util.Util;
import uta.mav.appoint.visitor.AppointmentVisitor;
import uta.mav.appoint.visitor.CancelAppointmentVisitor;
import uta.mav.appoint.visitor.Visitor;


public class Facade {
	
        public ArrayList<Object> cancelAppointment(int id, LoginUser user) {
		Appointment a = new Appointment();
		a.setAppointmentId(id);
		SQLCmd cmd = new GetAppointmentById(a);
		cmd.execute();
		a = (Appointment) cmd.getResult().get(0);
		
		Visitor v = new CancelAppointmentVisitor();
		user.accept(v,(Object)id);
		
		v = new AppointmentVisitor();
		ArrayList<Object> appointments = user.accept(v,null);
		
		LoginUser user1 = new LoginUser();
		user1.setUserId(a.getAdvisorUserId());
		cmd = new GetUserById(user1);
		cmd.execute();
		user1 = (LoginUser) cmd.getResult().get(0);
		
		String subject = "Advising Appointment with <PERSON> cancelled"; 
		String text = "Your appointment on " + a.getAdvisingDate() + " from " + a.getAdvisingStartTime() + " to " + a.getAdvisingEndTime() + " has been cancelled"; 
		Email email = new Email(a.getStudentEmail(), subject.replaceAll("<PERSON>", user.getPname()), text);
		Email email1 = new Email(user.getEmail(), subject.replaceAll("<PERSON>", "Student Id:" + a.getStudentid()), text);
			
		NotificationCommand notify = email;
		notify.execute();
		notify = email1;
		notify.execute();
		return appointments;
	}
    	public void scheduleAppointment(String phoneNumber, String appointmentId, String studentId, 
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
    				notifyEmail.setText("\n\nAn appointment has been set for " + a.getAdvisingDate()
    			+ " at " + a.getAdvisingStartTime() + " - " + a.getAdvisingEndTime() +
    			"\n\nTo View this appointment please click on the link below:"+
    			"\nhttp://localhost:8080/MavAppoint/viewGuestAppointment?viewApt="+result.get("appointmentId") +
    			"\n\nTo Cancel this appointment please click on the link below:"+
    			"\nhttp://localhost:8080/MavAppoint/cancel?id="+result.get("appointmentId"));
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