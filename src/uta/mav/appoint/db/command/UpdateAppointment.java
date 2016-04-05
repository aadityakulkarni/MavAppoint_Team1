package uta.mav.appoint.db.command;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import uta.mav.appoint.beans.Appointment;

public class UpdateAppointment extends SQLCmd{
	String description;
	String studentid;
	int id;
	Boolean b = false;
	String comments;
	
	public UpdateAppointment(Appointment a){
		description = a.getDescription();
		studentid = a.getStudentId();
		id = a.getAppointmentId();
		comments = a.getAdvisorComments();
	}
	
	public void queryDB(){
		try{
			String command = "UPDATE appointments SET description=?,studentId=?,comments=? where id=?";
			PreparedStatement statement = conn.prepareStatement(command);
			statement.setString(1, description);
			statement.setString(2, studentid);
			statement.setString(3, comments);
			statement.setInt(4, id);
			
			
			statement.executeUpdate();
			b=true;
		}
		catch(SQLException sq){
			//System.out.println(sq.toString());
		}
	}
	
	public void processResult(){
		result.add(b);
	}

}
