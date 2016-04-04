package uta.mav.appoint.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import uta.mav.appoint.TimeSlotComponent;
import uta.mav.appoint.beans.AllocateTime;
import uta.mav.appoint.beans.Appointment;
import uta.mav.appoint.beans.AppointmentType;
import uta.mav.appoint.beans.GetSet;
import uta.mav.appoint.login.*;

/**
 * Bridge Pattern: Implementation Interface
 * @author Ruchi.U
 *
 */
public interface DBImplInterface {
	
	public Boolean cancelAppointment(int id) throws SQLException;
	public ArrayList<Object> getAppointments(AdvisorUser user) throws SQLException;
	public ArrayList<Object> getAppointments(StudentUser user) throws SQLException;
	public ArrayList<Object> getAppointments(AdminUser user) throws SQLException;
	public HashMap<String, String> createAppointment(Appointment a, String email) throws SQLException;
	public ArrayList<TimeSlotComponent> getAdvisorSchedule(String name) throws SQLException;
	public ArrayList<TimeSlotComponent> getAdvisorSchedules(ArrayList<AdvisorUser> advisorUsers) throws SQLException;
	public Boolean createStudent(StudentUser studentUser) throws SQLException;
	public ArrayList<String> getAdvisors() throws SQLException;
	public AdvisorUser getAdvisor(String email) throws SQLException;
	public LoginUser checkUser(GetSet set) throws SQLException;
	public String addTimeSlot(AllocateTime at) throws SQLException;
	public Connection connectDB();
	public ArrayList<AppointmentType> getAppointmentTypes(String pname) throws SQLException;
	public Boolean updateAppointment(Appointment a);
	public Boolean deleteTimeSlot(AllocateTime at) throws SQLException;
	public Appointment getAppointment(String d, String e) throws SQLException;
	public String addAppointmentType(AdvisorUser user, AppointmentType at) throws SQLException;
	public ArrayList<String> getDepartmentStrings() throws SQLException;
	public ArrayList<String> getMajor() throws SQLException;
	public Integer createUser(LoginUser loginUser) throws SQLException;
	public Boolean createAdvisor(AdvisorUser advisorUser) throws SQLException;
	public ArrayList<AdvisorUser> getAdvisorsOfDepartment(String department) throws SQLException;
	public Boolean updateAdvisors(ArrayList<AdvisorUser> advisorUsers) throws SQLException;
	public ArrayList<Department> getDepartments() throws SQLException;
	public Department getDepartmentByName(String name) throws SQLException;
	public Boolean updateUser(LoginUser loginUser) throws SQLException;
	public StudentUser getStudent(String email);
	public AdminUser getAdmin(String email);
	public FacultyUser getFaculty(String email);
	public HashMap<String, ArrayList<String>> getAppointmentsUnderAdvisor(String advisor);
	public boolean deleteAdvisor(String advisorList);
	public String updateNotification(StudentUser user, String notification);
	public String updateNotification(AdvisorUser user, String notification);
	
}
