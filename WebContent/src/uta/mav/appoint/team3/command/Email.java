package uta.mav.appoint.team3.command;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email extends NotificationCommand{
	
	String to_address;
	String subject;
	String text;
	
	public Email(String to_address, String subject, String text){
		this.to_address = to_address;
		this.subject = subject;
		this.text = text;
	}
	
	@Override
	public void createMessage() {
		// TODO Auto-generated method stub
        try {
            message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(this.to_address));
            message.setSubject(this.subject);
            message.setText(this.text);
        }
		catch (MessagingException e) {
            throw new RuntimeException(e);
        }
	}

	public Email() {
		// TODO Auto-generated constructor stub
	}

	public void setTo_address(String to_address) {
		this.to_address = to_address;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTo_address() {
		return to_address;
	}

	public String getSubject() {
		return subject;
	}

	public String getText() {
		return text;
	}
	
}
