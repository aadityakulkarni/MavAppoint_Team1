package uta.mav.appoint.team3.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TestPassword {

	/**
	 * @param args
	 * @throws NoSuchAlgorithmException 
	 */
	public static void main(String[] args) throws NoSuchAlgorithmException {
		System.out.println(new String(MessageDigest.getInstance("MD5").digest("d3o!zzvmn#".getBytes())));
	}

}
