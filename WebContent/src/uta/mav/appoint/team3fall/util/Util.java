package uta.mav.appoint.team3fall.util;

public class Util {
	
	public static boolean validateEmail(String email){
		return email.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$");
	}
	
	public static boolean validatePhoneNumber(String phoneNumber){
		return phoneNumber.matches("^\\d{3}-\\d{3}-\\d{4}");
	}
	
	public static boolean validateStudentId(String studentId){
		return studentId.matches("^100\\d{7}") || studentId.matches("^6000\\d{6}");
	}
	
	public static String addTime(String hour, String minute, int add){
		String result = "";
		try{
		int h = Integer.parseInt(hour);
		int m = Integer.parseInt(minute);
		if (m + add >= 60){
			m = m+add-60;
			h++;
		}
		else{
			m = m+add;
		}
		result = h+":"+m;
		}
		catch(Exception e){
			
		}
		return result;
	}
	
	public static String convertDate(String d){
		if (d.equals("Jan")){
			return "1";
		}if (d.equals("Feb")){
			return "2";
		}if (d.equals("Mar")){
			return "3";
		}if (d.equals("Apr")){
			return "4";
		}if (d.equals("May")){
			return "5";
		}if (d.equals("Jun")){
			return "6";
		}if (d.equals("Jul")){
			return "7";
		}if (d.equals("Aug")){
			return "8";
		}if (d.equals("Sep")){
			return "9";
		}if (d.equals("Oct")){
			return "10";
		}if (d.equals("Nov")){
			return "11";
		}if (d.equals("Dec")){
			return "12";
		}
		return null;
	}

}
