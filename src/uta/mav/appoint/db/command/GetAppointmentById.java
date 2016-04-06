package uta.mav.appoint.db.command;

import java.sql.PreparedStatement;

import uta.mav.appoint.beans.Appointment;

public class GetAppointmentById extends SQLCmd{
	Integer id;
	private Appointment appointment;
	
	public GetAppointmentById(Appointment appointment){
		this.id = appointment.getAppointmentId();
		this.appointment = appointment;
	}
	
	public void queryDB(){
		try{
		String command = "SELECT us.email,u.pName,a.date,a.start,a.end,a.type,a.description,a.studentId,a.student_email,a.student_cell,a.advisor_userId,a.student_userId FROM appointments a inner join user_advisor u on u.userId=a.advisor_userId inner join user us on us.userId= u.userId WHERE id=?";
		PreparedStatement statement = conn.prepareStatement(command);
		statement.setInt(1,id);
		res = statement.executeQuery();
		}
		catch(Exception e){
			//System.out.println(e);
		}
	}
	
	
	public void processResult(){
		try{
			while (res.next()){
				int i=1;
				appointment.setAdvisorEmail(res.getString(i++));
				appointment.setPname(res.getString(i++));
				appointment.setAdvisingDate(res.getString(i++));
				appointment.setAdvisingStartTime(res.getString(i++));
				appointment.setAdvisingEndTime(res.getString(i++));
				appointment.setAppointmentType(res.getString(i++));
				appointment.setDescription(res.getString(i++));
				appointment.setStudentId(res.getString(i++));
				appointment.setStudentEmail(res.getString(i++));
				appointment.setStudentPhoneNumber(res.getString(i++));
				appointment.setAdvisorUserId(res.getInt(i++));
				appointment.setStudentUserId(res.getInt(i++));
				
				appointment.setAppointmentId(id);
				result.add(appointment);
			}
		}
		catch(Exception e){
			//System.out.println(e);
		}
	}

}
