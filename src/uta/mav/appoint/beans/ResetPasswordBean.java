package uta.mav.appoint.beans;

import java.io.Serializable;

public class ResetPasswordBean implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -7132310351445961611L;

	private String email;
	private String date;
	private String resetkey;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getResetkey() {
		return resetkey;
	}
	public void setResetkey(String resetkey) {
		this.resetkey = resetkey;
	}
	

}
