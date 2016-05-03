package uta.mav.appoint.db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import uta.mav.appoint.TimeSlotComponent;
import uta.mav.appoint.beans.*;
import uta.mav.appoint.login.*;


//Client Interface for Bridge Pattern
public class DatabaseManager {
	private DBImplInterface imp = new RDBImpl();
	
	public Integer createUser(LoginUser loginUser) throws SQLException{
		return imp.createUser(loginUser);
	}
		
	public LoginUser checkUser(GetSet set) throws SQLException{
		return imp.checkUser(set);
	}
	
	public Boolean createStudent(StudentUser studentUser) throws SQLException{
		return imp.createStudent(studentUser);
	}
	
	public ArrayList<String> getAdvisors() throws SQLException{
		return imp.getAdvisors();
	}

	public AdvisorUser getAdvisor(String email) throws SQLException{
		return imp.getAdvisor(email);
	}
	
	public StudentUser getStudent(String email) throws SQLException{
		return imp.getStudent(email);
	}
	
	public AdminUser getAdmin(String email) throws SQLException{
		return imp.getAdmin(email);
	}
	
	public FacultyUser getFaculty(String email) throws SQLException{
		return imp.getFaculty(email);
	}
	
	public ArrayList<TimeSlotComponent> getAdvisorSchedule(String name) throws SQLException{
		return imp.getAdvisorSchedule(name);
	}
	
	public ArrayList<TimeSlotComponent> getAdvisorSchedules(ArrayList<AdvisorUser> advisorUsers) throws SQLException{
		return imp.getAdvisorSchedules(advisorUsers);
	}

	public HashMap<String, String> createAppointment(Appointment a, String email) throws SQLException{
		return imp.createAppointment(a,email);
	}

	public ArrayList<Object> getAppointments(LoginUser user) throws SQLException{
		if (user instanceof AdvisorUser){
			return imp.getAppointments((AdvisorUser)user);
		}
		else if (user instanceof StudentUser){
			return imp.getAppointments((StudentUser)user);
		}
		else if (user instanceof AdminUser){
			return imp.getAppointments((AdminUser)user);
		}
		else
			return null;
	}

	public Boolean cancelAppointment(int id) throws SQLException{
		return imp.cancelAppointment(id);
	}
	public ArrayList<Object> appId(String scheduleId) throws SQLException{
		return imp.appId(scheduleId);
	}
	public String addTimeSlot(AllocateTime at) throws SQLException{
		return imp.addTimeSlot(at);
	}
	public String createSchedule(CreateScheduleBean cs) throws SQLException{
		return imp.createSchedule(cs);
	}
	public ArrayList<AppointmentType> getAppointmentTypes(String pname) throws SQLException{
		return imp.getAppointmentTypes(pname);
	}
	
	public Boolean updateAppointment(Appointment a) throws SQLException{
		return imp.updateAppointment(a);
	}

	public String deleteTimeSlot(AllocateTime at) throws SQLException{
		return imp.deleteTimeSlot(at);
	}

	public Appointment getAppointment(String date, String email) throws SQLException{
		return imp.getAppointment(date,email);
	}

	public String addAppointmentType(AdvisorUser user, AppointmentType at) throws SQLException{
		return imp.addAppointmentType(user, at);
	}
	
	public ArrayList<String> getDepartmentStrings() throws SQLException{
		return imp.getDepartmentStrings();
	}
	
	public ArrayList<String> getMajor() throws SQLException{
		return imp.getMajor();
	}
	
	public Boolean createAdvisor(AdvisorUser advisorUser) throws SQLException{
		return imp.createAdvisor(advisorUser);
	}
	
	public ArrayList<AdvisorUser> getAdvisorsOfDepartment(String department) throws SQLException{
		return imp.getAdvisorsOfDepartment(department);
	}
	
	public Boolean updateAdvisors(ArrayList<AdvisorUser> advisorUsers) throws SQLException {
		return imp.updateAdvisors(advisorUsers);
	}
	
	public ArrayList<Department> getDepartments() throws SQLException {
		return imp.getDepartments();
	}
	
	public Department getDepartmentByName(String name) throws SQLException {
		return imp.getDepartmentByName(name);
	}
	
	public Boolean updateUser(LoginUser loginUser) throws SQLException {
		return imp.updateUser(loginUser);
	}
	
	public HashMap<String, ArrayList<String>> getAppointmentsUnderAdvisor(String advisor) {
		return imp.getAppointmentsUnderAdvisor(advisor);
	}
	
	public boolean deleteAdvisor(String advisorList){
		return imp.deleteAdvisor(advisorList);
	}

	public String updateUserNotification(LoginUser user, String notification) {
		if(user instanceof AdvisorUser){
			return imp.updateNotification((AdvisorUser) user, notification);
		} else if(user instanceof StudentUser){
			return imp.updateNotification((StudentUser) user, notification);
		} else
			return null;
	}
	
	public String editAppointmentType(AdvisorUser user, AppointmentType at) throws SQLException{
		return imp.editAppointmentType(user, at);
	}
	public String cancelAppointmentType(AdvisorUser user, AppointmentType at) throws SQLException{
		return imp.cancelAppointmentType(user, at);
	}

	public Boolean editTimeSlot(AllocateTime a) {
		// TODO Auto-generated method stub
			return imp.editTimeSlot(a);
			
	}

	public Boolean resetPassword(String emailAddress, String resetKey, String dateNow) {
		// TODO Auto-generated method stub
		return imp.resetPassword(emailAddress,resetKey,dateNow);
	}

	public ArrayList<Object> getEmailResetPass(String resetKey) {
		// TODO Auto-generated method stub
		return imp.getEmailResetPass(resetKey);
	}

	public Boolean resetPass(String email, String hashpass) {
		// TODO Auto-generated method stub
		return imp.setResetPass(email,hashpass);
	}
	
	/*public String addAppointmentType(AdvisorUser user, AppointmentType at) throws SQLException{
		return imp.addAppointmentType(user, at);
	}*/
	
}

