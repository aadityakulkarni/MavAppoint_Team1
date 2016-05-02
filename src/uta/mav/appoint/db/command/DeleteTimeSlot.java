package uta.mav.appoint.db.command;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import uta.mav.appoint.beans.AllocateTime;

public class DeleteTimeSlot extends SQLCmd{
	String datePrev ; 
	String date;
	String scheduleId;
	//String startTime;
	//String endTime;
	String b = "";
	
	public DeleteTimeSlot(AllocateTime at){
		scheduleId = at.getScheduleID();
		date = at.getDate();
		//startTime = at.getStartTime();
		//endTime = at.getEndTime();
	}
	
	public void queryDB(){
		try{
			String command = "DELETE a FROM advising_schedule a JOIN User_Advisor b ON a.userid=b.userid WHERE "
							+"a.schedule_id=? and a.date>=?";
			PreparedStatement statement = conn.prepareStatement(command);
			statement.setString(1,scheduleId);
			statement.setString(2,date);
			statement.executeUpdate();
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Calendar c = Calendar.getInstance();
				Date date1 = format.parse(date);
				c.setTime(date1);
				c.add(Calendar.DATE, -7);  // number of days to add
				datePrev = format.format(c.getTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String command2 = "select start_date,end_date from schedule where schedule_id=?";
			PreparedStatement statement2 = conn.prepareStatement(command2);
			statement2.setString(1,scheduleId);
			res = statement2.executeQuery();
			
			while(res.next()){
				  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				  try{
					  b = res.getString(2);
		            Date date1 = sdf.parse(res.getString(1));
		            Date date2 = sdf.parse(datePrev);
				 
		            if(date2.before(date1)){
		         //       System.out.println("Date1 is before Date2");
		            	String command1 = "DELETE FROM schedule where schedule_id=?";
		    			PreparedStatement statement1 = conn.prepareStatement(command1);
		    			statement1.setString(1,scheduleId);
		    			statement1.executeUpdate();
		    	
		            	
		            }
		            else{
		             	String command1 = "UPDATE schedule set `end_date`=? WHERE `schedule_id`=? ";
		    			PreparedStatement statement1 = conn.prepareStatement(command1);
		    			
		    			statement1.setString(1,datePrev);
		    			statement1.setString(2,scheduleId);
		    			statement1.executeUpdate();
		    		
		            }
			}catch(Exception e){
				System.err.println(e);
			}
			}
		
	
		}
		catch(SQLException sqe){
			System.err.println(sqe);
		}
	}
	
	public void processResult(){
		result.add(b);
	}

}
