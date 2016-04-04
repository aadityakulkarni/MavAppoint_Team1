package uta.mav.appoint.team3.controller;

import java.util.ArrayList;
import java.util.HashMap;

import uta.mav.appoint.db.DatabaseManager;
import uta.mav.appoint.team3.command.Email;
import uta.mav.appoint.team3.command.NotificationCommand;

public class DeleteAdvisorController {

	public static boolean deleteAdvisor(String[] advisor) {
		
		String array = "";
		for(String adv: advisor){
			if (!"".equals(array))
				array += ",";
			array += adv;
		}
		
		DatabaseManager dbm = new DatabaseManager();
		HashMap<String, ArrayList<String>> advisorStudentEmailMap = dbm.getAppointmentsUnderAdvisor(array);
		boolean advisorDeleted = dbm.deleteAdvisor(array);
		if(!advisorDeleted)
			return false;
		
		Email email = new Email();
		NotificationCommand cmd;
		
		ArrayList<String> studentEmails;
		
		for(String advisorName: advisorStudentEmailMap.keySet()){
			String subject = "Appointments with " + advisorName + " cancelled.";
			String text = "All your appointments with " + advisorName + " have been cancelled as the advisor is not available";
			studentEmails = advisorStudentEmailMap.get(advisorName);
			for(String studentEmail: studentEmails){
				email.setSubject(subject);
				email.setText(text);
				email.setTo_address(studentEmail);
				cmd = email;
				cmd.execute();
			}
		}
		return true;
	}

}
