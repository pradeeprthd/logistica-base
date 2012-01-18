package logistica.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static Date getLastTime(Date fecha) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		calendar.add(Calendar.HOUR, 23);
		calendar.add(Calendar.MINUTE, 59);
		calendar.add(Calendar.SECOND, 59);

		return calendar.getTime();
	}

	public static Date getFirstTime(Date fecha) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		calendar.add(Calendar.HOUR, 0);
		calendar.add(Calendar.MINUTE, 0);
		calendar.add(Calendar.SECOND, 0);

		return calendar.getTime();
	}

}
