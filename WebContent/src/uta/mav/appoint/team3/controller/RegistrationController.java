package uta.mav.appoint.team3.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import uta.mav.appoint.db.DatabaseManager;
import uta.mav.appoint.email.Email;
import uta.mav.appoint.login.StudentUser;
import uta.mav.appoint.team3.security.RandomPasswordGenerator;
import uta.mav.appoint.team3fall.util.Util;

/**
 * Controller to register a student
 * @author Ruchi.U
 *
 */
public class RegistrationController {
	
	public static String registerStudent(String email, String phoneNumber, String studentId, String lastNameInitial, String degreeType, 
			ArrayList<String> departmentsSelected, ArrayList<String> majorsSelected) throws SQLException{
		
		if(!Util.validateEmail(email))
			return "Email Address Invalid";
		
		if(!Util.validatePhoneNumber(phoneNumber))
			return "Phone Number Invalid";
		
		if(!Util.validateStudentId(studentId))
			return "Invalid Student Id";
		
		StudentUser studentUser = new StudentUser();
		studentUser.setEmail(email);
		studentUser.setPhoneNumber(phoneNumber);
		studentUser.setStudentId(Integer.parseInt(studentId));
		studentUser.setRole("student");
		studentUser.setLastNameInitial(lastNameInitial);
		studentUser.setDepartments(departmentsSelected);
		studentUser.setMajors(majorsSelected);
		studentUser.setDegType(Integer.parseInt(degreeType));
		studentUser.setNotification("yes");
		
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
