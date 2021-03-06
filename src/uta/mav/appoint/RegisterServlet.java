package uta.mav.appoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uta.mav.appoint.login.*;
import uta.mav.appoint.team3.chainofresponsibility.AbstractLogger;
import uta.mav.appoint.team3.controller.GetDepartmentsController;
import uta.mav.appoint.team3.controller.RegistrationController;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L; // test comment
	private ArrayList<Department> departments;
	private ArrayList<String> majors;
	
	HttpSession session;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		majors = new ArrayList<String>();
		ArrayList<String> degreeType = new ArrayList<>();
		degreeType.add("Bachelor");
		degreeType.add("Master");
		degreeType.add("Doctorate");
		
		session.setAttribute("degreeType", degreeType);
		
		try {
			departments = GetDepartmentsController.getDepartments();
			
			for(Department dept : departments){
				majors.addAll(dept.getMajors());
			}
			
			//majors = departments.get(0).getMajors();
			session.setAttribute("departments", departments);
			session.setAttribute("major", majors);
			AbstractLogger.getInstance().logMessage(AbstractLogger.INFO, "In register servlet");
		} catch(Exception e){
			session.setAttribute("message", "Error while loading page");
		}
		
		session.setAttribute("message", "");
		
		request.setAttribute("includeHeader", "templates/header.jsp");
		request.getRequestDispatcher("/WEB-INF/jsp/views/register.jsp").forward(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		List<Department> selectedDeps = new ArrayList<Department>();
		if(!Boolean.valueOf(request.getParameter("submitted")))
		{
			System.out.println("I am in submitted");
			/*String[] deptParams = request.getParameterValues("drp_department");
			for(String dept:deptParams){
				Integer departmentIndex = Integer.valueOf(dept);
				selectedDeps.add(departments.get(departmentIndex));
			}
			//Integer departmentIndex = Integer.valueOf(request.getParameter("drp_department"));
			//Department selectedDep = departments.get(departmentIndex);
			departments.remove(selectedDep);
			departments.add(0, selectedDep);
			session.setAttribute("departments", departments);
			
			majors = selectedDep.getMajors();
			session.setAttribute("major", majors);
			
			request.setAttribute("includeHeader", "templates/header.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/views/register.jsp").forward(request,response);*/
		}
		else
		{
			try{
				ArrayList<String> departmentsSelected = new ArrayList<String>();
				String[] deptParams = request.getParameterValues("drp_department");
				for(String dept:deptParams){
					Integer departmentIndex = Integer.valueOf(dept);
					//selectedDeps.add(departments.get(departmentIndex));
					String departmentFound = departments.get(Integer.valueOf(departmentIndex)).getName();
					departmentsSelected.add(departmentFound);
				}
				
				String email = request.getParameter("emailAddress");
				String phone_num = request.getParameter("phone_num");
				String studentId = request.getParameter("student_Id");
				String lastName = request.getParameter("drp_last_name_initial");
				String drpDegreeType = request.getParameter("drp_degreeType");
				
				ArrayList<String> majorsSelected = new ArrayList<String>();
				String majorFound = majors.get(Integer.valueOf(request.getParameter("drp_major")));
				String minorFound = majors.get(Integer.valueOf(request.getParameter("drp_minor")));
				majorsSelected.add(majorFound);
				
				String message = RegistrationController.registerStudent(email, phone_num, studentId, lastName, drpDegreeType, departmentsSelected, majorsSelected, minorFound);
				session.setAttribute("message", message);
				
			}
			catch(Exception e){
				////System.out.println(e+" RegisterServlet");
				session.setAttribute("message", "Error while registering");
			}
			
			request.setAttribute("includeHeader", "templates/header.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/views/register.jsp").forward(request,response);
		}
	}
}