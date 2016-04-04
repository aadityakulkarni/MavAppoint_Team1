package uta.mav.appoint.team3.command;

public class CalendarInvite {
	
	private static StringBuffer buffer = null;
	
	public static StringBuffer getBuffer(){
		if (buffer == null){
			buffer = new StringBuffer();
			buffer.append("BEGIN:VCALENDAR\n")
			.append("PRODID:-//Microsoft Corporation//Outlook 9.0 MIMEDIR//EN\n")
			.append("VERSION:2.0\n")
			.append("METHOD:REQUEST\n")
			.append("BEGIN:VEVENT\n")
			.append("ATTENDEE;ROLE=REQ-PARTICIPANT;RSVP=TRUE:MAILTO:<TO_ADDRESS>\n")
			.append("DTSTART:<START_TIME>\n")
			.append("DTEND:<END_TIME>\n")
			.append("LOCATION:OFFICE OF <ADVISOR_NAME>\n")
			.append("TRANSP:OPAQUE\n")
			.append("SEQUENCE:0\n")
			.append("UID:040000008200E00074C5B7101A82E00800000000002FF466CE3AC5010000000000000000100\n")
			.append(" 000004377FE5C37984842BF9440448399EB02\n")
			.append("CATEGORIES:Meeting\n")
			.append("DESCRIPTION:Advising Appointment\n")
			.append("SUMMARY:You have an appointment with <ADVISOR_NAME>\n")
			.append("PRIORITY:5\n")
			.append("CLASS:PUBLIC\n")
			.append("BEGIN:VALARM\n")
			.append("TRIGGER:-CT15M\n")
			.append("ACTION:DISPLAY\n")
			.append("DESCRIPTION:Reminder\n")
			.append("END:VALARM\n")
			.append("END:VEVENT\n")
			.append("END:VCALENDAR");
		}
		return buffer;
	}
}
