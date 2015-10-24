package edu.asu.safemoney.service.impln;


//Secure Random Generator
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Seconds;
//Add commons-lang-2.4.jar




import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;

import edu.asu.safemoney.dao.LoginDAO;




public class OTPGenerator {
 /* public static void main(String[] args) {
      int otpLength=6;
      for(int i=0; i<1;i++) {
          System.out.println("OTP using SecureRandomGenerator: "+(i+1)+"==>"+generateOTP(otpLength));
         
      }
      timeDifference td = new timeDifference();
     System.out.println(td.timeDiff("2012-05-03 00:09:50", "2012-05-03 10:11:58"));
  }*/

  public static String generateOTP(int otpLengthNumber){
      String otp = new String();
      int otpSample=0;
      for(int i=0;i<otpLengthNumber;i++){
          otp=otp+"9";
      }

      otpSample=Integer.parseInt(otp);
    //Number Generation Algorithm
      SecureRandom prng;
      try {
          prng = SecureRandom.getInstance("SHA1PRNG"); 
          otp = new Integer(prng.nextInt(otpSample)).toString();
          otp = (otp.length() < otpLengthNumber) ? padleft(otp, otpLengthNumber, '0') : otp;
      } catch (NoSuchAlgorithmException e) {
      }

//      If generated OTP exists in DB -regenerate OTP again
      boolean otpExists=false;
      if (otpExists) {
          generateOTP(otpLengthNumber);
      } else {
          return otp;
      }
     
      return otp;
  }
  private static String padleft(String s, int len, char c) { //Fill with some char or put some logic to make more secure
      s = s.trim();
      StringBuffer d = new StringBuffer(len);
      int fill = len - s.length();
      while (fill-- > 0)
          d.append(c);
      d.append(s);
      return d.toString();
  }
  public static String generateRandomOTP(int otpLengthNumber){
      String otp = RandomStringUtils.randomNumeric(otpLengthNumber);
      return otp;
  }
}

 class timeDifference
 {
	SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	boolean timeDiff(String s1, Timestamp t2)
	{
		 System.out.println("Inside td method + 1st time"+ s1);
		 System.out.println("t2"+ t2);
	
		try {
			
	
			
			  Timestamp t1 = Timestamp.valueOf(s1);
			  //Timestamp t2 = Timestamp.valueOf(s2);
		 
		 
		long milliseconds1 = t1.getTime();
		  long milliseconds2 = t2.getTime();

		  long diff = milliseconds2 - milliseconds1;
		  
		  System.out.println(diff);
		  if(diff<=180000 && diff>0)
		  {
			  return true;
		  }
		  
		  else return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			return false;
		}
		
		
		//Another code
		/*Date d1 = null;
		Date d2 = null;
		
		try {
			d1 = format.parse(s1);
			d2 = format.parse(s2);
	 
			DateTime dt1 = new DateTime(d1);
			DateTime dt2 = new DateTime(d2);
			
	 
			System.out.print(Days.daysBetween(dt1, dt2).getDays() + " days, ");
			System.out.print(Hours.hoursBetween(dt1, dt2).getHours() % 24 + " hours, ");
			System.out.print(Minutes.minutesBetween(dt1, dt2).getMinutes() % 60 + " minutes, ");
			System.out.print(Seconds.secondsBetween(dt1, dt2).getSeconds() % 60 + " seconds.");
	 
		 } catch (Exception e) {
			e.printStackTrace();
		 }*/
	}
 }
