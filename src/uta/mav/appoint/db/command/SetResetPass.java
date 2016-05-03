package uta.mav.appoint.db.command;

import java.sql.PreparedStatement;
import java.util.ArrayList;

import uta.mav.appoint.beans.Appointment;


public class SetResetPass extends SQLCmd{
	
	String emailreset;
	String passwordreset;
	Boolean msg = false;
	
	public SetResetPass(String email, String pass){
		passwordreset = pass;
		emailreset = email;
	}
	
	@Override
	public void queryDB(){
		try{
			
			String command = "UPDATE `user` SET `password`=? WHERE `email` =?";
				PreparedStatement statement = conn.prepareStatement(command, PreparedStatement.RETURN_GENERATED_KEYS);
				statement.setString(1,passwordreset);
				statement.setString(2,emailreset);
				
			    statement.executeUpdate();
			
			    String command1 = "DELETE FROM `resetpass` WHERE `email` =?";
				PreparedStatement statement1 = conn.prepareStatement(command1, PreparedStatement.RETURN_GENERATED_KEYS);
				statement1.setString(1,emailreset);
				
			    statement1.executeUpdate();
			
				
				msg = true;
		}
		catch(Exception e){
			System.out.printf("Add Time Slot error : " + e.toString());
		}
	}
	
	@Override
	public void processResult(){
		try{
			
				result.add(msg);
				
			
		}
		catch(Exception e){
			//System.out.printf("Add Time Slot processResult error: " + e.toString());
		}
	}	

}
