package logistica.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static Date getLastTime(Date fecha) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		calendar.set(Calendar.HOUR_OF_DAY, 23);            
		calendar.set(Calendar.MINUTE, 59);                
		calendar.set(Calendar.SECOND, 59);                
		calendar.set(Calendar.MILLISECOND, 99);

		return calendar.getTime();
	}

	public static Date getFirstTime(Date fecha) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		calendar.set(Calendar.HOUR_OF_DAY, 0);            
		calendar.set(Calendar.MINUTE, 0);                
		calendar.set(Calendar.SECOND, 0);                
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}
}
