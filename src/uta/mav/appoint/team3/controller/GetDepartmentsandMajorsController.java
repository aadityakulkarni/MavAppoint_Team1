package uta.mav.appoint.team3.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import uta.mav.appoint.db.DatabaseManager;
import uta.mav.appoint.login.Department;

public class GetDepartmentsandMajorsController {
	
	
	public static ArrayList<Department> getDepartments() throws SQLException{
		DatabaseManager dbm = new DatabaseManager();
		return dbm.getDepartments();
	}
}
