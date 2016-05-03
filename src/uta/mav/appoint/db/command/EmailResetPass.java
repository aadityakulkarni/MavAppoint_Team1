package uta.mav.appoint.db.command;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import uta.mav.appoint.beans.ResetPasswordBean;

public class EmailResetPass extends SQLCmd{
	String reset;
	ResetPasswordBean rpb = new ResetPasswordBean();
	
	public EmailResetPass(String resetKey) {
		// TODO Auto-generated constructor stub
		reset = resetKey;
	}

	@Override
	public void queryDB() {
		// TODO Auto-generated method stub
		try{
			String command = "select email,datevalid from resetpass where uniqueid=?";
			PreparedStatement statement = conn.prepareStatement(command);
			statement.setString(1,reset);
			
			res = statement.executeQuery();
			while(res.next()){
			rpb.setEmail(res.getString(1));
			rpb.setDate(res.getString(2));
			}
			
		}
		catch(Exception e){
			//System.out.println(e);
		}
	}

	@Override
	public void processResult() {
		// TODO Auto-generated method stub
		
			result.add(rpb);
			
			
	
}

}
