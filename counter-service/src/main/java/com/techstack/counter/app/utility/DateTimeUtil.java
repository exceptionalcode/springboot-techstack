package com.techstack.counter.app.utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTimeUtil {

	public static String getCurrentDateTime() {
		return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
	}
}
