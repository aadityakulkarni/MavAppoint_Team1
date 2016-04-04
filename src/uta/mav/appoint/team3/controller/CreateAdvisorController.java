package uta.mav.appoint.team3.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import uta.mav.appoint.db.DatabaseManager;
import uta.mav.appoint.email.Email;
import uta.mav.appoint.login.AdvisorUser;
import uta.mav.appoint.team3.security.RandomPasswordGenerator;
import uta.mav.appoint.team3fall.util.Util;

/**
 * Controller for creating advisor
 * @author SDP Team 3
 *
 */
public class CreateAdvisorController {
	
	public static String createAdvisor(String emailAddress, String pName, ArrayList<String> departmentsSelected, 
			ArrayList<String> majorsSelected) throws SQLException{
		
		if(!Util.validateEmail(emailAddress))
			return "Email Address Invalid!!";	
					
		AdvisorUser advisorUser = new AdvisorUser();
		advisorUser.setEmail(emailAddress);
		advisorUser.setPname(pName);
		advisorUser.setDepartments(departmentsSelected);
		advisorUser.setPassword(RandomPasswordGenerator.genpass());
		advisorUser.setNotification("yes");
		advisorUser.setMajors(majorsSelected);
		advisorUser.setNameLow("A");
		advisorUser.setNameHigh("Z");
		advisorUser.setDegType(7);
		
		DatabaseManager dbm = new DatabaseManager();
		if (dbm.createAdvisor(advisorUser)){
			String msgSub = "Mavappoint User Information";

			String msgText ="An advisor account has been created for your email address! Your login information is:"
	            	+ "\nUsername: " + advisorUser.getPname()
	            	+ "\npassword: \""+advisorUser.getPassword()+"\" "
	            	+ "\nMavAppoint";
			String toEmail = advisorUser.getEmail();
			
			Email newMail = new Email(msgSub, msgText, toEmail);
			newMail.sendMail();
		}
		
		return "Advisor created successfully. An email has been sent to the advisor's account with his/her temporary password";
	}
}
