package uta.mav.appoint.db.command;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import uta.mav.appoint.beans.AllocateTime;
import uta.mav.appoint.beans.CreateScheduleBean;
import uta.mav.appoint.helpers.TimeSlotHelpers;


public class CreateSchedule extends SQLCmd{
	String date;
	String starttime;
	String endtime;
	int userid;
	int count;
	String msg;
	int scheduleID;
	String endDate;
	
	public CreateSchedule(CreateScheduleBean at,int id){
		date = at.getDate();
		starttime = at.getStartTime();
		endtime = at.getEndTime();
		endDate = at.getEndDate();
		userid = id;
		count = TimeSlotHelpers.count(at.getStartTime(),at.getEndTime());
		msg = "Unable to add time slot.";
	}
	
	@Override
	public void queryDB(){
		try{
			String command = "INSERT INTO SCHEDULE (start_date,start_time,end_time,end_date) VALUES(?,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(command, PreparedStatement.RETURN_GENERATED_KEYS);
				statement.setString(1,date);
				statement.setString(2,starttime);
				statement.setString(3,endtime);
				statement.setString(4,endDate);
				
				 statement.executeUpdate();
				 ResultSet rs = statement.getGeneratedKeys();
	                if(rs.next())
	                {
	                	scheduleID = rs.getInt(1);
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
			result.add((Object)msg);
			result.add(scheduleID);
		}
		catch(Exception e){
			//System.out.printf("Add Time Slot processResult error: " + e.toString());
		}
	}	

}
