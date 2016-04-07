package uta.mav.appoint.db.command;

import java.sql.PreparedStatement;

import uta.mav.appoint.beans.AppointmentType;

public class EditAppointmentType extends SQLCmd {
		String type;
		int duration;
		int userid;
		String resu;
		
		public EditAppointmentType(AppointmentType at,int id){
			type = at.getType();
			duration = at.getDuration();
			userid = id;
			resu = "";
		}
		
		@Override
		public void queryDB(){
			try{
				String command = "UPDATE Appointment_Types set duration=? where type=? and userId =?";
				PreparedStatement statement = conn.prepareStatement(command); 
				statement.setInt(3,userid);
				statement.setString(2,type);
				statement.setInt(1,duration);
				statement.executeUpdate();
				resu = "1";
				}
			catch (Exception e){
				//System.out.println(e);	
			}
			
		}
		
		@Override
		public void processResult(){
			result.add(resu);
			
		}	
}

