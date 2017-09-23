package br.com.mybackup.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UTIL {
	public String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
		Date date = new Date();
		return dateFormat.format(date);
	}
}
