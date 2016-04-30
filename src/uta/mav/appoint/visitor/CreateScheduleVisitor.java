package uta.mav.appoint.visitor;

import java.util.ArrayList;

import uta.mav.appoint.beans.AllocateTime;
import uta.mav.appoint.beans.CreateScheduleBean;
import uta.mav.appoint.db.DatabaseManager;
import uta.mav.appoint.login.AdminUser;
import uta.mav.appoint.login.AdvisorUser;

public class CreateScheduleVisitor extends Visitor{
	
	@Override
	public ArrayList<Object> check(AdvisorUser user,Object o){
		try{
			DatabaseManager dbm = new DatabaseManager();
			
			
			
			user.setMsg(dbm.createSchedule((CreateScheduleBean)o));
			
			return null;
		}
		catch(Exception e){
			//System.out.printf("AllocateTimeVisitor error : " + e.toString() + "\n");
			return null;
		}
	}

	@Override
	public ArrayList<Object> check(AdminUser user,Object o){
		try{
			DatabaseManager dbm = new DatabaseManager();
			user.setMsg(dbm.createSchedule((CreateScheduleBean)o));
			return null;
		}
		catch(Exception e){
			//System.out.printf("AllocateTimeVisitor error : " + e.toString() + "\n");
			return null;
		}
	}
}
