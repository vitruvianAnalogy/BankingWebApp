package edu.asu.safemoney.helper;

import java.util.Date;
import java.util.UUID;

public class ExternalUserHelper {

	public static long generateRandomNumber()
	{
		long random = UUID.randomUUID().getLeastSignificantBits();
		random = System.currentTimeMillis() + random;
		random = (random / System.currentTimeMillis());
		return Math.abs(random);
	}
	
	public static long generateAccountNumber(int memberId)
	{
		long random = UUID.randomUUID().getLeastSignificantBits();
		random = System.currentTimeMillis() + random;
		random = (random / (memberId * 100000) );
		return Math.abs(random);
	}
	
	public static int getNumDays(Date date1, Date date2)
	{
		final long DAY_TO_MILLI_SECCOND = 1000 * 60 * 60 * 24;

		int numDays = (int) ((date1.getTime() - date2.getTime())/ DAY_TO_MILLI_SECCOND );
		return numDays;
	}
}
