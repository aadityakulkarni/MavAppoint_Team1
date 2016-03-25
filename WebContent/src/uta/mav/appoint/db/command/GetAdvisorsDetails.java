package uta.mav.appoint.db.command;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

public class GetAdvisorsDetails extends SQLCmd {

	@Override
	public void queryDB() {
		// TODO Auto-generated method stub
		try{
			String command = "SELECT user_advisor.userId, pName FROM USER,User_Advisor WHERE ROLE='advisor' AND USER.userid = User_Advisor.userid";
			PreparedStatement statement = conn.prepareStatement(command);
			res = statement.executeQuery();	
		}
		catch(SQLException sq){
			//System.out.printf(sq.toString());
		}
	}

	@Override
	public void processResult() {
		// TODO Auto-generated method stub
		HashMap<Integer, String> advisorIdNameMap = new HashMap<Integer, String>();
		try{
			while (res.next()) {
				int key = res.getInt("userId");
				if(!advisorIdNameMap.containsKey(key))
					advisorIdNameMap.put(key, res.getString("pName"));
			}
			result.add(advisorIdNameMap);
		}
		catch(SQLException sq){
			//System.out.println(sq.toString());
		}
	}
}
