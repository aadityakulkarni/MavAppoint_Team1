
package uta.mav.appoint.team3.command;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

public class OutlookNotification extends NotificationCommand {

	String to_address;
	String start_time;
	String end_time;
	String subject;
	String advisorName;
	
	@Override
	public void createMessage() throws MessagingException, IOException {
		// TODO Auto-generated method stub
		message = new MimeMessage(session);
		message.addHeaderLine("method=REQUEST");

		message.addHeaderLine("charset=UTF-8");
		message.addHeaderLine("component=VEVENT");

		message.setFrom(new InternetAddress(user));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(this.to_address));
		message.setSubject("Advising Appointment");

		String buffer = CalendarInvite.getBuffer().toString();
		buffer = buffer.replaceAll("<TO_ADDRESS>", this.to_address);
		buffer = buffer.replaceAll("<START_TIME>", this.start_time);
		buffer = buffer.replaceAll("<END_TIME>", this.end_time);
		buffer = buffer.replaceAll("<ADVISOR_NAME>", this.advisorName);

		BodyPart messageBodyPart = new MimeBodyPart();
		// Fill the message
		messageBodyPart.setHeader("Content-Class", "urn:content-  classes:calendarmessage");
		messageBodyPart.setHeader("Content-ID", "calendar_message");
		messageBodyPart.setDataHandler(new DataHandler(new ByteArrayDataSource(buffer.toString(), "text/calendar")));// very
																														// important

		// Create a Multipart
		Multipart multipart = new MimeMultipart();

		// Add part one
		multipart.addBodyPart(messageBodyPart);

		// Put parts in message
		message.setContent(multipart);
	}
	
	
	public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmm'00'");
	public static SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	public OutlookNotification(String to_address, String advising_date, String start_time, String end_time,
			String subject, String advisorName) {
		super();
		this.to_address = to_address;
		try {
			this.start_time = dateFormat.format(dateParser.parse(advising_date + " " + start_time));
			this.end_time = dateFormat.format(dateParser.parse(advising_date + " " + end_time));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.subject = subject;
		this.advisorName = advisorName;
	}

}
