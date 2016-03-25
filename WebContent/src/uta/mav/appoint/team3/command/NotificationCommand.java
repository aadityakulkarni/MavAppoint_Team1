package uta.mav.appoint.team3.command;

import java.io.IOException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import uta.mav.appoint.team3fall.singleton.ConfigFileReader;

/**
 * Template and Command Pattern
 * @author Ruchi.U
 *
 */
public abstract class NotificationCommand {
	
	protected String user = ConfigFileReader.getInstance().getValue("EMAIL_USERNAME");
    protected String pass = ConfigFileReader.getInstance().getValue("EMAIL_PASSWORD");
	protected Session session;
	protected MimeMessage message;
    
    public abstract void createMessage() throws MessagingException, IOException;
	
	public void execute(){
		try {
			createSession();
			createMessage();
			sendMessage();
			closeSession();
		} catch(Exception e){
			//Failed to create outlook message
		}
	}
	
	public void createSession(){

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
 
        session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
          });
	}
	
	public void sendMessage() throws MessagingException{
		Transport.send(message);
	}
	
	public void closeSession(){
		session = null;
	}

}
