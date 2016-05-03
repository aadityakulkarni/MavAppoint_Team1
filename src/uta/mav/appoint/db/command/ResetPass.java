package uta.mav.appoint.db.command;

import java.sql.PreparedStatement;

public class ResetPass extends SQLCmd{

	String email;
	String reset;
	String dateReset;
	Boolean msg = false;
	
	public ResetPass(String emailAddress, String resetKey, String date) {
		// TODO Auto-generated constructor stub
		email = emailAddress;
		reset = resetKey;
		dateReset = date;
	}

	@Override
	public void queryDB() {
		// TODO Auto-generated method stub
		try{
			String command = "insert into resetpass values(?,?,?)";
			PreparedStatement statement = conn.prepareStatement(command);
			statement.setString(1,email);
			statement.setString(2,reset);
			statement.setString(3,dateReset);
			
			 statement.execute();
			msg=true;
		}
		catch(Exception e){
			//System.out.println(e);
		}
	}

	@Override
	public void processResult() {
		// TODO Auto-generated method stub
	
		result.add(msg);
		}

}
