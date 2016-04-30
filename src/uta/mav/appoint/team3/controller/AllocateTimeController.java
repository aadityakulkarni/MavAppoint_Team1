package uta.mav.appoint.team3.controller;

import uta.mav.appoint.beans.AllocateTime;
import uta.mav.appoint.beans.CreateScheduleBean;
import uta.mav.appoint.helpers.TimeSlotHelpers;
import uta.mav.appoint.login.LoginUser;
import uta.mav.appoint.visitor.AllocateTimeVisitor;
import uta.mav.appoint.visitor.CreateScheduleVisitor;
import uta.mav.appoint.visitor.Visitor;

/**
 * Controller to allocate time
 * @author team3-fall.
 *
 */
public class AllocateTimeController {
		
	public static String allocateTimeToAdvisor(String date, String startTime, String endTime, LoginUser user, String repeat){
		
		int rep;
		try{
			rep = Integer.parseInt(repeat);
		}
		catch(Exception e){
			rep = 0;
		}
		CreateScheduleBean csb = new CreateScheduleBean();
		csb.setDate(date);
		csb.setEndDate(TimeSlotHelpers.addDate(csb.getDate(),rep));
		csb.setStartTime(startTime);
		csb.setEndTime(endTime);
		csb.setUserEmail(user.getEmail());
		Visitor csv = new CreateScheduleVisitor();
		user.accept(csv,(Object)csb);
		
		AllocateTime at = new AllocateTime();
		at.setDate(date);
		at.setEndTime(endTime);
		at.setStartTime(startTime);
		at.setEmail(user.getEmail());
		at.setScheduleID(user.getMsg());
		Visitor v = new AllocateTimeVisitor();
		user.accept(v,(Object)at);
		String msg = user.getMsg();
		for (int i=0;i<rep;i++){
			at.setDate(TimeSlotHelpers.addDate(at.getDate(),1));
			user.accept(v,(Object)at);
		}
		
		return "Advising hours have been set successfully";
	}
}