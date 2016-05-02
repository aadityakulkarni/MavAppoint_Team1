package uta.mav.appoint.db.command;

import java.sql.PreparedStatement;
import java.util.ArrayList;

import uta.mav.appoint.beans.Appointment;


public class AppId extends SQLCmd{
	
	String msg;
	String scheduleID;
	Appointment set = new Appointment();
	
	public AppId(String scheduleId){
		scheduleID = scheduleId;
		msg = "Unable to add time slot.";
	}
	
	@Override
	public void queryDB(){
		try{
			
			String command = "SELECT distinct ap.Id,ap.`student_email`,ap.date,ap.start,ap.end FROM `advising_schedule` ad inner join appointments ap on ad.appid=ap.Id where ad.schedule_id=? and ad.date > DATE(NOW())";
				PreparedStatement statement = conn.prepareStatement(command, PreparedStatement.RETURN_GENERATED_KEYS);
				statement.setString(1,scheduleID);
				res = statement.executeQuery();
				
				while(res.next()){
					set.setAppointmentId(res.getInt(1));
					set.setStudentEmail(res.getString(2));
					set.setAdvisingDate(res.getString(3));
					set.setAdvisingStartTime(res.getString(4));
					set.setAdvisingEndTime(res.getString(5));
				}
				
				msg = "Time slot has been added.";
		}
		catch(Exception e){
			System.out.printf("Add Time Slot error : " + e.toString());
		}
	}
	
	@Override
	public void processResult(){
		try{
			
				result.add(set);
				
			
		}
		catch(Exception e){
			//System.out.printf("Add Time Slot processResult error: " + e.toString());
		}
	}	

}
