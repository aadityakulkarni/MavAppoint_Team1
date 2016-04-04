package uta.mav.appoint.db.command;

import java.sql.PreparedStatement;

import java.sql.SQLException;

import uta.mav.appoint.login.*;

public class CreateAdvisor extends SQLCmd{

	private Integer userId;
	private String pname;
	private String name_low;
	private String name_high;
	private String notification;
	private Integer degree_types;
	private Boolean b;
	
	public CreateAdvisor(AdvisorUser advisorUser){
		this.userId = advisorUser.getUserId();
		this.pname = advisorUser.getPname();
		this.notification = advisorUser.getNotification();
		this.name_low = advisorUser.getNameLow();
		this.name_high = advisorUser.getNameHigh();
		this.degree_types = advisorUser.getDegType();
		b = false;
	}
	
	@Override
	public void queryDB() {
		try{
			String command = "INSERT INTO User_Advisor (userid,pname,notification,name_low,name_high,degree_types) "
								+"values(?,?,?,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(command);
			statement.setInt(1,userId);
			statement.setString(2,pname);
			statement.setString(3,notification);
			statement.setString(4,name_low);
			statement.setString(5,name_high);
			statement.setInt(6,degree_types);
			statement.executeUpdate();
			b = true;
		}
		catch(SQLException sqe){
			//System.out.println(sqe.toString());
		}
		
	}

	@Override
	public void processResult() {
		result.add(b);
	}

}
