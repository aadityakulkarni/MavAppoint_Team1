import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Tests {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	/*	String date = "2016-05-04";
		String datePrev;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Calendar c = Calendar.getInstance();
			Date date1 = format.parse(date);
			c.setTime(date1);
			c.add(Calendar.DATE, -7);  // number of days to add
			datePrev = format.format(c.getTime());
			System.out.println(datePrev+" ");
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		Calendar cal1 = new GregorianCalendar();
	     Calendar cal2 = new GregorianCalendar();

	     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	     Date date1,date2;
		try {
			date1 = sdf.parse("2016-04-27");
			 cal1.setTime(date1);
		     date2 = sdf.parse("2016-06-01");
		     cal2.setTime(date2);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    //cal1.set(2008, 8, 1); 
	     //cal2.set(2008, 9, 31);
	     System.out.println("Days= "+daysBetween(cal1.getTime(),cal2.getTime()));
		
	}
	  public static int daysBetween(Date d1, Date d2){
          return (int)( ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24))/7);
  }
}
