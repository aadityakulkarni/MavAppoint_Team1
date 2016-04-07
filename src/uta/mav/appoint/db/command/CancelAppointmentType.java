package uta.mav.appoint.db.command;

import java.sql.PreparedStatement;

import uta.mav.appoint.beans.AppointmentType;

public class CancelAppointmentType extends SQLCmd {
		String type;
		int duration;
		int userid;
		String resu;
		
		public CancelAppointmentType(AppointmentType at,int id){
			type = at.getType();
			duration = at.getDuration();
			userid = id;
			resu = "";
		}
		
		@Override
		public void queryDB(){
			try{
				String command = "Delete from Appointment_Types where type=? and userid = ?";
				PreparedStatement statement = conn.prepareStatement(command); 
				statement.setInt(2,userid);
				statement.setString(1,type);
				//statement.setInt(1,duration);
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

