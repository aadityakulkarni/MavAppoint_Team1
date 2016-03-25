package uta.mav.appoint.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import uta.mav.appoint.TimeSlotComponent;
import uta.mav.appoint.beans.AllocateTime;
import uta.mav.appoint.beans.Appointment;
import uta.mav.appoint.beans.AppointmentType;
import uta.mav.appoint.beans.GetSet;
import uta.mav.appoint.login.AdminUser;
import uta.mav.appoint.login.AdvisorUser;
import uta.mav.appoint.login.Department;
import uta.mav.appoint.login.FacultyUser;
import uta.mav.appoint.login.LoginUser;
import uta.mav.appoint.login.StudentUser;
import uta.mav.appoint.team3fall.singleton.ConfigFileReader;

/**
 * Bridge Pattern connect to Postgres
 * @author Ruchi.U
 *
 */
public class PostgresImpl implements DBImplInterface {
	
	@Override
	public Connection connectDB() {
		try
	    {
	    Class.forName("org.postgresql.Driver");
	    String jdbcUrl = "jdbc:postgresql://";
	    jdbcUrl += ConfigFileReader.getInstance().getValue("POSTGRESQL_HOST") + ":";
	    jdbcUrl += ConfigFileReader.getInstance().getValue("POSTGRESQL_PORT") + "/";
	    jdbcUrl += ConfigFileReader.getInstance().getValue("POSTGRESQL_DATABASE");
	    String userid = ConfigFileReader.getInstance().getValue("POSTGRESQL_USER");
	    String password = ConfigFileReader.getInstance().getValue("POSTGRESQL_PASSWORD");
	    Connection conn = DriverManager.getConnection(jdbcUrl,userid,password);
	    return conn;
	    }
	    catch (Exception e){
	        System.out.println(e.toString());
	    }
	    return null;
	}
	

	@Override
	public Boolean cancelAppointment(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Object> getAppointments(AdvisorUser user) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Object> getAppointments(StudentUser user) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Object> getAppointments(AdminUser user) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> createAppointment(Appointment a, String email) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<TimeSlotComponent> getAdvisorSchedule(String name) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<TimeSlotComponent> getAdvisorSchedules(ArrayList<AdvisorUser> advisorUsers) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean createStudent(StudentUser studentUser) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getAdvisors() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AdvisorUser getAdvisor(String email) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LoginUser checkUser(GetSet set) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addTimeSlot(AllocateTime at) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<AppointmentType> getAppointmentTypes(String pname) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updateAppointment(Appointment a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteTimeSlot(AllocateTime at) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Appointment getAppointment(String d, String e) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addAppointmentType(AdvisorUser user, AppointmentType at) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getDepartmentStrings() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getMajor() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer createUser(LoginUser loginUser) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean createAdvisor(AdvisorUser advisorUser) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<AdvisorUser> getAdvisorsOfDepartment(String department) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updateAdvisors(ArrayList<AdvisorUser> advisorUsers) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Department> getDepartments() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Department getDepartmentByName(String name) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updateUser(LoginUser loginUser) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StudentUser getStudent(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AdminUser getAdmin(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FacultyUser getFaculty(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, ArrayList<String>> getAppointmentsUnderAdvisor(String advisor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteAdvisor(String advisorList) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String updateNotification(StudentUser user, String notification) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateNotification(AdvisorUser user, String notification) {
		// TODO Auto-generated method stub
		return null;
	}

}
