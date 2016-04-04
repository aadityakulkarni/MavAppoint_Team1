package uta.mav.appoint.team3.controller;

import uta.mav.appoint.beans.AllocateTime;
import uta.mav.appoint.helpers.TimeSlotHelpers;
import uta.mav.appoint.login.LoginUser;
import uta.mav.appoint.visitor.AllocateTimeVisitor;
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
		AllocateTime at = new AllocateTime();
		at.setDate(date);
		at.setEndTime(endTime);
		at.setStartTime(startTime);
		at.setEmail(user.getEmail());
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