package uta.mav.appoint.team3.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashPassword {
	public static String hashpass(String pass){
		MessageDigest m = null;
		try {
			m = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] pass1=pass.getBytes();
		
		String hashpassw=new String(m.digest(pass1),StandardCharsets.UTF_8);
		return hashpassw;	
	}
}