package uta.mav.appoint.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import uta.mav.appoint.PrimitiveTimeSlot;
import uta.mav.appoint.TimeSlotComponent;
import uta.mav.appoint.beans.AllocateTime;
import uta.mav.appoint.beans.Appointment;
import uta.mav.appoint.beans.AppointmentType;
import uta.mav.appoint.beans.CreateAdvisorBean;
import uta.mav.appoint.beans.CreateScheduleBean;
import uta.mav.appoint.beans.GetSet;
import uta.mav.appoint.beans.RegisterBean;
import uta.mav.appoint.beans.ResetPasswordBean;
import uta.mav.appoint.db.command.*;
import uta.mav.appoint.flyweight.TimeSlotFlyweightFactory;
import uta.mav.appoint.helpers.TimeSlotHelpers;
import uta.mav.appoint.login.*;
import uta.mav.appoint.prototype.PrototypeMgr;
import uta.mav.appoint.prototype.RDBCmdPrototype;
import uta.mav.appoint.prototype.UpdatePrototype;
import uta.mav.appoint.team3fall.singleton.ConfigFileReader;
import uta.mav.appoint.team3fall.util.Util;

/**
 * Bridge Pattern Concrete class: Connect to MySQL
 * @author Ruchi.U
 *
 */
public class RDBImpl implements DBImplInterface{

	public Connection connectDB(){
		try
	    {
	    Class.forName("com.mysql.jdbc.Driver").newInstance();
	    String jdbcUrl = "jdbc:mysql://";
	    jdbcUrl += ConfigFileReader.getInstance().getValue("MYSQL_SERVER") + ":";
	    jdbcUrl += ConfigFileReader.getInstance().getValue("MYSQL_PORT") + "/";
	    jdbcUrl += ConfigFileReader.getInstance().getValue("MYSQL_DATABASE");
	    String userid = ConfigFileReader.getInstance().getValue("MYSQL_USER");
	    String password = ConfigFileReader.getInstance().getValue("MYSQL_PASSWORD");
	    Connection conn = DriverManager.getConnection(jdbcUrl,userid,password);
	    return conn;
	    }
	    catch (Exception e){
	        System.out.println(e.toString());
	    }
	    return null;
	}
	
			
	//user login checking, check username and password against database
	//then return role if a match is found
	//using command pattern
	public LoginUser checkUser(GetSet set) throws SQLException{
		LoginUser user = null;
		try{
			SQLCmd cmd = new CheckUser(set.getEmailAddress());
			cmd.execute();
			//System.out.println("Result = "+cmd.getResult());
			user = (LoginUser)(cmd.getResult()).get(0);
			
		}
		catch(Exception e){
			//System.out.println(e+" -- FOUND IN -- "+this.getClass().getSimpleName());
		}
		return user;
	}
	
	public Boolean updateAppointment(Appointment a){
		
		try{
			SQLCmd cmd = new UpdateAppointment(a);
			cmd.execute();
			//System.out.println("Finished update");

			//System.out.println("Found id " + a.getAppointmentId());
			cmd = new GetAppointmentById(a);
			cmd.execute();
			//System.out.println("Finished getting appointment");
			
			return true;
		}
		catch(Exception e){
			//System.out.println(e+"-- Found in -- "+ this.getClass().getSimpleName());
		}
		return false;
	}
	
	public Boolean createStudent(StudentUser studentUser){
		try{
			SQLCmd cmd = new CreateUser(studentUser);
			cmd.execute();
			//System.out.println("Created User"+cmd.getResult());
			
			cmd = new CreateStudent(studentUser);
			cmd.execute();
			//System.out.println(cmd.getResult());
			return (Boolean)cmd.getResult().get(0);
		}
		catch(Exception e){
			//System.out.println(e+" -- Found In -- "+this.getClass().getName());
			return false;
		}
	}
	
	//using command pattern
	public ArrayList<String> getAdvisors() throws SQLException{
		ArrayList<String> arraylist = new ArrayList<String>();
		try{
			SQLCmd cmd = new GetAdvisors();
			cmd.execute();
			ArrayList<Object> tmp = cmd.getResult();
			for (int i=0;i<tmp.size();i++){
				arraylist.add(((String)tmp.get(i)));
			}
		}
		catch(Exception sq){
			//System.out.printf(sq.toString());
		}
		return arraylist;
	}
	
	public ArrayList<TimeSlotComponent> getAdvisorSchedule(String name){
		ArrayList<TimeSlotComponent> array = new ArrayList<TimeSlotComponent>();
		try {
			Connection conn = this.connectDB();
			PreparedStatement statement;
			if (name.equals("all")){
			String command = "SELECT pname,date,start,end,id,schedule_id FROM user,Advising_Schedule,User_Advisor "
							+ "WHERE user.userid=User_Advisor.userid AND user.userid=Advising_Schedule.userid AND studentId is null";
			statement = conn.prepareStatement(command);
			}
			else{
				String command = "SELECT pname,date,start,end,id,schedule_id FROM USER,Advising_Schedule,User_Advisor "
								+ "WHERE USER.userid=User_Advisor.userid AND USER.userid=Advising_Schedule.userid AND USER.userid=Advising_Schedule.userid AND User_Advisor.pname=? AND studentId is null";
				statement = conn.prepareStatement(command);
				statement.setString(1,name);
			}	
			ResultSet res = statement.executeQuery();
			while(res.next()){
				//Use flyweight factory to avoid build cost if possible
				PrimitiveTimeSlot set = (PrimitiveTimeSlot)TimeSlotFlyweightFactory.getInstance().getFlyweight(res.getString(1)+"-"+res.getString(2),res.getString(3));
				set.setName(res.getString(1));
				set.setDate(res.getString(2));
				set.setStartTime(res.getString(3));
				set.setEndTime(res.getString(4));
				set.setUniqueId(res.getInt(5));
				set.setScheduleId(res.getInt(6));
				array.add(set);
			}
			array = TimeSlotHelpers.createCompositeTimeSlot(array);
			conn.close();
		}
		catch(Exception e){
			//System.out.printf(e.toString());
		}
		return array;
	}

	public ArrayList<TimeSlotComponent> getAdvisorSchedules(ArrayList<AdvisorUser> advisorUsers){
		ArrayList<TimeSlotComponent> timeSlots = new ArrayList<TimeSlotComponent>();
		try {
			Connection conn = this.connectDB();
			PreparedStatement statement;
			for(int i=0; i<advisorUsers.size(); i++)
			{
				String command = "SELECT pname,date,start,end,id FROM USER,Advising_Schedule,User_Advisor "
								+ "WHERE USER.userid=User_Advisor.userid AND USER.userid=Advising_Schedule.userid AND USER.userid=Advising_Schedule.userid AND User_Advisor.pname=? AND studentId is null";
				statement = conn.prepareStatement(command);
				statement.setString(1,advisorUsers.get(i).getPname());
				ResultSet res = statement.executeQuery();
				while(res.next()){
					//Use flyweight factory to avoid build cost if possible
					PrimitiveTimeSlot set = (PrimitiveTimeSlot)TimeSlotFlyweightFactory.getInstance().getFlyweight(res.getString(1)+"-"+res.getString(2),res.getString(3));
					set.setName(res.getString(1));
					set.setDate(res.getString(2));
					set.setStartTime(res.getString(3));
					set.setEndTime(res.getString(4));
					set.setUniqueId(res.getInt(5));
					timeSlots.add(set);
				}
			}	
			timeSlots = TimeSlotHelpers.createCompositeTimeSlot(timeSlots);
			conn.close();
		}
		catch(Exception e){
			//System.out.printf(e.toString());
		}
		return timeSlots;
	}

	public HashMap<String, String> createAppointment(Appointment appointment,
			String email) {
		HashMap<String, String> result = new HashMap<String, String>();
		int student_id = 0;
		int advisor_id = 0;
		try {
			Connection conn = this.connectDB();
			PreparedStatement statement;
			String command = "SELECT userid from user where email=?";
			statement = conn.prepareStatement(command);
			statement.setString(1, email);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				student_id = rs.getInt(1);
			}

			command = "SELECT notification from user_student where userId=?";
			statement = conn.prepareStatement(command);
			statement.setInt(1, student_id);
			rs = statement.executeQuery();
			while (rs.next()) {
				result.put("student_notify", rs.getString("notification"));
			}

			command = "SELECT userid FROM User_Advisor WHERE User_Advisor.pname=?";
			statement = conn.prepareStatement(command);
			statement.setString(1, appointment.getPname());
			rs = statement.executeQuery();
			while (rs.next()) {
				advisor_id = rs.getInt(1);
			}

			command = "SELECT notification from user_advisor where userId=?";
			statement = conn.prepareStatement(command);
			statement.setInt(1, advisor_id);
			rs = statement.executeQuery();
			while (rs.next()) {
				result.put("advisor_notify", rs.getString("notification"));
			}

			command = "SELECT email from user where userId=?";
			statement = conn.prepareStatement(command);
			statement.setInt(1, advisor_id);
			rs = statement.executeQuery();
			while (rs.next()) {
				result.put("advisor_email", rs.getString("email"));
			}

			// check for slots already taken
			command = "SELECT COUNT(*) FROM Advising_Schedule WHERE userid=? AND date=? AND start=? AND end=? AND studentId is not null";
			statement = conn.prepareStatement(command);
			statement.setInt(1, advisor_id);
			statement.setString(2, appointment.getAdvisingDate());
			statement.setString(3, appointment.getAdvisingStartTime());
			statement.setString(4, appointment.getAdvisingEndTime());
			rs = statement.executeQuery();
			while (rs.next()) {
				if (rs.getInt(1) < 1) {
					command = "INSERT INTO Appointments (id,advisor_userid,student_userid,date,start,end,type,studentId,description,student_email,student_cell)"
							+ "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
					statement = conn.prepareStatement(command, PreparedStatement.RETURN_GENERATED_KEYS);
					statement.setInt(1, appointment.getAppointmentId());
					statement.setInt(2, advisor_id);
					statement.setInt(3, student_id);
					statement.setString(4, appointment.getAdvisingDate());
					statement.setString(5, appointment.getAdvisingStartTime());
					statement.setString(6, appointment.getAdvisingEndTime());
					statement.setString(7, appointment.getAppointmentType());
					if (!Util.isEmpty(appointment.getStudentId())) {
						statement.setString(8,appointment.getStudentId());
					}else{
						statement.setString(8,null);
					}
					statement.setString(9, appointment.getDescription());
					statement.setString(10, email);
					statement
							.setString(11, appointment.getStudentPhoneNumber());

					// System.out.println("Update about to execute");
					statement.executeUpdate();
					// System.out.println("Should have set "+appointment.getStudentPhoneNumber());
					// System.out.println("Update should have executed");
					int appid =0;
					ResultSet rs1 = statement.getGeneratedKeys();
	                if(rs1.next())
	                {
	                	appid = rs1.getInt(1);
	                }
					
					command = "UPDATE Advising_Schedule SET studentId=?, appid=? where userid=? AND date=? and start >= ? and end <= ?";
					statement = conn.prepareStatement(command);
					String studentId = Util.isEmpty(appointment.getStudentId())?"-1":appointment.getStudentId();
					statement.setString(1, studentId);
					statement.setInt(2, appid);
					statement.setInt(3, advisor_id);
				
					
					statement.setString(4, appointment.getAdvisingDate());
					statement.setString(5, appointment.getAdvisingStartTime());
					statement.setString(6, appointment.getAdvisingEndTime());
					statement.executeUpdate();
					result.put("response", "success");
					result.put("appointmentId", appointment.getAppointmentId()
							+ "");

				}
			}
			conn.close();
		} catch (Exception e) {
			System.out.printf(e.toString());
		}
		return result;
	}

	public ArrayList<Object> getAppointments(AdvisorUser user){
		ArrayList<Object> Appointments = new ArrayList<Object>();
		try{
			Connection conn = this.connectDB();
			PreparedStatement statement;
//			String command = "SELECT User_Advisor.pname,User_Advisor.email,date,start,end,type,id,Appointments.description,studentId,Appointments.student_email FROM USER,Appointments,User_Advisor "
					String command = "SELECT User_Advisor.pname,User.email,date,start,end,type,id,Appointments.description,studentId,Appointments.student_email,Appointments.student_cell,Appointments.comments FROM USER,Appointments,User_Advisor "
						+ "WHERE USER.email=? AND user.userid=Appointments.advisor_userid AND User_Advisor.userid=Appointments.advisor_userid";
			statement = conn.prepareStatement(command);
			statement.setString(1, user.getEmail());
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				Appointment set = new Appointment();
				set.setPname(rs.getString(1));
				set.setAdvisorEmail(rs.getString(2));
				set.setAdvisingDate(rs.getString(3));
				set.setAdvisingStartTime(rs.getString(4));
				set.setAdvisingEndTime(rs.getString(5));
				set.setAppointmentType(rs.getString(6));
				set.setAppointmentId(rs.getInt(7));
				set.setDescription(rs.getString(8));
				set.setStudentId(rs.getString(9));
				set.setStudentEmail(rs.getString(10));
				set.setStudentPhoneNumber(rs.getString(11));
				set.setAdvisorComments(rs.getString(12));
				Appointments.add(set);
			}
			conn.close();
		}
		catch(Exception e){
			//System.out.printf(e.toString());
		}
		
		return Appointments;
	}

	public ArrayList<Object> getAppointments(StudentUser user){
		ArrayList<Object> Appointments = new ArrayList<Object>();
		try{
			Connection conn = this.connectDB();
			PreparedStatement statement;
//			String command = "SELECT User_Advisor.pname,User_Advisor.email,date,start,end,type,id,description,student_email FROM USER,Appointments,User_Advisor "
					String command = "SELECT User_Advisor.pname,User.email,date,start,end,type,id,description,student_email, student_cell,User_Advisor.userid FROM USER,Appointments,User_Advisor "
						+ "WHERE USER.email=? AND user.userid=Appointments.student_userid AND User_Advisor.userid=Appointments.advisor_userid";
			statement = conn.prepareStatement(command);
			statement.setString(1, user.getEmail());
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				Appointment set = new Appointment();
				set.setPname(rs.getString(1));
				//set.setAdvisorEmail(rs.getString(2));
				set.setAdvisingDate(rs.getString(3));
				set.setAdvisingStartTime(rs.getString(4));
				set.setAdvisingEndTime(rs.getString(5));
				set.setAppointmentType(rs.getString(6));
				set.setAppointmentId(rs.getInt(7));
				set.setDescription(rs.getString(8));
				set.setStudentId("Advisor only");
				set.setStudentEmail(rs.getString(9));
				set.setStudentPhoneNumber(rs.getString(10));
				int advisorId = rs.getInt(11);
				command = "select u.email from user u where u.userId=?";
				statement = conn.prepareStatement(command);
				statement.setInt(1, advisorId);
				ResultSet innerQueryResult = statement.executeQuery();
				if(innerQueryResult.next()){
					set.setAdvisorEmail(innerQueryResult.getString(1));	
				}
				
				Appointments.add(set);
			}
			conn.close();
		}
		catch(Exception e){
			//System.out.printf(e.toString());
		}
		
		return Appointments;
	}
	//getAppointmentsAdvisor

	public ArrayList<Object> getAppointments(AdminUser user){
		ArrayList<Object> Appointments = new ArrayList<Object>();
		try{
			Connection conn = this.connectDB();
			PreparedStatement statement;
//			String command = "SELECT User_Advisor.pname,User_Advisor.email,date,start,end,type,id FROM Appointments INNER JOIN User_Advisor "
					String command = "SELECT User_Advisor.pname,User.email,date,start,end,type,id,description,student_email, student_cell,studentId,comments FROM USER inner join Appointments on Appointments.advisor_userId=USER.userId inner join User_Advisor on User_Advisor.userId = Appointments.advisor_userId WHERE advisor_userId in (select user_advisor.userId from user_advisor Inner JOin department_user on department_user.userId = user_advisor.userId where department_user.name in (select du.name from user u inner join department_user du on du.userId = u.userId where u.userId =?))";
			statement = conn.prepareStatement(command);
			statement.setLong(1, user.getUserId());
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				Appointment set = new Appointment();
				set.setPname(rs.getString(1));
				set.setAdvisorEmail(rs.getString(2));
				set.setAdvisingDate(rs.getString(3));
				set.setAdvisingStartTime(rs.getString(4));
				set.setAdvisingEndTime(rs.getString(5));
				set.setAppointmentType(rs.getString(6));
				set.setAppointmentId(rs.getInt(7));
				set.setDescription(rs.getString(8));
				set.setStudentEmail(rs.getString(9));
				set.setStudentPhoneNumber(rs.getString(10));
				set.setStudentid(rs.getString(11));
				set.setAdvisorComments(rs.getString(12));
				Appointments.add(set);
			}
			conn.close();
		}
		catch(Exception e){
			//System.out.printf(e.toString());
		}
		
		return Appointments;
	}
	
	public Boolean cancelAppointment(int id){
		Boolean result = false;
		try{
			Connection conn = this.connectDB();
			PreparedStatement statement;
			String command = "SELECT count(*),date,start, end from Appointments where id=?";
			statement=conn.prepareStatement(command);
			statement.setInt(1,id);
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				if (rs.getInt(1) == 1){
					command = "DELETE FROM Appointments where id=?";
					statement=conn.prepareStatement(command);
					statement.setInt(1, id);
					statement.executeUpdate();
					command = "UPDATE Advising_Schedule SET studentId=null where date=? AND start >=? AND end <=?";
					statement=conn.prepareStatement(command);
					statement.setString(1, rs.getString(2));
					statement.setString(2,rs.getString(3));
					statement.setString(3, rs.getString(4));
					statement.executeUpdate();
					result = true;
				}
			}
			conn.close();	
		}
		catch(SQLException e){
			//System.out.printf("Error in Database: " + e.toString());
			return false;
		}
		return result;
	}

	public ArrayList<Object> appId(String scheduleId){
		
		/*ArrayList<String> AppointmentIds = new ArrayList<String>();
		
		SQLCmd cmdSchedule = new AppId(scheduleId);
		cmdSchedule.execute();
		
		return AppointmentIds;
		int scheduleID = (int)cmdSchedule.getResult().get(1);
		//cs.setScheduleID(scheduleID);
		return String.valueOf(scheduleID);*/
		

		ArrayList<Object> arraylist = new ArrayList<Object>();
		try{
			SQLCmd cmd = new AppId(scheduleId);
			cmd.execute();
			ArrayList<Object> tmp = cmd.getResult();
			for (int i=0;i<tmp.size();i++){
				arraylist.add((tmp.get(i)));
			}
		}
		catch(Exception sq){
			//System.out.printf(sq.toString());
		}
		return arraylist;
	
	}
	

	
	public String addTimeSlot(AllocateTime at){
		SQLCmd cmd = new GetUserIDByEmail(at.getEmail());
		cmd.execute();
		int id = (int)cmd.getResult().get(0);
		cmd = new CheckTimeSlot(at,id);
		cmd.execute();
		
		if ((Boolean)cmd.getResult().get(0) == true){
			cmd = new AddTimeSlot(at,id);
			cmd.execute();
			return (String)cmd.getResult().get(0);
		}
		else{
			return "Unable to add time slot.";
		}
	}
	
	public String createSchedule(CreateScheduleBean cs){
		SQLCmd cmd = new GetUserIDByEmail(cs.getUserEmail());
		cmd.execute();
		int id = (int)cmd.getResult().get(0);
	
		SQLCmd cmdSchedule = new CreateSchedule(cs,id);
		cmdSchedule.execute();
		int scheduleID = (int)cmdSchedule.getResult().get(1);
		//cs.setScheduleID(scheduleID);
		return String.valueOf(scheduleID);
	}
	public AdvisorUser getAdvisor(String email){
		SQLCmd cmd = new GetUserIDByEmail(email);
		cmd.execute();
		Integer userId = (Integer)cmd.getResult().get(0);
		
		cmd = new GetAdvisorById(userId);
		cmd.execute();
		return (AdvisorUser)cmd.getResult().get(0);
	}
	
	public StudentUser getStudent(String email){
		SQLCmd cmd = new GetUserIDByEmail(email);
		cmd.execute();
		Integer userId = (Integer)cmd.getResult().get(0);
		
		cmd = new GetStudentById(userId);
		cmd.execute();
		return (StudentUser)cmd.getResult().get(0);
	}
	
	public AdminUser getAdmin(String email){
		SQLCmd cmd = new GetUserIDByEmail(email);
		cmd.execute();
		Integer userId = (Integer)cmd.getResult().get(0);
		
		cmd = new GetAdminById(userId);
		cmd.execute();
		return (AdminUser)cmd.getResult().get(0);
	}
	
	public FacultyUser getFaculty(String email){
		SQLCmd cmd = new GetUserIDByEmail(email);
		cmd.execute();
		Integer userId = (Integer)cmd.getResult().get(0);
		
		cmd = new GetFacultyById(userId);
		cmd.execute();
		return (FacultyUser)cmd.getResult().get(0);
	}
	
	public ArrayList<AppointmentType> getAppointmentTypes(String pname){
			ArrayList<AppointmentType> ats = new ArrayList<AppointmentType>();
			try{
			Connection conn = this.connectDB();
			PreparedStatement statement;
			String command = "SELECT type,duration,user.email FROM  Appointment_Types,User_Advisor,user WHERE Appointment_Types.userid=User_Advisor.userid AND User_Advisor.userid=user.userid AND User_Advisor.pname=?";
			statement = conn.prepareStatement(command);
			statement.setString(1,pname);
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				AppointmentType at = new AppointmentType();
				at.setType(rs.getString(1));
				at.setDuration(rs.getInt(2));
				at.setEmail(rs.getString(3));
				ats.add(at);
			}
			conn.close();
		}
		catch(Exception e){
			//System.out.println(e);
		}
		return ats;
	
	}
	
	public String deleteTimeSlot(AllocateTime at){
		String b;
		SQLCmd cmd = new DeleteTimeSlot(at);
		cmd.execute();
		b = (String)(cmd.getResult()).get(0);
		return b;
	}
	
	public Appointment getAppointment(String d, String e){
		Appointment app = null;
		try{
			SQLCmd cmd = new GetAppointment(d,e);
			cmd.execute();
			if (cmd.getResult().size() > 0){
				app = (Appointment)(cmd.getResult()).get(0);
			}
		}
		catch(Exception ex){
			//System.out.println(ex);
		}
		return app;
	}
	
	public Integer createUser(LoginUser loginUser){
		Integer userId = -1;
		try{
			SQLCmd cmd = new CreateUser(loginUser);
			cmd.execute();
			if ((Boolean)cmd.getResult().get(0)){
				cmd = new GetUserIDByEmail(loginUser.getEmail());
				cmd.execute();
				userId = (int)cmd.getResult().get(0);
			}
			else{
				//System.out.println("User not created"+"RDBImpl");
			}
		}
		catch(Exception e){
			//System.out.println(e+"RDBImpl");
		}

		return userId;
	}
	
	public Boolean createAdvisor(AdvisorUser advisorUser){
		advisorUser.setRole("advisor");
		
		try{
			SQLCmd cmd = new CreateUser(advisorUser);
			cmd.execute();
			cmd = new CreateAdvisor(advisorUser);
			cmd.execute();
			//System.out.println("Created Advisor");
				return (Boolean)cmd.getResult().get(0);
		}
		catch(Exception e){
			//System.out.println(e+this.getClass().getName());
			return false;
		}
	}
	
	public String addAppointmentType(AdvisorUser user, AppointmentType at){
		String msg = null;
		SQLCmd cmd = new GetUserIDByEmail(user.getEmail());
		cmd.execute();
		cmd = new AddAppointmentType(at, (int)cmd.getResult().get(0));
		cmd.execute();
		return (String)cmd.getResult().get(0);
	}
	
	//using command pattern
	public ArrayList<String> getDepartmentStrings() throws SQLException{
		ArrayList<String> arraylist = new ArrayList<String>();
		try{
			SQLCmd cmd = new GetDepartmentNames();
			cmd.execute();
			ArrayList<Object> tmp = cmd.getResult();
			for (int i=0;i<tmp.size();i++){
				arraylist.add(((String)tmp.get(i)));
			}
		}
		catch(Exception sq){
			//System.out.printf(sq.toString());
		}
		return arraylist;
	}
	
	//using command pattern
	public ArrayList<String> getMajor() throws SQLException{
		ArrayList<String> arraylist = new ArrayList<String>();
		try{
			SQLCmd cmd = new GetMajors();
			cmd.execute();
			ArrayList<Object> tmp = cmd.getResult();
			for (int i=0;i<tmp.size();i++){
				arraylist.add(((String)tmp.get(i)));
			}
		}
		catch(Exception sq){
			//System.out.printf(sq.toString());
		}
		return arraylist;
	}
	
	public ArrayList<AdvisorUser> getAdvisorsOfDepartment(String department) throws SQLException {
		
		ArrayList<AdvisorUser> advisorUsers = new ArrayList<AdvisorUser>();
		SQLCmd sqlCmd = new GetAdvisorIdsOfDepartment(department);
		sqlCmd.execute();
		//System.out.println("User Ids "+sqlCmd.getResult());
		
		for(int i=0; i<sqlCmd.getResult().size(); i++)
		{
			Integer userId = Integer.valueOf((String)sqlCmd.getResult().get(i));
			//System.out.println("User Id "+userId);
			SQLCmd sqlCmd2 = new GetAdvisorById(userId);
			sqlCmd2.execute();
			//System.out.println("Advisor "+sqlCmd2.getResult());
			AdvisorUser advisorUser = (AdvisorUser)sqlCmd2.getResult().get(0);
			advisorUsers.add(advisorUser);
		}
		
		return advisorUsers;
	}
	
	public Boolean updateAdvisors(ArrayList<AdvisorUser> advisorUsers) throws SQLException {
		
		for(int i=0; i<advisorUsers.size(); i++)
		{
			SQLCmd sqlCmd = new UpdateAdvisor(advisorUsers.get(i));
			sqlCmd.execute();
		}	
		return true;
	}
	
	public ArrayList<Department> getDepartments() throws SQLException {
		SQLCmd sqlCmd = new GetDepartmentNames();
		sqlCmd.execute();
		Department department;
		
		ArrayList<Department> departments = new ArrayList<Department>();
		for(int depIndex=0; depIndex<sqlCmd.getResult().size(); depIndex++)
		{
			String depName = (String)sqlCmd.getResult().get(depIndex);
			SQLCmd sqlCmd2 = new GetDepartmentByName(depName);
			sqlCmd2.execute();
		
			department = (Department)sqlCmd2.getResult().get(0);
			departments.add(department);
		}
		return departments;
	}
	
	public Department getDepartmentByName(String name) throws SQLException {
		SQLCmd sqlCmd2 = new GetDepartmentByName(name);
		sqlCmd2.execute();
		return (Department)sqlCmd2.getResult().get(0);
	}
	
	public Boolean updateUser(LoginUser loginUser) throws SQLException {
		SQLCmd sqlCmd = new UpdateUser(loginUser);
		sqlCmd.execute();
		return true;
	}
	
	public HashMap<String, ArrayList<String>> getAppointmentsUnderAdvisor(String advisorList){
		
		Connection conn = this.connectDB();
		ResultSet rs = null;
		HashMap<String, ArrayList<String>> advisorNameStudentMap = new HashMap<String, ArrayList<String>>();
		Statement statement = null;
		try {
			String command = "SELECT distinct adv.pName, app.student_email FROM appointments app, user_advisor adv WHERE adv.userId = app.advisor_userId AND adv.userId IN (" + advisorList + ")";
			statement = conn.createStatement();
			rs = statement.executeQuery(command);
			while(rs.next()){
				String advisor = rs.getString("pName");
				String studentEmail = rs.getString("student_email");
				ArrayList<String> studentEmails = new ArrayList<String>();
				if(advisorNameStudentMap.containsKey(advisor)){
					studentEmails = advisorNameStudentMap.remove(advisor);
				}
				studentEmails.add(studentEmail);
				advisorNameStudentMap.put(advisor, studentEmails);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (statement != null)
					statement.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			}
		}
		return advisorNameStudentMap;
	}
	
	public boolean deleteAdvisor(String advisorList){
		
		Connection conn = this.connectDB();
		Statement statement = null;
		boolean response = false;
		try {
			conn.setAutoCommit(false);
			String command = "DELETE FROM appointments WHERE advisor_userId IN ("+advisorList+")";
			statement = conn.createStatement();
			statement.executeUpdate(command);
			
			command = "DELETE FROM advising_schedule WHERE userId IN ("+advisorList+")";
			statement = conn.createStatement();
			statement.executeUpdate(command);
			
			command = "DELETE FROM department_user WHERE userId IN ("+advisorList+")";
			statement = conn.createStatement();
			statement.executeUpdate(command);
			
			command = "DELETE FROM major_user WHERE userId IN ("+advisorList+")";
			statement = conn.createStatement();
			statement.executeUpdate(command);
			
			command = "DELETE FROM user_advisor WHERE userId IN ("+advisorList+")";
			statement = conn.createStatement();
			statement.executeUpdate(command);
			
			command = "DELETE FROM user WHERE userId IN ("+advisorList+")";
			statement = conn.createStatement();
			statement.executeUpdate(command);
			
			command = "DELETE FROM appointment_types WHERE userId IN ("+advisorList+")";
			statement = conn.createStatement();
			statement.executeUpdate(command);
			
			conn.commit();
			response = true;
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			response = false;
		} finally {
			try {
				statement.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			}
		}
		return response;
	}


	@Override
	public String updateNotification(StudentUser user, String notification) {
		
		Connection conn = this.connectDB();
		try {
			conn.setAutoCommit(false);
			PreparedStatement statement;
			String command = "UPDATE user_student SET notification = ? WHERE userId = ?";
			statement=conn.prepareStatement(command);
			statement.setString(1, notification);
			statement.setInt(2, user.getUserId());
			statement.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			try {
				if(conn != null)
					conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "Updated successfully";
	}
	
	@Override
	public String editAppointmentType(AdvisorUser user, AppointmentType at) {
		String msg = null;
		SQLCmd cmd = new GetUserIDByEmail(user.getEmail());
		cmd.execute();
		/*cmd = new EditAppointmentType(at, (int) cmd.getResult().get(0));
		cmd.execute();
		return (String) cmd.getResult().get(0);
		*/
		int userId = (int) cmd.getResult().get(0);
		return String.valueOf(createEditAppointmentTypePrototype(at,userId)); 
	}

	
	private int createEditAppointmentTypePrototype(AppointmentType at, int userId) {
 		RDBCmdPrototype prototype = PrototypeMgr.getInstance().get("Update Prototype");
 		if(prototype == null){
 			prototype = new UpdatePrototype(new HashMap<String, Object>(), new HashMap<String, Object>());
 			PrototypeMgr.getInstance().add("Update Prototype", prototype);
 		}
 		UpdatePrototype prot;
 		Map<String, Object> whereParams = new HashMap<String, Object>();
 		Map<String, Object> setParams = new HashMap<String, Object>();
 		whereParams.put("userId", userId);
 		whereParams.put("type", at.getType());
 		setParams.put("duration", at.getDuration());
 		try {
 			prot = (UpdatePrototype) prototype.clone();
 			prot.setTableName("Appointment_Types");
 			prot.setSetParams(setParams);
 			prot.setWhereParams(whereParams);
 			prot.execute();
 			int advisorUser = prot.getPrototypeResult();
 			return advisorUser;
 		} catch (CloneNotSupportedException e) {
 			e.printStackTrace();
 		}
 		
 		return 0;		
 	}
	
	
	@Override
	public String cancelAppointmentType(AdvisorUser user, AppointmentType at) {
		String msg = null;
		SQLCmd cmd = new GetUserIDByEmail(user.getEmail());
		cmd.execute();
		cmd = new CancelAppointmentType(at, (int) cmd.getResult().get(0));
		cmd.execute();
		return (String) cmd.getResult().get(0);
	}

	@Override
	public String updateNotification(AdvisorUser user, String notification) {
		
		Connection conn = this.connectDB();
		try {
			conn.setAutoCommit(false);
			PreparedStatement statement;
			String command = "UPDATE user_advisor SET notification = ? WHERE userId = ?";
			statement=conn.prepareStatement(command);
			statement.setString(1, notification);
			statement.setInt(2, user.getUserId());
			statement.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			try {
				if(conn != null)
					conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "Updated successfully";
	}


	@Override
	public Boolean editTimeSlot(AllocateTime a) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Boolean resetPassword(String emailAddress,String resetKey, String date) {
		// TODO Auto-generated method stub
		String msg = null;
		SQLCmd cmd = new ResetPass(emailAddress,resetKey,date);
		cmd.execute();
		return (Boolean) cmd.getResult().get(0);
	}


	@Override
	public ArrayList<Object> getEmailResetPass(String resetKey) {
		// TODO Auto-generated method stub
		
	
		//ArrayList<Object> e = new ArrayList<Object>();
		
		SQLCmd cmd = new EmailResetPass(resetKey);
		cmd.execute();
		ArrayList<Object> tmp = cmd.getResult();
	/*	for (int i=0;i<tmp.size();i++){
			e.add(((String)tmp.get(i)));
		}
	*/	return tmp;
	}


	@Override
	public Boolean setResetPass(String email, String hashpass) {
		// TODO Auto-generated method stub
		SQLCmd cmd = new SetResetPass(email,hashpass);
		cmd.execute();
		return (Boolean) cmd.getResult().get(0);
	}
}

