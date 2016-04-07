package uta.mav.appoint.team3.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import uta.mav.appoint.db.DatabaseManager;
import uta.mav.appoint.email.Email;
import uta.mav.appoint.login.AdvisorUser;
import uta.mav.appoint.login.LoginUser;
import uta.mav.appoint.login.StudentUser;
import uta.mav.appoint.team3.security.RandomPasswordGenerator;
import uta.mav.appoint.team3fall.util.Util;

/**
 * Controller to register a student
 * @author Ruchi.U
 *
 */
public class RegistrationController {
	
	public static String registerStudent(String email, String phoneNumber, String studentId, String lastName, String degreeType, 
			ArrayList<String> departmentsSelected, ArrayList<String> majorsSelected, String minor) throws SQLException{
		
		if(Util.isEmpty(lastName))
			return "Invalid Last Name";
		
		if(!Util.validateEmail(email))
			return "Email Address Invalid";
		
		if(!Util.validatePhoneNumber(phoneNumber))
			return "Phone Number Invalid";
		
		StudentUser studentUser = new StudentUser();
		boolean validStudentId = Util.validateStudentId(studentId);
		boolean validUniversityId = Util.validateUniversityEmail(email);
		if(!Util.isEmpty(studentId) && !validStudentId)
			return "Invalid Student Id";
		else if(Util.isEmpty(studentId)){
			if(!validUniversityId){
				studentUser.setStudentId(null);
			}else{
				return "Invalid Student Id";
			}
		}else {
			if(validUniversityId){
				studentUser.setStudentId(Integer.parseInt(studentId));
			}else{
				return "Invalid University Email ID";
			}
			
		}
			
		studentUser.setEmail(email);
		studentUser.setPhoneNumber(phoneNumber);
		studentUser.setRole("student");
		studentUser.setLastNameInitial(lastName.substring(0,1).toUpperCase());
		studentUser.setDepartments(departmentsSelected);
		studentUser.setMajors(majorsSelected);
		studentUser.setDegType(Integer.parseInt(degreeType));
		studentUser.setNotification("yes");
		studentUser.setMinor(minor);
		
		String password = RandomPasswordGenerator.genpass();
		studentUser.setPassword(password);
		
		DatabaseManager dbm = new DatabaseManager();
		if (dbm.createStudent(studentUser)){
			Email userEmail = new Email("MavAppoint Account Created",
					"Your account for MavAppoint has been created! Your account information is:\n"
					+"Role: Student \n"
					+"Password: "+password,
					email);
			userEmail.sendMail();
		}
		
		return "Account Created Successfully. Your temporary password has been sent to your email address.";
	}
}
